package net.webjoy.jackluo.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jackluo on 9/6/15.
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean>mlist;
    private LayoutInflater minflater;
    private long mSumTime;

    public MyAdapter(Context context,List<ItemBean> list){
        this.mlist = list;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回每一项显示的内容
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // 逗比模式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //将xml文件转换为view
/*
//        View view = minflater.inflate(R.layout.item,null);
//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
//        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
//        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
//        ItemBean itemBean = mlist.get(position);
//        imageView.setImageResource(itemBean.ItemImageResId);
//        tvTitle.setText(itemBean.ItemTitle);
//        tvContent.setText(itemBean.ItemContent);
*/
        // 逗比模式 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

/*
        //普通模式  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if (convertView == null){
            convertView = minflater.inflate(R.layout.item,null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tv_content);
        ItemBean itemBean = mlist.get(position);
        imageView.setImageResource(itemBean.ItemImageResId);
        tvTitle.setText(itemBean.ItemTitle);
        tvContent.setText(itemBean.ItemContent);
        return convertView;
        //普通模式  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
*/
        //文艺模式  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        long start = System.nanoTime();//获取系统的纳秒时间

        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = minflater.inflate(R.layout.item,null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content =(TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean itemBean = mlist.get(position);
        viewHolder.image.setImageResource(itemBean.ItemImageResId);
        viewHolder.title.setText(itemBean.ItemTitle);
        viewHolder.content.setText(itemBean.ItemContent);
        long end = System.nanoTime();//获取系统的纳秒时间
        long dValue = end-start;
        mSumTime +=dValue;
        Log.i("xys",String.valueOf(mSumTime));
        return convertView;
        //文艺模式  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    }
    //避免重复的findbyid操作
    class ViewHolder{
        public ImageView image;
        public TextView title;
        public TextView content;
    }
}
