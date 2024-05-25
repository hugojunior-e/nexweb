package nexcabo;

import oracle.sql.CLOB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class UUtils {

    public static String path_root;
    public static String path_delimiter;
    public static Connection con = null;


    public static String g_cor = "";

    public static String pegaCor() {
        g_cor = g_cor.equals("#c0c0c0") ? "" : "#c0c0c0";
        return g_cor;
    }


    public static String pegaDataHora() {
        java.util.Date v_agora = new java.util.Date();
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(v_agora);
    }

    public static String pegaDataHora(java.sql.Timestamp d) {
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(d);
    }

    public static java.sql.Timestamp pegaDataHora(String d) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date date = sdf.parse(d);
        java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime());

        return timest;
    }

    /**
     *
     */
    public static String connectNexcabo(HttpServletRequest rq, JspWriter out) {
        String info = "[NEXCABO: OK]";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String s_usuario = "nexcabo";
            String s_senha = "123456";
            String s_base = "200.98.168.231:1521:XE";

            path_delimiter = System.getProperty("file.separator");
            path_root = rq.getRealPath("") + path_delimiter;
            con = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@" + s_base,s_usuario, s_senha);

            info = info + "/" + s_base;
        } catch (Exception exx) {
            info = "[NEXCABO: ERRO: " + exx + "]";
            con = null;
        }
        return info;
    }

    /**
     *
     */
    public static String getValue(Object value) {
        return getValue(value, "");
    }

    /**
     *
     */
    public static String getValue(Object value, String def) {
        return (value == null || value.toString().length() == 0) ? def : value.toString();
    }


    public static String doLinkAction(String valor, String funcao, String id) {
        return "<u><a OnClick=\"" + funcao + "\" style=\"cursor:pointer; color:#999900\" id=" + id + ">" + valor + "</a></u>";
    }

    public static String doLinkAction(String valor, String funcao) {
        return doLinkAction(valor, funcao, "_");
    }


    public static void setStatementValue(java.sql.CallableStatement s, int idx, Object value, int t) throws Exception {
        if (value == null || value.toString().length() == 0)
            s.setNull(idx, java.sql.Types.VARCHAR);
        else {
            if (t == 1) {
                s.setString(idx, value.toString());
            }
        }
    }

    public static void setStatementValue(java.sql.CallableStatement s, int idx, Object value) throws Exception {
        setStatementValue(s, idx, value, 1);
    }


    public static String newFileName(String fn) throws Exception {
        return UUtils.path_root + "temp" + UUtils.path_delimiter + fn;
    }

    /**
     *
     */
    public static CLOB getCLOB(String clobData) throws Exception {
        CLOB tempClob = null;

        try {
            tempClob = CLOB.createTemporary(con, true, CLOB.DURATION_SESSION);
            tempClob.open(CLOB.MODE_READWRITE);
            Writer tempClobWriter = tempClob.getCharacterOutputStream();
            tempClobWriter.write(clobData);
            tempClobWriter.flush();
            tempClobWriter.close();
            tempClob.close();

        } catch (Exception exp) {
            tempClob.freeTemporary();
            System.out.println("Exception thrown in getCLOB method of given status : " + exp.getMessage());
        }
        return tempClob;
    }


    public static void fillCombo(java.sql.Connection con, JspWriter out, String s_SQL) throws Exception {

        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(s_SQL);

        while (rs.next()) {
            out.println("<option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + "</option>");
        }

        rs.close();
        st.close();
    }


    public static String[] preencheDados(ResultSet rs) {
        try {
            int idx = 0;

            String[] retorno = new String[100];

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                    Object o = rs.getObject(i);
                    String d = "";

                    if (o != null) {
                        if (rs.getMetaData().getColumnType(i) == java.sql.Types.CLOB) {
                            d = rs.getClob(i).getSubString((long) 1, (int) rs.getClob(i).length());
                        }
                        if (rs.getMetaData().getColumnType(i) == java.sql.Types.DATE || rs.getMetaData().getColumnType(i) == java.sql.Types.TIMESTAMP) {
                            d = pegaDataHora(rs.getTimestamp(i));
                        } else {
                            d = o.toString();
                        }
                    }
                    retorno[idx++] = d;
                }
            }

            return retorno;
        } catch (Exception exx) {
            return null;
        }
    }
}
