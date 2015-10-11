package net.webjoy.jackluo.android_json;

import java.util.List;

/**
 * Created by jackluo on 9/28/15.
 */
public class Person {
    private String name;
    private int age;
    private String url;
    private List<SchoolInfo> schoolInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SchoolInfo> getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(List<SchoolInfo> schoolInfo) {
        this.schoolInfo = schoolInfo;
    }
}
