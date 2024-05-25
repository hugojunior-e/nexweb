package nexcabo;

public class UCanais {
    public String listaCanais() {
        try {
            String sql = "select * from t_siglas_canais order by ordem_atu";
            StringBuilder ret = new StringBuilder();

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String cor = UUtils.pegaCor();
                ret.append("<tr bgcolor=\"" + cor + "\" id=" + rs.getString("SIGLA") + ">");
                String b = "<img src=imgs/cal_plus.png onclick=\"jsMover('" + rs.getString("SIGLA") + "',-1)\">" +
                        "<img src=imgs/cal_minus.png onclick=\"jsMover('" + rs.getString("SIGLA") + "', 1)\">" +
                        "<img src=imgs/ok.png    onclick=\"jsAtivar('" + rs.getString("SIGLA") + "')\">" +
                        "<img src=imgs/Minus.png onclick=\"jsApagar('" + rs.getString("SIGLA") + "')\">";

                ret.append("<td>" + b + "</td>");

                ret.append("<td>" + UUtils.getValue(rs.getString("SIGLA")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("NOME_CANAL")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("CONTATO")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("TELEFONE")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("EMAIL")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("SITE")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("USUARIO")) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("SENHA")) + "</td>");
                ret.append("</tr>");
            }
            rs.close();
            st.close();

            return ret.substring(0);
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String moverRegistro(String p_sigla, String p_ordem) {
        try {
            String sql = "begin pr_atualiza_ordem('<1>','<2>'); end;".replaceAll("<1>", p_sigla).replaceAll("<2>", p_ordem);

            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            return "Feito Com Sucesso!";

        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String listarControleCanais(String p_periodo) {
        try {
            String sql =
                    "select a.SIGLA,NOME_CANAL,\n" +
                            "       TO_CHAR(aa.CRIADO_EM, 'dd/mm/yyyy hh24:mi') DTT,\n" +
                            "       aa.STATUS\n" +
                            "  from t_siglas_canais A,\n" +
                            "       (SELECT * FROM t_ctrl_atualizacoes c WHERE c.PERIODO = '" + p_periodo + "') AA\n" +
                            " WHERE AA.canal(+) = A.sigla\n" +
                            " order by ORDEM_ATU";


            StringBuilder ret = new StringBuilder();

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String b = "";
                if (UUtils.getValue(rs.getString("STATUS")).length() == 0)
                    b = "<img src=imgs/ok.png onclick=\"jsMarcarControle('" + rs.getString("SIGLA") + "','" + p_periodo + "',1)\">";
                else
                    b = "<img src=imgs/Minus.png onclick=\"jsMarcarControle('" + rs.getString("SIGLA") + "','" + p_periodo + "',2)\">";

                String cor = UUtils.pegaCor();
                ret.append("<tr bgcolor=\"" + cor + "\">");
                ret.append("<td>" + b + "</td>");

                ret.append("<td>" + rs.getString("DTT") + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString("NOME_CANAL")) + "</td>");
                ret.append("</tr>");
            }
            rs.close();
            st.close();

            return ret.substring(0);
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String marcarControle(String g_sigla, String periodo, String flag) {
        try {
            String sql = "begin pro_ctrl_atualiza('<1>','<2>','<3>'); end;".replaceAll("<1>", g_sigla).replaceAll("<2>", periodo).replaceAll("<3>", flag);

            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            return "Feito Com Sucesso!";

        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String apagarCanal(String g_sigla) {
        try {
            String sql = "delete  nexcabo.t_siglas_canais where SIGLA='<1>'".replaceAll("<1>", g_sigla);
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            return "Feito Com Sucesso!";

        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String salvarCanal(String g_sigla, String SIGLA, String NOME_CANAL, String CONTATO, String TELEFONE, String EMAIL, String SITE, String USUARIO, String SENHA) {
        try {
            if (g_sigla.equals("---")) {
                //String sql = "insert into nexcabo.t_siglas_canais(SIGLA,NOME_CANAL,ENDERECO,OBSERVACAO,PROGRAMA) values('<1>','<2>','<3>','<4>','<5>')";
                String sql = "insert into nexcabo.t_siglas_canais(SIGLA,NOME_CANAL,CONTATO,TELEFONE,EMAIL,SITE,USUARIO,SENHA) values('<1>','<2>','<3>','<4>','<5>','<6>','<7>','<8>')";
                sql = sql.replaceAll("<1>", SIGLA);
                sql = sql.replaceAll("<2>", NOME_CANAL);
                sql = sql.replaceAll("<3>", CONTATO);
                sql = sql.replaceAll("<4>", TELEFONE);
                sql = sql.replaceAll("<5>", EMAIL);
                sql = sql.replaceAll("<6>", SITE);
                sql = sql.replaceAll("<7>", USUARIO);
                sql = sql.replaceAll("<8>", SENHA);

                java.sql.Statement st = UUtils.con.createStatement();
                st.executeUpdate(sql);

            } else {
                String sql = "update nexcabo.t_siglas_canais t set SIGLA = '<1>',NOME_CANAL = '<2>',CONTATO = '<3>',TELEFONE = '<4>',EMAIL = '<5>',SITE = '<6>',USUARIO = '<7>', SENHA = '<8>' where sigla='" + g_sigla + "'";
                sql = sql.replaceAll("<1>", SIGLA);
                sql = sql.replaceAll("<2>", NOME_CANAL);
                sql = sql.replaceAll("<3>", CONTATO);
                sql = sql.replaceAll("<4>", TELEFONE);
                sql = sql.replaceAll("<5>", EMAIL);
                sql = sql.replaceAll("<6>", SITE);
                sql = sql.replaceAll("<7>", USUARIO);
                sql = sql.replaceAll("<8>", SENHA);

                java.sql.Statement st = UUtils.con.createStatement();
                st.executeUpdate(sql);

            }
            return "Feito Com Sucesso!";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }
}
