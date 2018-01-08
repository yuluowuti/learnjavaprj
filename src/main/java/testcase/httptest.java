package testcase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class httptest {
//    get请求
    public String GetRequests(String url,String params)
    {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            if (StringUtils.isNotBlank(params))
                method.setQueryString(URIUtil.encodeQuery(params));
            client.executeMethod(method);
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (URIException e) {
            System.out.println("执行HTTP Get请求时，编码查询字符串“" + params + "”发生异常！");
        } catch (IOException e) {
            System.out.println("执行HTTP Get请求" + url + "时，发生异常！");
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }

    public static String PostRequests(String url, Map params, Map header) {
        StringBuffer response = new StringBuffer();
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        // 创建httppost
//        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
//        // 创建参数队列
//        List formparams = new ArrayList();
//        formparams.add(new BasicNameValuePair("type", "house"));
//        UrlEncodedFormEntity uefEntity;
//        try {
//            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httppost.setEntity(uefEntity);
//            System.out.println("executing request " + httppost.getURI());
//            CloseableHttpResponse response = httpclient.execute(httppost);
//            try {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    System.out.println("--------------------------------------");
//                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
//                    System.out.println("--------------------------------------");
//                }
//            } finally {
//                response.close();
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return response.toString();
    }

//    测试
    public static void main(String[] args) {
        httptest one = new httptest();
        System.out.println(one.GetRequests("https://www.baidu.com/",""));
    }
}
