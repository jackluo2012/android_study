package net.webjoy.jackluo.android_recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button mDeleteBar;

    private void showDeleteBar(){
        //mDeleteBar.startAnimation(AnimationUtils.loadAnimation(this,Android.R.anim));
        mDeleteBar.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //mDeleteBar
        mDeleteBar = (Button) findViewById(R.id.deleteBar);


        //创建右下角圆形按钮
        //创建RecycleView控件
        //创建上方的Delete面板
/*

        ViewOutlineProvider provider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //获取按钮的尺寸
                int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_margin);

                //设置轮廓的尺寸
                outline.setOval(-4,-4,fabSize+2,fabSize+2);
            }
        };

*/



        //获取右下角圆形按钮对象
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //获取 RecyclerView对象
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //创建线性管理器
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //为RecyclerView指定布局管理器对象
        recyclerView.setLayoutManager(layoutManager);
        //创建列表分割线对象
        final RecyclerView.ItemDecoration itemDecoration = new SampleDivider(this);


        //分割线将控件相关联
        recyclerView.addItemDecoration(itemDecoration);

        final SampleRecyclerAdapter sampleRecyclerAdapter = new SampleRecyclerAdapter();
        recyclerView.setAdapter(sampleRecyclerAdapter);

        //处理右下角的单击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionToAdd = layoutManager.findFirstCompletelyVisibleItemPosition();

                //在该位置插入新的item
                sampleRecyclerAdapter.addItem(positionToAdd);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //处理删除
        //*/

        //处理点击
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy>0){
                    mDeleteBar.setVisibility(View.VISIBLE);
                }else{
                    mDeleteBar.setVisibility(View.GONE);
                }
                super.onScrolled(recyclerView, dx, dy);
//                Log.i("test->>dx", Integer.toString(dx));
 //               Log.i("test->>dy",Integer.toString(dy));
            }
        });
        mDeleteBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("tag", Integer.toString(layoutManager.findFirstCompletelyVisibleItemPosition()));
                sampleRecyclerAdapter.removeData(layoutManager.findFirstCompletelyVisibleItemPosition());
            }
        });
    }
}
