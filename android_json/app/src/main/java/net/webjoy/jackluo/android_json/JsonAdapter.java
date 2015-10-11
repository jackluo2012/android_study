package net.webjoy.jackluo.android_json;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jackluo on 9/28/15.
 */
public class JsonAdapter extends BaseAdapter {
    private List<Person> list;
    private Context context;
    private LayoutInflater inflater;//触发布局对象

    private Handler handler = new Handler();

    public  JsonAdapter(Context context,List<Person> list){
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public JsonAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Person> data){
        this.list = data;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item,null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        Person person = list.get(position);
        holder.name.setText(person.getName());
        holder.age.setText(""+person.getAge());
        List<SchoolInfo> schoolInfos = person.getSchoolInfo();
        SchoolInfo schoolInfo1 = schoolInfos.get(0);
        SchoolInfo schoolInfo2 = schoolInfos.get(1);
        holder.school1.setText(schoolInfo1.getSchool_name());
        holder.school2.setText(schoolInfo2.getSchool_name());
        new HttpImage(person.getUrl(),handler,holder.imageView).start();

        return convertView;
    }

    class Holder{
        private TextView name;
        private TextView age;
        private TextView school1;
        private TextView school2;
        private ImageView imageView;

        public Holder(View view){
            name = (TextView) view.findViewById(R.id.name);
            age = (TextView) view.findViewById(R.id.age);
            school1 = (TextView) view.findViewById(R.id.school1);
            school2 = (TextView) view.findViewById(R.id.school2);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}
