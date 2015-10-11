package net.webjoy.jackluo.android_json;

import java.util.List;

/**
 * Created by jackluo on 9/28/15.
 */
public class Result {
    private int result;
    private List<Person> personData;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Person> getPersonData() {
        return personData;
    }

    public void setPersonData(List<Person> personData) {
        this.personData = personData;
    }
}
