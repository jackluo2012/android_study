package net.webjoy.jackluo.android_recyclerview;

import java.util.ArrayList;

/**
 * Created by jackluo on 10/31/15.
 * model
 */
public class DemoApp {

    //获取 要显示的数据
    public static ArrayList<SampleModel> getSampleData(int size){

        ArrayList<SampleModel> sampleData = new ArrayList<SampleModel>(size);
        for (int i=0;i<size;i++){
            //每一项数据 后面都有相应的序号
            sampleData.add(new SampleModel("新的列表项<"+i+">"));
        }
        return sampleData;
    }

}
