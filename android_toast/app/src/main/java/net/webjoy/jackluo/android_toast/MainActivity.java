package net.webjoy.jackluo.android_toast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvent();
    }

    /**
     * 初始化方法
     */
    private void initEvent(){
        findViewById(R.id.toastBtn_default).setOnClickListener(clickListener);
        findViewById(R.id.toastBtn_2).setOnClickListener(clickListener);
        findViewById(R.id.toastBtn_3).setOnClickListener(clickListener);
        findViewById(R.id.toastBtn_4).setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.toastBtn_default:
                    showToastDefault();
                    break;
                case R.id.toastBtn_2:
                    showToast2();
                    break;
                case R.id.toastBtn_3:
                    showToast3();
                    break;
                case R.id.toastBtn_4:
                    showToast4();
                    break;
                default:
                    break;
            }


        }
    };



    /**
     * 显示默认toast
     */
    private void showToastDefault(){
        Toast toast = Toast.makeText(this,"这是一个默认的Toast!",Toast.LENGTH_SHORT);
        toast.show();
    }
    /**
     * 显示自定义的位置的Toast
     */
    private void showToast2(){
        Toast toast = Toast.makeText(this,"改变位置的Toast!",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
    /**
     * 显示带图片的Toast
     */
    private void showToast3(){
        Toast toast = Toast.makeText(this,"带有图片的Toast!",Toast.LENGTH_SHORT);
        LinearLayout toast_layout = (LinearLayout) toast.getView();
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        toast_layout.addView(imageView);
        toast.show();
    }

    /**
     * 完全自定义的Toast
     */
    private void showToast4() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View toast_view = inflater.inflate(R.layout.toast_layout,null);
        Toast toast = new Toast(this);
        toast.setView(toast_view);
        toast.show();
    }
}
