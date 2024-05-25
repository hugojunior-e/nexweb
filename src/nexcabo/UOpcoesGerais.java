package nexcabo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.Statement;

public class UOpcoesGerais {
    public String salvarAddHoras(String valor, String sinopse) {
        try {
            String sql = "update configs set qtd_horas = " + valor + ", tam_sinopse=" + sinopse;
            Statement st = UUtils.con.createStatement();
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
            Statement st = UUtils.con.createStatement();
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
            Statement st = UUtils.con.createStatement();
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
            Statement st = UUtils.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ret = rs.getString(i);
            }
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
            Statement st = UUtils.con.createStatement();
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
            Statement st = UUtils.con.createStatement();
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

            Statement st = UUtils.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String cor = UUtils.pegaCor();
                ret.append("<tr bgcolor=\"" + cor + "\">");
                String b = "<img src=imgs/" + (rs.getInt("ATIVO") == 1 ? "ok.png" : "del.png") + " onclick=\"jsAtivar(this,'" + rs.getString("row_id") + "')\"";

                String jsEdit = "jsEdit(':1', ':2')".replace(":1", rs.getString("SIGLA")).replaceAll(":2", nome_cliente);
                String jsGenero = "jsGenero(':1', ':2')".replace(":1", rs.getString("SIGLA")).replaceAll(":2", nome_cliente);

                ret.append("<td>" + b + "</td>");
                ret.append("<td>" + rs.getString("SIGLA") + "</td>");
                ret.append("<td>" + UUtils.doLinkAction(UUtils.getValue(rs.getString("NRO"), "__"), jsEdit, new StringBuilder().append("rs_id_").append(rs.getString("SIGLA")).toString()) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("CANAL")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("NOME")) + "</td>");

