package cn.bootrun.financier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by tinker on 17/3/1.
 */
public class MyListViewAdapter extends BaseAdapter {
    private Context context = null;
    private List<Map<String, String>> data = null;
    private int width;
    private int height;

    public MyListViewAdapter() {

    }

    public MyListViewAdapter(Context context, List<Map<String, String>> data, int w, int h) {
        this.context = context;
        this.data = data;
        width = w;
        height = h;
    }

    //获得数据条数
    @Override
    public int getCount() {
        return data.size();
    }

    //获得数据中的某个对象
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    //获得某一行的ID或位置
    @Override
    public long getItemId(int position) {
        return position;
    }

    //获得一个view对象
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_layout, null);
            convertView.setLayoutParams(new AbsListView.LayoutParams(width, height / 8));
        }

        Map<String, String> map = data.get(position);

        TextView t1 = (TextView) convertView.findViewById(R.id.createTime);
        TextView t2 = (TextView) convertView.findViewById(R.id.type);
        TextView t3 = (TextView) convertView.findViewById(R.id.val);
        if (map.get("createTime").length() > 2) {
            t1.setText(map.get("createTime").substring(0, map.get("createTime").length() - 2));
        } else {
            t1.setText(map.get("createTime"));
        }
        t2.setText(map.get("type"));
        t3.setText(map.get("val"));


        return convertView;
    }
}
