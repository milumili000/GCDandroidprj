package cn.bootrun.financier;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryActivity extends AppCompatActivity {

    private ListView listView = null;
    private Spinner spinner = null;
    private String[] arrStr = null;
    private Button button = null;
    private ProgressDialog progressDialog = null;

    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        WindowManager windowManager = this.getWindowManager();
        width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();


        button = (Button) findViewById(R.id.refush);
        listView = (ListView) findViewById(R.id.myListView);
        spinner = (Spinner) findViewById(R.id.querySpinner);
        arrStr = getResources().getStringArray(R.array.place);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrStr);
        spinner.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute(String.valueOf(spinner.getSelectedItemId()));
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new MyTask().execute(String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class MyTask extends AsyncTask<String, Integer, List<Map<String, String>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            Toast.makeText(QueryActivity.this, "正在查询数据", Toast.LENGTH_SHORT).show();

            progressDialog = new ProgressDialog(QueryActivity.this);
            progressDialog.setTitle("正在查询");
            progressDialog.setMessage("请稍候...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(List<Map<String, String>> s) {
            super.onPostExecute(s);
            MyListViewAdapter adapter = new MyListViewAdapter(QueryActivity.this, s, width, height);
            listView.setAdapter(adapter);
            progressDialog.dismiss();
            Toast.makeText(QueryActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            String url = "http://yyf.bootrun.cn/query/data?type=" + params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            try {
                response = client.newCall(request).execute();
                String respStr = response.body().string();
                if (respStr == "" || "".equals(respStr)) {
                    return list;
                }
                String[] arrObj = respStr.split("&");
                for (int i = 0; i < arrObj.length; i++) {
                    String[] arrVal = arrObj[i].split(";");
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("createTime", arrVal[0]);
                    map.put("type", arrVal[1]);
                    map.put("val", arrVal[2]);
                    list.add(map);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
