package net.webjoy.jackluo.android_json;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackluo on 9/28/15.
 */
public class TestJson {
    public static void main(String[] args){
        Result result = new Result();
        result.setResult(1);
        List<Person> list = new ArrayList<Person>();
        result.setPersonData(list);
        Person person1 = new Person();
        person1.setName("jackluo");
        person1.setAge(26);
        person1.setUrl("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1320856528,3201437244&fm=58");
        List<SchoolInfo> schoolInfos = new ArrayList<SchoolInfo>();

        SchoolInfo schoolInfo = new SchoolInfo();
        schoolInfo.setSchool_name("清华");
        SchoolInfo schoolInfo1 = new SchoolInfo();
        schoolInfo.setSchool_name("北大");
        schoolInfos.add(schoolInfo);
        schoolInfos.add(schoolInfo1);
        person1.setSchoolInfo(schoolInfos);



        Person person2 = new Person();
        person1.setName("Mike");
        person1.setAge(23);
        person1.setUrl("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1320856528,3201437244&fm=58");
        List<SchoolInfo> schoolInfos2 = new ArrayList<SchoolInfo>();

        SchoolInfo schoolInfo2 = new SchoolInfo();
        schoolInfo1.setSchool_name("人大");
        SchoolInfo schoolInfo3 = new SchoolInfo();
        schoolInfo2.setSchool_name("大大");
        schoolInfos.add(schoolInfo2);
        schoolInfos.add(schoolInfo3);
        person2.setSchoolInfo(schoolInfos);

        list.add(person1);
        list.add(person2);
        Gson gson = new Gson();


        System.out.println(gson.toJson(result));
    }
}
