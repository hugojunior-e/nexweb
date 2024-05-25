package nexcabo;

import java.util.Vector;

public class InserirProgramacoes {

    public String pegaValorIdx(String[] a, int i) {
        try {
            return a[i];
        } catch (Exception exx) {
            return "";
        }
    }


    public String mostraCanaisLancados(String mesano) {
        try {
            String sql =
                    "select canal,\n" +
                            "       count(distinct to_char(data_correta, 'DDMMYYY')) qtd_dias,\n" +
                            "       to_char(trunc(min(data_correta)),'DD/MM/YYYY') menor_data,\n" +
                            "       to_char(trunc(max(data_correta)),'DD/MM/YYYY') maior_data\n" +
                            "  from sinopse\n" +
                            " where mesano = '" + mesano + "'\n" +
                            " group by canal";

            StringBuilder ret = new StringBuilder();

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ret.append("<tr>");
                ret.append("<td>" + UUtils.getValue(rs.getString("CANAL")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("QTD_DIAS")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("MENOR_DATA")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("MAIOR_DATA")) + "</td>");
                ret.append("</tr>");
            }
            rs.close();
            st.close();

            return ret.substring(0);
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String apagarProgramacao(String p_mesano, String p_canal) {
        try {
            String sql = "delete from sinopse where canal='"
                    + p_canal.toUpperCase() + "' and mesano = '" + p_mesano
                    + "'";
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            return "Apagado com Sucesso!";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }


    public String jsEfetuarCorrecoes() {
        try {
            String sql = "begin PRO_ACERTA_DATA_CORRETA_GERAL();   end;";
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            return "Atualizado com Sucesso!";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String[] importarArquivo(int tipo, String nome_arquivo) {
        int i = 0;
        try {
            System.setProperty("file.encoding", "Cp1252");
            String[] ret = new String[]{"", "", "", "", "", "", "", ""};

            java.io.BufferedReader rd = new java.io.BufferedReader(
                    new java.io.InputStreamReader(new java.io.FileInputStream(
                            "/var/www/xml/" + nome_arquivo), "Cp1252"));
            String linha = "";
            while ((linha = rd.readLine()) != null) {

                String[] valores = (linha).split(";");
                if (tipo == 1) {
                    ret[0] += pegaValorIdx(valores, 0) + "\n";
                    ret[1] += pegaValorIdx(valores, 1) + "\n";
                    ret[2] += pegaValorIdx(valores, 2) + "\n";
                    ret[3] += pegaValorIdx(valores, 3) + "\n";
                    ret[4] += pegaValorIdx(valores, 4) + "\n";
                    ret[5] += pegaValorIdx(valores, 5) + "\n";
                    ret[6] += pegaValorIdx(valores, 6) + "\n";
                    ret[7] += pegaValorIdx(valores, 7) + "\n";

                } else {
                    ret[0] += valores[0] + "\n";
                    ret[1] += valores[1] + "\n";
                }
                i++;
            }

            rd.close();

            return ret;
        } catch (Exception e) {
            return new String[]{"ERRO:" + i + "--" + e};
        }
    }

    public String buscaInfo(Vector<String> tsl, int i, String c, int tipo) {
        String ret = "";

        for (int p = i; p <= (i + 10); p++) {
            if (tipo == -3) {
                if (tsl.get(p).toUpperCase().indexOf(c.toUpperCase()) > 0)
                    return tsl.get(p);
            }

            if (tipo == 0) {
                String cmp = "";
                if (c.length() > tsl.get(p).length())
                    cmp = tsl.get(p).trim().toUpperCase();
                else
                    cmp = tsl.get(p).substring(0, c.length()).trim()
                            .toUpperCase();

                if (c.trim().toUpperCase().equals(cmp)) {
                    if ((c.length() + 1) > tsl.get(p).length())
                        return "";
                    else
                        return tsl.get(p).substring(c.length() + 1).trim();
                }

            }
            if (tipo > 0) {
                if (tsl.get(p).trim().toUpperCase().indexOf(c.toUpperCase()) >= 0) {
                    return tsl.get(p + tipo).substring(0);
                }
            }
        }

        return ret;
    }

    public String[] buscarSinopse(String arq_sinopse, String s_programa) {
        String linha = "";
        try {
            String[] ret = new String[]{"", "", "", "", "", "", "", "", "",
                    ""};

            java.io.BufferedReader rd = new java.io.BufferedReader(
                    new java.io.InputStreamReader(new java.io.FileInputStream(
                            "/var/www/xml/" + arq_sinopse), "Cp1252"));
            Vector<String> tsl = new Vector<String>();

            while ((linha = rd.readLine()) != null) {
                if (linha.trim().length() > 0)
                    tsl.addElement(linha.trim());
            }
            rd.close();

            String[] programa = s_programa.split("\n");

            for (int i = 0; i < programa.length; i++) {
                int t = tsl.indexOf(programa[i].trim());

                linha = "" + i;
                if (t > 0) {
                    String cla = buscaInfo(tsl, t, "Classifica", -3);

                    ret[0] += buscaInfo(tsl, t, "DIREÇÃO", 0) + "\n";
                    ret[1] += buscaInfo(tsl, t, "Roteiro:", 0) + "\n";
                    ret[2] += buscaInfo(tsl, t, "Elenco:", 0) + "\n";
                    ret[3] += cla.split(",")[3].trim().substring(16) + "\n";
                    ret[4] += tsl.get(t + 1) + "\n";
                    ret[5] += tsl.get(t) + "\n";
                    ret[6] += cla.split(",")[0] + "\n";
                    ret[7] += cla.split(",")[2] + "\n";
                    ret[8] += buscaInfo(tsl, t, "Elenco:", 1) + "\n";
                    ret[9] += tsl.get(t + 1) + "\n";
                } else {
                    ret[0] += "\n";
                    ret[1] += "\n";
                    ret[2] += "\n";
                    ret[3] += "\n";
                    ret[4] += "\n";
                    ret[5] += "\n";
                    ret[6] += "\n";
                    ret[7] += "\n";
                    ret[8] += "\n";
                    ret[9] += "\n";
                }
            }

            return ret;
        } catch (Exception e) {
            return new String[]{"ERRO:" + e + "-" + linha};
        }
    }

    public String salvar(String p_mesano, String p_canal, String P_HORARIO, String P_PROGRAMA, String P_DIRETOR, String P_ROTEIRO,
                         String P_ELENCO, String P_CLASSIFI, String P_TIT_ORIG,
                         String P_TIT_POR, String P_ANO, String P_GENERO, String P_S_LONGA,
                         String P_S_CURTA) {
        try {
            String[] r_HORARIO = (P_HORARIO + "\n123").split("\n");
            String[] r_PROGRAMA = (P_PROGRAMA + "\n123").split("\n");
            String[] r_DIRETOR = (P_DIRETOR + "\n123").split("\n");
            String[] r_ROTEIRO = (P_ROTEIRO + "\n123").split("\n");
            String[] r_ELENCO = (P_ELENCO + "\n123").split("\n");
            String[] r_CLASSIFI = (P_CLASSIFI + "\n123").split("\n");
            String[] r_TIT_ORIG = (P_TIT_ORIG + "\n123").split("\n");
            String[] r_TIT_POR = (P_TIT_POR + "\n123").split("\n");
            String[] r_ANO = (P_ANO + "\n123").split("\n");
            String[] r_GENERO = (P_GENERO + "\n123").split("\n");
            String[] r_S_LONGA = (P_S_LONGA + "\n123").split("\n");
            String[] r_S_CURTA = (P_S_CURTA + "\n123").split("\n");


            String sql =
                    "insert into sinopse\n" +
                            "  (CANAL,\n" +
                            "   DATA,\n" +
                            "   HORARIO,\n" +
                            "   ANO,\n" +
                            "   NRO_EPISODIO,\n" +
                            "   DURACAO,\n" +
                            "   BLOCO,\n" +
                            "   PROGRAMA,\n" +
                            "   TITULO_ORIGINAL,\n" +
                            "   TITULO_PORTUGUES,\n" +
                            "   SINOPSE_CURTA,\n" +
                            "   SINOPSE_LONGA,\n" +
                            "   DIRETOR,\n" +
                            "   ELENCO,\n" +
                            "   IDIOMA,\n" +
                            "   CLASSIFICACAO,\n" +
                            "   JUSTIFICATIVA,\n" +
                            "   GENERO,\n" +
                            "   ROTEIRO,\n" +
                            "   MESANO)\n" +
                            "values\n" +
                            "  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            java.sql.CallableStatement st = UUtils.con.prepareCall(sql);

            for (int i = 0; i < (r_PROGRAMA.length - 1); i++) {
                st.setString(1, p_canal);
                st.setString(2, "01/" + p_mesano);
                st.setString(3, r_HORARIO[i]);
                st.setString(4, r_ANO[i]);
                st.setString(5, "");
                st.setString(6, "");
                st.setString(7, "");
                st.setString(8, r_PROGRAMA[i]);
                st.setString(9, r_TIT_ORIG[i]);
                st.setString(10, r_TIT_POR[i]);
                st.setString(11, r_S_CURTA[i]);
                st.setString(12, r_S_LONGA[i]);
                st.setString(13, r_DIRETOR[i]);
                st.setString(14, r_ELENCO[i]);
                st.setString(15, "");
                st.setString(16, r_CLASSIFI[i]);
                st.setString(17, "");
                st.setString(18, r_GENERO[i]);
                st.setString(19, r_ROTEIRO[i]);
                st.setString(20, p_mesano);
                st.execute();
            }

            st.close();

            UUtils.con.createStatement().execute("BEGIN PRO_ACERTA_DATA_CORRETA('" + p_canal.toUpperCase() + "', '" + p_mesano + "'); END;");

            return "Inseridos com Sucesso!";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

}
