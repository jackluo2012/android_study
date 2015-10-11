package net.webjoy.jackluo.android_http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by jackluo on 9/27/15.
 */
public class HttpClientThread extends Thread {

    private String url;
    private String name;
    private String age;

    public HttpClientThread(String url){
        this.url = url;
    }

    public HttpClientThread(String url,String name,String age){
        this.url = url;
        this.name = name;
        this.age = age;
    }

    private void doHttpClientGet(){

        //HttpGet httpGet = new HttpGet();
        HttpGet httpGet = new HttpGet(url);
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String content = EntityUtils.toString(response.getEntity());//
                System.out.println("contnt---------->"+content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void doHttpCLientPost(){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();

        list.add(new BasicNameValuePair("name",name));
        list.add(new BasicNameValuePair("age",age));
        try {
           post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response =  client.execute(post);
            if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

        //doHttpClientGet();
        doHttpCLientPost();
    }
}
