package net.webjoy.jackluo.android_http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by jackluo on 9/27/15.
 */
public class HttpThreadRegister extends Thread {
    String url;
    String name;
    String age;

    public HttpThreadRegister(String url,String name, String age){
        this.url = url;
        this.name = name;
        this.age = age;
    }

    /**
     * 通过Get 请求的方式
     */
    public void doGet(){
        try {
            url = url +"?name="+ URLEncoder.encode(name, "utf-8")+"&age"+age;
            URL httpUrl = null;
            httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str=null;
            StringBuffer sb = new StringBuffer();

            while ((str = reader.readLine())!=""){
                sb.append(str);
            }
            System.out.println("result:"+sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Post 请求的方式
     */
    public void doPost(){
        try {
            Properties properties = System.getProperties();
            properties.list(System.out);

            URL httpUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            //向服务端发送数据
            OutputStream out = connection.getOutputStream();
            String content = "name="+name +"&age"+age;
            //将字符转换成字节
            out.write(content.getBytes());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str =reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println(sb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        doGet();
    }
}
