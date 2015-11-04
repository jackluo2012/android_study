package net.webjoy.jackluo.android_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jackluo on 11/1/15.
 */
public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.ViewHolder> {

    private final ArrayList<SampleModel> sampleData = DemoApp.getSampleData(20);

    /**
     * 用于创建控件的
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public SampleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //获得列表项控件(LinearLayer对象)

        //list_basic_item.xml布局文件中包含一个 <LinearLayout>标签,在该标签中包含一个TextView控件

        //  item是LinearLayout对象
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_basic_item,parent,false);

        return new ViewHolder(item);
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(SampleRecyclerAdapter.ViewHolder holder, int position) {
        //获取当前Item中显示的数据
        final SampleModel rowData = sampleData.get(position);
        //设置要显示的数据
        holder.textViewSample.setText(rowData.getSampleText());
        //
        holder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {
        return sampleData.size();
    }
    //删除 指定的item
    public void removeData(int position){
        sampleData.remove(position);
        //通知删除了,有个动画的效果
        notifyItemRemoved(position);
    }
    //添加指定位置新的item
    public void addItem(int positionToAdd){
        sampleData.add( positionToAdd,new SampleModel("新的列表项"+new Random().nextInt(10000)));
        //通知RecyclerView控件插入了某个Item
        notifyItemInserted(positionToAdd);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewSample;
        public ViewHolder(View itemView){
            super(itemView);
            textViewSample = (TextView) itemView.findViewById(R.id.textViewSample);
        }
    }
}
