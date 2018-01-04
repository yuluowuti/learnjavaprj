import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;

//ssh通道连接mysql
public class mysqlConnection {
    static int lport = 5507;//本地端口
    static String rhost = "rm-bp1qb3etnt3zb55oc.mysql.rds.aliyuncs.com";//远程MySQL服务器
    static int rport = 3306;//远程MySQL服务端口

    public static void main(String[] args) {
        String user = "3602d0_root";//SSH连接用户名
        String password = "Yfb111qqq";//SSH连接密码
        String host = "118.31.70.164";//SSH服务器
        int port = 9998;//SSH访问端口

        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:5507", "kld", "Kld123456");
            st = conn.createStatement();
            String sql = "select * from kld_caifu_wealth.users where cellphone='13572489850'";
            rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString(1));
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //rs.close();st.close();conn.close();
        }
    }
}
