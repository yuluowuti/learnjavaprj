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
//        HttpClient client = new HttpClient();
//        PostMethod method=new PostMethod(url);
//        int i;
////封装HTTP请求头
//        if(header != null && header.size()>0){
//            Set key = header.keySet();
//            for (Iterator it = key.iterator(); it.hasNext();) {
//                String s = (String) it.next();
//                method.addRequestHeader(s,header.get(s));
//            }
//        }
////设置Http Post数据
//        if (params != null) {
//            NameValuePair[] postData = new NameValuePair[params.size()];
//            i=0;
//            for (Map.Entry entry : params.entrySet()) {
//                postData[i] = new NameValuePair(entry.getKey(), entry.getValue());
//                i++;
//            }
//            method.addParameters(postData);
//        }
//        try {
//            client.executeMethod(method);
//            if (method.getStatusCode() == HttpStatus.SC_OK) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//            }
//        } catch (IOException e) {
//            System.out.println("执行HTTP Post请求" + url + "时，发生异常！"+e);
//        } finally {
//            method.releaseConnection();
//        }
        return response.toString();
    }

//    测试
    public static void main(String[] args) {
        httptest one = new httptest();
        System.out.println(one.GetRequests("https://www.baidu.com/",""));
    }
}
