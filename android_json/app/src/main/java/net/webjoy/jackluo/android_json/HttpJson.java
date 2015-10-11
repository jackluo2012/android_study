package net.webjoy.jackluo.android_json;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackluo on 9/28/15.
 */
public class HttpJson extends Thread {

    private String url;
    private Context context;

    private ListView listView;

    private JsonAdapter jsonAdapter;

    private Handler handler;

    public HttpJson(String url,ListView listView,JsonAdapter jsonAdapter,Handler handler){
        this.listView = listView;
        this.jsonAdapter = jsonAdapter;
        this.url = url;
//        this.context = context;
        this.handler = handler;
    }


    @Override
    public void run() {
        URL httpUrl = null;
        try {
            httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
             final List<Person> data = parseJson(sb.toString());

            //往主线程发送消息
            handler.post(new Runnable() {

                @Override
                public void run() {
                    jsonAdapter.setData(data);
                    listView.setAdapter(jsonAdapter);
                }
            });



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private List<Person>  parseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<Person> persons = new ArrayList<Person>();
            int result = jsonObject.getInt("result");
            if (result ==1){
                JSONArray personData = jsonObject.getJSONArray("personData");

                for(int i=0;i<personData.length();i++){

                    Person personObject = new Person();
                    persons.add(personObject);
                    JSONObject person = (JSONObject) personData.get(i);
                    String name = person.getString("name");
                    int age = person.getInt("age");
                    String url = person.getString("url");
                    personObject.setName(name);
                    personObject.setAge(age);
                    personObject.setUrl(url);
                    JSONArray schoolInfos = person.getJSONArray("schoolInfo");
                    List<SchoolInfo> schInfos =new ArrayList<SchoolInfo>();
                    personObject.setSchoolInfo(schInfos);
                    for (int j=0;j<schoolInfos.length();j++){
                        JSONObject school = schoolInfos.getJSONObject(j);
                        String schoolName = school.getString("school_name");
                        SchoolInfo schoolInfo = new SchoolInfo();
                        schoolInfo.setSchool_name(schoolName);
                        schInfos.add(schoolInfo);
                    }
                }
                return persons;
            }else{
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    //    Gson gson = new Gson();
        return null;
    }
}
