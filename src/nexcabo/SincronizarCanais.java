package nexcabo;

public class SincronizarCanais {
    public String mostrar() {
        try {
            String sql = "select * from t_atualizacoes order by 1";
            StringBuilder ret = new StringBuilder();

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ret.append("<tr id=" + rs.getString(3) + ">");
                ret.append("<td>" + UUtils.getValue(rs.getString(1)) + "</td>");
                ret.append("<td>" + UUtils.getValue(rs.getString(2)) + "</td>");
                ret.append("<td>" + UUtils.doLinkAction("apagar", "jsApagar(" + rs.getString(3) + ")") + "</td>");
                ret.append("</tr>");
            }
            rs.close();
            st.close();

            return ret.substring(0);
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String salvar(String c1, String c2) {
        try {
            String sql = "insert into t_atualizacoes values ('" + c1 + "','" + c2 + "',t_atualizacoes_seq.nextval) ";
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Feito";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }

    public String apaga(String c1) {
        try {
            String sql = "delete t_atualizacoes where ordem=" + c1;
            java.sql.Statement st = UUtils.con.createStatement();
            st.executeUpdate(sql);
            st.close();

            return "Feito";
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }
}
