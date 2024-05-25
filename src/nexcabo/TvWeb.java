package nexcabo;

public class TvWeb {


    public String listaTvWeb(String canal, String diaMesAno) {
        try {
            String sql = "select to_char(data_correta,'HH24:MI') a, programa b, sinopse_longa c from sinopse where to_char(data_correta,'DD/MM/YYYY') = '" + diaMesAno + "' and canal = '" + canal + "' order by data_correta";

            StringBuilder ret = new StringBuilder();

            java.sql.Statement st = UUtils.con.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String texto = UUtils.getValue(rs.getString("c"));
                String[] dados = texto.split(" ");
                texto = "";

                for (int i = 0; i < dados.length; i++) {
                    texto += dados[i] + (i % 10 == 0 ? "<br>" : " ");
                }
                ret.append("<tr>");
                ret.append("<td>" + UUtils.getValue(rs.getString("a")) + "</td>");
                ret.append("<td onmouseover=\"Tip('" + texto + "')\" onmouseout=\"UnTip()\" >" + UUtils.getValue(rs.getString("b")) + "</td>");
                ret.append("</tr>");
            }
            rs.close();
            st.close();

            return ret.substring(0);
        } catch (Exception e) {
            return "ERRO:" + e;
        }
    }
}
