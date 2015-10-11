package net.webjoy.jackluo.picexec;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by jackluo on 8/13/15.
 */
public class ImageDetailFragment extends Fragment{
    private static final String IMAGE_DAT_EXTRA = "resId";
    private int mImageNum;
    private ImageView mImageView;
    static ImageDetailFragment newInstance(int mImageNum) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_DAT_EXTRA, mImageNum);
        f.setArguments(args);
        return f;
    }
    public ImageDetailFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageNum = getArguments() != null ?getArguments().getInt(IMAGE_DAT_EXTRA) : -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(android.R.layout.image_detail_fragment,container,false);
    }
}
