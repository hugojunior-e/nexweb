package nexcabo;


public class USinopse {

    public String mostraDadosFiltro(String dia1, String dia2, String canal, String mesano) {
        try {
            String sql = "select * from sinopse where canal='<1>' and data_correta >= to_date('<2>','DD/MM/YYYY') and data_correta < to_date('<3>','DD/MM/YYYY') + 1 order by data_correta";
            String ret = "";

            sql = sql.replaceAll("<1>", canal.toUpperCase());
            sql = sql.replaceAll("<2>", dia1 + "/" + mesano);
            sql = sql.replaceAll("<3>", dia2 + "/" + mesano);

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String cor = UUtils.pegaCor();

                ret += "<tr bgcolor=\"" + cor + "\" id=linha" + rs.getString("SINOPSE_ID") + ">";
                ret += "<td>" + UUtils.doLinkAction("novo", "jsNovo()") + " - "
                        + UUtils.doLinkAction("altera", "jsEdita(" + rs.getString("SINOPSE_ID") + ")") + " - "
                        + UUtils.doLinkAction("del", "jsApaga(" + rs.getString("SINOPSE_ID") + ")") + "</td>";

                ret += "<td>" + UUtils.pegaDataHora(rs.getTimestamp("DATA_CORRETA")) + "</td>";
                ret += "<td>" + UUtils.getValue(rs.getString("PROGRAMA")) + "</td>";
                ret += "<td>" + UUtils.getValue(rs.getString("SINOPSE_LONGA")) + "</td>";
                ret += "<td align=center>" + UUtils.getValue(rs.getString("CLASSIFICACAO")) + "</td>";
                ret += "<td>" + UUtils.getValue(rs.getString("TITULO_ORIGINAL")) + "</td>";
                ret += "</tr>";
            }
            rs.close();
            st.close();

            return ret;
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String[] mostraRegistro(String s_sinopse_id) {
        try {
            String sql = "select * from sinopse where sinopse_id=" + s_sinopse_id;


            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            String[] ret = UUtils.preencheDados(rs);

            rs.close();
            st.close();

            return ret;
        } catch (Exception e) {
            return new String[]{"ERRO:" + e};
        }
    }

    public String salvarRegistro(int i_sinopse_id, String s_canal, String s_data, String s_horario, String s_ano, String s_nro_episodio, String s_duracao,
                                 String s_bloco, String s_programa, String s_titulo_original, String s_titulo_portugues, String s_sinopse_curta, String s_sinopse_longa,
                                 String s_diretor, String s_elenco, String s_idioma, String s_classificacao, String s_justificativa, String s_genero, String s_roteiro,
                                 String s_mesano, String s_data_correta) {
        try {
            String sql_insert =
                    "insert into sinopse\n"
                            + "  (canal,\n"
                            + "   data,\n"
                            + "   horario,\n"
                            + "   ano,\n"
                            + "   nro_episodio,\n"
                            + "   duracao,\n"
                            + "   bloco,\n"
                            + "   programa,\n"
                            + "   titulo_original,\n"
                            + "   titulo_portugues,\n"
                            + "   sinopse_curta,\n"
                            + "   sinopse_longa,\n"
                            + "   diretor,\n"
                            + "   elenco,\n"
                            + "   idioma,\n"
                            + "   classificacao,\n"
                            + "   justificativa,\n"
                            + "   genero,\n"
                            + "   roteiro,\n"
                            + "   mesano,\n"
                            + "   data_correta)\n"
                            + "values\n"
                            + "  (?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?)";

            String sql_update =
                    "update sinopse\n" +
                            "   set canal            = ?,\n" +
                            "       data             = ?,\n" +
                            "       horario          = ?,\n" +
                            "       ano              = ?,\n" +
                            "       nro_episodio     = ?,\n" +
                            "       duracao          = ?,\n" +
                            "       bloco            = ?,\n" +
                            "       programa         = ?,\n" +
                            "       titulo_original  = ?,\n" +
                            "       titulo_portugues = ?,\n" +
                            "       sinopse_curta    = ?,\n" +
                            "       sinopse_longa    = ?,\n" +
                            "       diretor          = ?,\n" +
                            "       elenco           = ?,\n" +
                            "       idioma           = ?,\n" +
                            "       classificacao    = ?,\n" +
                            "       justificativa    = ?,\n" +
                            "       genero           = ?,\n" +
                            "       roteiro          = ?,\n" +
                            "       mesano           = ?,\n" +
                            "       data_correta     = ?\n" +
                            " where sinopse_id = ?";


            java.sql.CallableStatement st = UUtils.con.prepareCall(i_sinopse_id == -1 ? sql_insert : sql_update);

            st.setString(1, s_canal.toUpperCase());
            st.setString(2, s_data);
            st.setString(3, s_horario);
            st.setString(4, s_ano);
            st.setString(5, s_nro_episodio);
            st.setString(6, s_duracao);
            st.setString(7, s_bloco);
            st.setString(8, s_programa);
            st.setString(9, s_titulo_original);
            st.setString(10, s_titulo_portugues);
            st.setString(11, s_sinopse_curta);
            st.setString(12, s_sinopse_longa);
            st.setString(13, s_diretor);
            st.setString(14, s_elenco);
            st.setString(15, s_idioma);
            st.setString(16, s_classificacao);
            st.setString(17, s_justificativa);
            st.setString(18, s_genero);
            st.setString(19, s_roteiro);
            st.setString(20, s_mesano);
            st.setTimestamp(21, UUtils.pegaDataHora(s_data_correta));
            if (i_sinopse_id >= 0)
                st.setInt(22, i_sinopse_id);
            st.execute();
            st.close();

            return "OK";
        } catch (Exception e) {
            return "ERRO:" + e;
        }

    }


    public String apagarRegistro(int i_sinopse_id) {
        try {
            String sql_delete =
                    "delete from sinopse where sinopse_id=" + i_sinopse_id;


            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql_delete);
            st.close();

            return "OK";
        } catch (Exception e) {
            return "ERRO:" + e;
        }

    }


    public String criarRepeteco(String canal, String mesano) {
        try {
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate("begin pro_copia_mes('<1>','<2>'); end;".replaceAll("<1>", canal).replaceAll("<2>", mesano));
            st.close();

            return "OK";
        } catch (Exception e) {
            return "ERRO:" + e;
        }

    }

}
