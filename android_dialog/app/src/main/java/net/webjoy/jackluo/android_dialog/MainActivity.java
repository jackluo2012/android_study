package net.webjoy.jackluo.android_dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String []single_list = {"男生","女生","女博士","程序员"};
    String []multi_list = {"打球","唱歌","跳舞","旅游","画画"};
    String []item_list = {"项目经理","策划","测试","美工","程序鎱"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void ButonClick(View view){
        switch (view.getId()){
            case R.id.dialog_bt:
                showDialog1();
                break;
            case R.id.dialog_bt1:
                showDialog2();
                break;
            case R.id.dialog_bt2:
                showDialog3();
                break;
            case R.id.dialog_bt3:
                showDialog4();
                break;
            case R.id.dialog_bt4:
                showDialog5();
                break;
            default:

                break;

        }

        Log.d("info", "-----ok---");
    }

    /**
     * 显示确认对话框
     */
    private void showDialog1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("确认对话框");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了确定按钮!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了取消按钮!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }

    /**
     * 单选对话框
     */
    private void showDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择性别");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(single_list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = single_list[which];
                Toast.makeText(MainActivity.this, "你的性别是:" + str, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /**
     * 多选对话框
     */
    private void showDialog3(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择爱好");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(multi_list, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(MainActivity.this, "我喜欢上了:" + multi_list[which], Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /**
     * 显示列表对话框
     */
    private void showDialog4(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择部门列表");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(item_list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你选择的部门是:" + item_list[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /**
     * 自定义对话框
     */
    private void showDialog5(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_layout,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
