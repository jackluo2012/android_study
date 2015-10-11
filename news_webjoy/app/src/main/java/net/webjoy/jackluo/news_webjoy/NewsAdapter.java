package net.webjoy.jackluo.news_webjoy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jackluo on 9/8/15.
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private List<NewsBean> mList;
    private LayoutInflater mInfater;
    private ImageLoader mImageLoader;
    private int mStart,mEnd;//获取 listView滚动的
    public static String[] URLS;//获取所有要加载的图片URL
    private boolean mFirstIn;

    public NewsAdapter(Context context, List<NewsBean> data,ListView listView) {
        this.mList = data;
        mInfater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(listView);
        URLS = new String[data.size()];
        for (int i=0;i<data.size();i++){
            URLS[i] = data.get(i).newsIconUrl;
        }
        mFirstIn = true;
        //一定要注册代理
        listView.setOnScrollListener(this);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInfater.inflate(R.layout.item_layout,null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsBean bean = mList.get(position);
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        //创建一个标识
        viewHolder.ivIcon.setTag(bean.newsIconUrl);
        //使用多线程方式加载
        //new ImageLoader().showImageByThread(viewHolder.ivIcon,bean.newsIconUrl);
        //AsyncTask 方式运行
        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, bean.newsIconUrl);

        viewHolder.tvTitle.setText(bean.newsTitle);
        viewHolder.tvContent.setText(bean.newsContent);

        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE){
            //加载    可见项
            mImageLoader.loadImages(mStart,mEnd);
        }else {
            //停止任务
            mImageLoader.cancelAllTasks();
        }
    }

    /**
     * 滑动调用
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem +visibleItemCount;
        //listView 已经画出来了 第一次显示 调用
        if (mFirstIn && visibleItemCount>0){
            //人工的显示 第一屏数据
            mImageLoader.loadImages(mStart,mEnd);
            mFirstIn = false;
        }
    }

    class ViewHolder{
        public TextView tvTitle,tvContent;
        public ImageView ivIcon;
    }
}