                ret.append("<td>" + UUtils.doLinkAction(UUtils.getValue(rs.getString("GENERO"), "__"), jsGenero, new StringBuilder().append("rs_id_genero_").append(rs.getString("SIGLA")).toString()) + "</td>");
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
            Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);

            ResultSet rs = st.executeQuery("select ativo from nexcabo.t_xml_canais  where rowid='" + row_id + "'");
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
            Statement stx = UUtils.con.createStatement();
            ResultSet rsx = stx.executeQuery("select tipo from t_xml_combo where nome = '<1>'".replaceAll("<1>", p_cliente));
            while (rsx.next()) {
                selectedIndex = rsx.getInt(1);
            }
            rsx.close();
            stx.close();

            String arqName = "";
            String fileName = "";

            if (p_cliente.equals("GRDTEC")) {
                arqName = "t_xml_" + p_cliente.replaceAll(" ", "_").toLowerCase() + ".dat";
                fileName = UUtils.newFileName(arqName);
            } else if (p_cliente.equals("ECAD")) {
                arqName = "t_xml_" + p_cliente.replaceAll(" ", "_").toLowerCase() + ".csv";
                fileName = UUtils.newFileName(arqName);
            } else {
                arqName = "t_xml_" + p_cliente.replaceAll(" ", "_").toLowerCase() + ".xml";
                fileName = UUtils.newFileName(arqName);
            }

            System.setProperty("file.encoding", "Cp1252");

            BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "Cp1252"));
            if ((selectedIndex != 0) ||

                    (selectedIndex == 1)) {
                Statement st = UUtils.con.createStatement();
                ResultSet rs = null;

                String sql = "begin pro_arq_xml('" + p_cliente + "','" + p_mesano + "'," + p_dia1 + "," + p_dia2 + "); end;";
                st.executeUpdate(sql);
                if ((p_cliente.equals("MINERVA")) || (p_cliente.equals("CONECTOR")) || (p_cliente.equals("MULTIPLAY_NA_INTRANET"))) {
                    if ((p_cliente.equals("MINERVA")) || (p_cliente.equals("CONECTOR"))) {
                        String[] arqs = {"progrec.txt", "skedrec.txt", "statrec.txt"};
                        for (int aa = 0; aa < arqs.length; aa++) {
                            rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = '" + arqs[aa] + "' order by 2");
                            while (rs.next()) {
                                fw.write(UUtils.getValue(rs.getString("info1")) + "\n");
                            }
                            fw.write("\n-->" + arqs[aa] + "\n");

                            rs.close();
                        }
                    } else {
                        String[] arqs = {"ul_.chn", "ul_.ppv", "ul_.prg", "ul_.sch"};
                        for (int aa = 0; aa < arqs.length; aa++) {
                            rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = '" + arqs[aa] + "' order by 2");
                            while (rs.next()) {
                                fw.write(UUtils.getValue(rs.getString("info1")) + "\n");
                            }
                            fw.write("\n-->" + arqs[aa] + "\n");

                            rs.close();
                        }
                    }
                } else if (p_cliente.equals("GRDTEC")) {

                    rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' order by 2");
                    while (rs.next()) {
                        fw.write("EXECUTE PROCEDURE PROC_ADDPROG(" + UUtils.getValue(rs.getString("info2")) + ", " + UUtils.getValue(rs.getString("info1")) + ")\n");
                    }

                    //fw.write("\n-->" + arqs[aa] + "\n");
                    rs.close();
                } else if (p_cliente.equals("ECAD")) {
                    rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' order by 2");
                    while (rs.next()) {
                        fw.write(UUtils.getValue(rs.getString("info1")) + "\n");
                    }
                    rs.close();
                } else {
                    String cab = "";
                    String rod = "";

                    rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = 'A#' order by XML_ID");
                    while (rs.next()) {
                        cab = cab + "\n" + UUtils.getValue(rs.getString("info1"));
                    }
                    rs.close();

                    rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = 'B#' order by XML_ID");
                    while (rs.next()) {
                        rod = rod + "\n" + UUtils.getValue(rs.getString("info1"));
                    }
                    rs.close();

                    cab = cab.trim();
                    rod = rod.trim();
                    if (cab.length() == 0) {
                        rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 not in ('A#','B#') order by XML_ID");
                        while (rs.next()) {
                            fw.write(UUtils.getValue(rs.getString("info1")) + "\n");
                        }
                        rs.close();
                    } else {
                        if (cab.equals("-")) {
                            cab = "";
                        }
                        rs = st.executeQuery("select TRANSLATE(canal,'&','e') CANAL, SIGLA from t_xml_canais t where cliente = '" + p_cliente + "' and ativo=1 and tipo = 'XML' order by canal");
                        while (rs.next()) {
                            Statement st2 = UUtils.con.createStatement();
                            ResultSet rs2 = st2.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' and info2 = '" + rs.getString("SIGLA") + "' order by XML_ID");
                            if (cab.length() > 0) {
                                fw.write(cab);
                                fw.write("\n");
                            }
                            while (rs2.next()) {
                                fw.write(UUtils.getValue(rs2.getString("info1")) + "\n");
                            }
                            fw.write(rod);
                            if (p_cliente.equals("SSTV") || p_cliente.equals("TVAC Tiete") || p_cliente.equals("KLINTV")) {

                                if (p_cliente.equals("SSTV")) {
                                    fw.write("\n-->SSTV_" + UUtils.getValue(rs.getString("SIGLA")) + "\n");
                                } else {
                                    ResultSet resultado = null;
                                    Statement stresult = UUtils.con.createStatement();
                                    String canal = UUtils.getValue(rs.getString("CANAL"));

                                    resultado = stresult.executeQuery("select * from t_lista_de_para_canais where canal_original = '" + canal + "' and cliente = '" + p_cliente + "'");
                                    while (resultado.next()) {
                                        fw.write("\n-->" + UUtils.getValue(resultado.getString("CANAL_PARA")) + "\n");
                                    }

                                    resultado.close();
                                    stresult.close();
                                }

                            } else {
                                fw.write("\n-->" + UUtils.getValue(rs.getString("CANAL")) + "\n");
                            }
                            rs2.close();
                            st2.close();

                        }
                        rs.close();
                    }
                }
                st.close();

            }
            if (selectedIndex == 2) {
                Statement st = UUtils.con.createStatement();
                ResultSet rs = null;
                st.executeUpdate("begin pro_arq_ctbc_smtv('" + p_cliente + "','" + p_mesano + "'); end;");

                rs = st.executeQuery("select * from t_xml where cliente = '" + p_cliente + "' order by XML_ID");
                while (rs.next()) {
                    fw.write(UUtils.getValue(rs.getString("info1")) + "\n");
                }
                rs.close();
                st.close();
            }

            fw.close();

            Compress.gzipFile(fileName, fileName + ".gz");

            return "temp/" + arqName + ".gz";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }
}
