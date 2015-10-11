package net.webjoy.jackluo.android_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jackluo on 9/4/15.
 */
public class Fragment1 extends android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.view1,container,false);

//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
