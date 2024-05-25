package nexcabo;


public class UOpcoesGerais1 {

    public String salvarAddHoras(String valor, String sinopse) {
        try {
            String sql = "update configs set qtd_horas = " + valor + ", tam_sinopse=" + sinopse;
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Salvo com Sucesso";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String alterarNumeroCanal(String sigla, String cliente, String canal) {
        try {
            String sql = "update t_xml_canais set nro = '<1>' where cliente = '<2>' and sigla = '<3>'".replaceAll("<1>", canal).replaceAll("<2>", cliente).replaceAll("<3>", sigla);
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Salvo com Sucesso";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }


    public String alterarGenero(String sigla, String cliente, String genero) {
        try {
            String sql = "update t_xml_canais set genero = '<1>' where cliente = '<2>' and sigla = '<3>'".replaceAll("<1>", genero).replaceAll("<2>", cliente).replaceAll("<3>", sigla);
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Salvo com Sucesso";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }


    public String pegaConfig(int i) {
        try {
            String ret = "";
            String sql = "select * from configs";
            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                ret = rs.getString(i);
            rs.close();
            st.close();

            return ret;
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }


    public String duplicar(String origem, String destino) {
        try {
            String sql = "begin pro_duplicar_cliente('<1>','<2>'); end;".replaceAll("<1>", origem).replaceAll("<2>", destino);
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Feito Com sucesso";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String apagar(String origem) {
        try {
            String sql = "begin delete t_xml_combo where nome = '<1>'; delete t_xml_canais where cliente='<1>'; end;".replaceAll("<1>", origem);
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Feito Com sucesso";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String mostraFiltroCliente(String nome_cliente) {
        try {
            String sql = "select t.*, rowid row_id from nexcabo.t_xml_canais t where cliente = '<1>' and tipo = 'XML' order by canal";
            StringBuilder ret = new StringBuilder();

            sql = sql.replaceAll("<1>", nome_cliente);

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String cor = UUtils.pegaCor();
                ret.append("<tr bgcolor=\"" + cor + "\">");
                String b = "<img src=imgs/" + (rs.getInt("ATIVO") == 1 ? "ok.png" : "del.png") + " onclick=\"jsAtivar(this,'" + rs.getString("row_id") + "')\"";

                String jsEdit = "jsEdit(':1', ':2')".replace(":1", rs.getString("SIGLA")).replaceAll(":2", nome_cliente);
                String jsGenero = "jsGenero(':1', ':2')".replace(":1", rs.getString("SIGLA")).replaceAll(":2", nome_cliente);

                ret.append("<td>" + b + "</td>");
                ret.append("<td>" + rs.getString("SIGLA") + "</td>");
                ret.append("<td>" + UUtils.doLinkAction(UUtils.getValue(rs.getString("NRO"), "__"), jsEdit, "rs_id_" + rs.getString("SIGLA")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("CANAL")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("NOME")) + "</td>");
                //ret.append("<td>" + UUtils.getValue(rs.getString("GENERO")) + "</td>");
                ret.append("<td>" + UUtils.doLinkAction(UUtils.getValue(rs.getString("GENERO"), "__"), jsGenero, "rs_id_genero_" + rs.getString("SIGLA")) + "</td>");
                ret.append("</tr>");
            }
            rs.close();
            st.close();

            return ret.substring(0);
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String ativarDesativar(String row_id) {
        try {
            String sql = "update nexcabo.t_xml_canais t set ativo = 1-ativo where rowid='" + row_id + "'";
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);

            java.sql.ResultSet rs;
            rs = st.executeQuery("select ativo from nexcabo.t_xml_canais  where rowid='" + row_id + "'");
            rs.next();
            String ret = rs.getInt("ATIVO") == 1 ? "imgs/ok.png" : "imgs/del.png";

            rs.close();
            st.close();

            return ret;
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String gerarArquivoXML(String p_cliente, String p_mesano, String p_dia1, String p_dia2) {
        try {
            int selectedIndex = 0;
            java.sql.Statement stx = UUtils.con.createStatement();
            java.sql.ResultSet rsx = stx.executeQuery("select tipo from t_xml_combo where nome = '<1>'".replaceAll("<1>", p_cliente));

            while (rsx.next())
                selectedIndex = rsx.getInt(1);

            rsx.close();
            stx.close();


            String arqName = "t_xml_" + p_cliente.replaceAll(" ", "_").toLowerCase() + ".xml";
            String fileName = UUtils.newFileName(arqName);

            System.setProperty("file.encoding", "Cp1252");

            java.io.BufferedWriter fw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(fileName), "Cp1252"));

            // ------------------------------------------------------------------------------------------------------------------------------

            if (selectedIndex == 0) {
                // pro_gerar_xml_netangra
            }

            // ------------------------------------------------------------------------------------------------------------------------------

            if (selectedIndex == 1) {
                java.sql.Statement st = UUtils.con.createStatement();
                java.sql.ResultSet rs = null;

                String sql = "begin pro_arq_xml('" + p_cliente + "','" + p_mesano + "'," + p_dia1 + "," + p_dia2 + "); end;";
                st.executeUpdate(sql);

                if (p_cliente.equals("MINERVA") || p_cliente.equals("CONECTOR") || p_cliente.equals("Multiplay_na_Intranet")) {
                    if (p_cliente.equals("MINERVA") || p_cliente.equals("CONECTOR")) {

                        String[] arqs = new String[]{"progrec.txt", "skedrec.txt", "statrec.txt"};

                        for (int aa = 0; aa < arqs.length; aa++) {
                            rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = '" + arqs[aa] + "' order by 2");

                            while (rs.next())
                                fw.write(UUtils.getValue(rs.getString("info1")) + "\n");

                            fw.write("\n-->" + arqs[aa] + "\n");

                            rs.close();
                        }
                    } else {
                        String[] arqs = new String[]{"progrec.txt", "skedrec.txt", "statrec.txt"};

                        for (int aa = 0; aa < arqs.length; aa++) {
                            rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = '" + arqs[aa] + "' order by 2");

                            while (rs.next())
                                fw.write(UUtils.getValue(rs.getString("info1")) + "\n");

                            fw.write("\n-->" + arqs[aa] + "\n");

                            rs.close();
                        }
                    }
                } else {
                    String cab = "";
                    String rod = "";


                    rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = 'A#' order by XML_ID");

                    while (rs.next())
                        cab += "\n" + UUtils.getValue(rs.getString("info1"));

                    rs.close();

                    rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = 'B#' order by XML_ID");

                    while (rs.next())
                        rod += "\n" + UUtils.getValue(rs.getString("info1"));

                    rs.close();

                    cab = cab.trim();
                    rod = rod.trim();


                    if (cab.length() == 0) {
                        rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 not in ('A#','B#') order by XML_ID");
                        while (rs.next())
                            fw.write(UUtils.getValue(rs.getString("info1")) + "\n");

                        rs.close();
                    } else {
                        if (cab.equals("-"))
                            cab = "";

                        rs = st.executeQuery("select * from t_xml_canais t where cliente = '" + p_cliente + "' and ativo=1 and tipo = 'XML' order by canal");

                        while (rs.next()) {
                            java.sql.Statement st2 = UUtils.con.createStatement();
                            java.sql.ResultSet rs2 = st2.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = '" + rs.getString("SIGLA") + "' order by XML_ID");

                            if (cab.length() > 0) {
                                fw.write(cab);
                                fw.write("\n");
                            }

                            while (rs2.next())
                                fw.write(UUtils.getValue(rs2.getString("info1")) + "\n");

                            fw.write(rod);
                            fw.write("\n-->" + UUtils.getValue(rs.getString("SIGLA")) + "\n");

                            rs2.close();
                            st2.close();
                        }

                        rs.close();
                    }

                }
                st.close();
            }

            // ------------------------------------------------------------------------------------------------------------------------------

            if (selectedIndex == 2) {

                java.sql.Statement st = UUtils.con.createStatement();
                java.sql.ResultSet rs = null;
                st.executeUpdate("begin pro_arq_ctbc_smtv('" + p_cliente + "','" + p_mesano + "'); end;");

                rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' order by XML_ID");

                while (rs.next())
                    fw.write(UUtils.getValue(rs.getString("info1")) + "\n");

                rs.close();
                st.close();
            }

            // ------------------------------------------------------------------------------------------------------------------------------

            fw.close();

            Compress.gzipFile(fileName, fileName + ".gz");

            return "temp/" + arqName + ".gz";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

}
