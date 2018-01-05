import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.sql.*;

import static java.sql.DriverManager.*;

public class javasamplerTest extends AbstractJavaSamplerClient{
    private SampleResult sampleresult = new SampleResult();

    int lport = 5507;//本地端口
    String rhost = "rm-bp1qb3etnt3zb55oc.mysql.rds.aliyuncs.com";//远程MySQL服务器
    int rport = 3306;//远程MySQL服务端口
    String user = "3602d0_root";//SSH连接用户名
    String password = "Yfb111qqq";//SSH连接密码
    String host = "118.31.70.164";//SSH服务器
    int port = 9998;//SSH访问端口
    String datauser = "kld";//数据库连接用户名
    String datapassword = "Kld123456";//数据库连接密码

    String sql="";

    JSch jsch = new JSch();
    Session session = null;

    //把测试的一些默认数据在程序运行前显示到JMeter客户端
    @Override
    public Arguments getDefaultParameters()
    {
        Arguments params = new Arguments();
        params.addArgument("lport","value1");
        params.addArgument("rhost","value2");
        params.addArgument("rport","value3");

        params.addArgument("user","value4");
        params.addArgument("password","value5");
        params.addArgument("host","value6");
        params.addArgument("port","value7");
        params.addArgument("sql","value8");

        params.addArgument("datauser","value9");
        params.addArgument("datapassword","value10");
        return params;
    }
    //子类用它来 记录log
    protected org.apache.log.Logger getLogger()
    {
        return null;
    }
    //初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
    public void setupTest(JavaSamplerContext context)
    {
        //获取界面中传递的值
        lport = Integer.parseInt(context.getParameter("lport"));
        rhost = context.getParameter("rhost");
        rport = Integer.parseInt(context.getParameter("rport"));
        user = context.getParameter("user");
        password = context.getParameter("password");
        host = context.getParameter("host");
        port = Integer.parseInt(context.getParameter("port"));
        datauser = context.getParameter("datauser");
        datapassword = context.getParameter("datapassword");
        sql = context.getParameter("sql");

        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
    //测试执行的循环体，根据线程数和循环次数的不同可执行多次
    public SampleResult runTest(JavaSamplerContext context) {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        SampleResult sr = new SampleResult();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sr.setSamplerData("请求参数url："+"jdbc:mysql://localhost:5507");
            conn = getConnection("jdbc:mysql://localhost:5507", datauser, datapassword);
            st = conn.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultData ="id=" + rs.getString(1) + ",userIdentifier=" + rs.getString(2);
                System.out.println(resultData);
                sr.setResponseData("结果是：" + resultData, null);
                sr.setDataType(SampleResult.TEXT);
            }
            Thread.sleep(10);
            sr.setSuccessful(true);
        } catch (Exception e) {
            sr.setSuccessful(false);
            e.printStackTrace();
        } finally {
            //rs.close();st.close();conn.close();
        }
        return sr;
    }

    //结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
    public void teardownTest(JavaSamplerContext context)
    {
        session.disconnect();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Arguments params = new Arguments();
        params.addArgument("lport","5507");
        params.addArgument("rhost","rm-bp1qb3etnt3zb55oc.mysql.rds.aliyuncs.com");
        params.addArgument("rport","3306");

        params.addArgument("user","3602d0_root");
        params.addArgument("password","Yfb111qqq");
        params.addArgument("host","118.31.70.164");
        params.addArgument("port","9998");
        params.addArgument("sql","select * from kld_caifu_wealth.users where cellphone ='13572489850';");

        params.addArgument("datauser","kld");
        params.addArgument("datapassword","Kld123456");

        JavaSamplerContext arg0 = new JavaSamplerContext(params);
        javasamplerTest test = new javasamplerTest();
        test.setupTest(arg0);
        test.runTest(arg0);
        test.teardownTest(arg0);
    }
}
