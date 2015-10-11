package net.webjoy.jackluo.android_json;

import android.os.Environment;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jackluo on 10/1/15.
 */
public class UploadThread extends Thread {

    private String fileName;
    private String url;


    public UploadThread(String url,String fileName){
        this.url = url;
        this.fileName = fileName;
    }

    private void httpUpload(){
        String boundary="----WebKitFormBoundaryVGJlKwp57PmC5Lvm";
        String prefix="--";
        String end="\r\n";
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("POST");//向服务器发送实体数据
            connection.setDoOutput(true);//向服务器发起数据
            connection.setDoInput(true);//向服务器读取数据
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(prefix+boundary+end);
            outputStream.writeBytes("Content-Disposition: form-data; "+"name=\"filename\"; filename=\""+"g3.png"+"\""+end);
            outputStream.writeBytes(end);

            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            byte[] b = new byte[1024*4];
            int len;
            while ((len=fileInputStream.read(b))!=1){
                outputStream.write(b,0,len);
            }
            outputStream.writeBytes(end);
            outputStream.writeBytes(prefix + boundary + prefix + end);
            outputStream.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine())!=null){
                sb.append(str);
            }
            System.out.println("response:"+sb.toString());
            if (outputStream !=null){
                outputStream.close();
            }
            if (reader!=null){
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadHttpClient(){
        HttpClient client = new DefaultHttpClient();
        HttpPost post= new HttpPost(url);
        MultipartEntity multi = new MultipartEntity();
        File parent = Environment.getExternalStorageDirectory();
        File fileAbs = new File(parent,"");

        FileBody fileBody = new FileBody(fileAbs);
        multi.addPart("file", fileBody);

        post.setEntity(multi);

        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {


    }
}
