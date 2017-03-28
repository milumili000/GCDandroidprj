package cn.bootrun.financier;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class FormActivity extends AppCompatActivity {

    private Button button = null;
    private Spinner spinner = null;
    private String[] arrStr = null;
    private ProgressDialog progressDialog = null;

    private TextView textView1 = null;
    private TextView textView2 = null;
    private TextView textView3 = null;
    private TextView textView4 = null;
    private TextView textView5 = null;

    private EditText editText1 = null;
    private EditText editText2 = null;
    private EditText editText3 = null;
    private EditText editText4 = null;
    private EditText editText5 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        spinner = (Spinner) findViewById(R.id.mySpinner);
        button = (Button) findViewById(R.id.submitButton);
        textView1 = (TextView) findViewById(R.id.myText1);
        textView2 = (TextView) findViewById(R.id.myText2);
        textView3 = (TextView) findViewById(R.id.myText3);
        textView4 = (TextView) findViewById(R.id.myText4);
        textView5 = (TextView) findViewById(R.id.myText5);
        editText1 = (EditText) findViewById(R.id.myEditText1);
        editText2 = (EditText) findViewById(R.id.myEditText2);
        editText3 = (EditText) findViewById(R.id.myEditText3);
        editText4 = (EditText) findViewById(R.id.myEditText4);
        editText5 = (EditText) findViewById(R.id.myEditText5);

        arrStr = getResources().getStringArray(R.array.place);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, arrStr);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FormActivity.this, "提交被取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] arrStr = new String[]{};
                        int i = (int) spinner.getSelectedItemId();
                        switch (i) {
                            case 0:
                                arrStr = new String[]{"1", editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), "000", "000"};
                                break;
                            case 1:
                                arrStr = new String[]{"2", editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString(), "000"};
                                break;
                            case 2:
                                arrStr = new String[]{"3", editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString(), editText5.getText().toString()};
                                break;
                            case 3:
                                arrStr = new String[]{"4", editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), "000", "000"};
                                break;
                            case 4:
                                arrStr = new String[]{"5", editText1.getText().toString(), editText2.getText().toString(), "000", "000", "000"};
                                break;
                            case 5:
                                arrStr = new String[]{"6", editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), "000", "000"};
                                break;
                        }
                        new MyTask().execute(arrStr);
                    }
                });
                builder.setCancelable(false);
                builder.setTitle("数据录入");

                StringBuilder msg = new StringBuilder();
                int i = (int) spinner.getSelectedItemId();

                switch (i) {
                    case 0:
                        msg.append("观测点：观测点一\n");
                        msg.append("温 度：" + editText1.getText() + "\n");
                        msg.append("流 量：" + editText2.getText() + "\n");
                        msg.append("压 力：" + editText3.getText() + "\n");
                        msg.append("\n");
                        msg.append("确定要提交数据吗?");
                        builder.setMessage(msg);
                        builder.show();
                        break;
                    case 1:
                        msg.append("观测点：观测点二\n");
                        msg.append("速 度：" + editText1.getText() + "\n");
                        msg.append("高 度：" + editText2.getText() + "\n");
                        msg.append("宽 度：" + editText3.getText() + "\n");
                        msg.append("长 度：" + editText4.getText() + "\n");
                        msg.append("\n");
                        msg.append("确定要提交数据吗?");
                        builder.setMessage(msg);
                        builder.show();
                        break;
                    case 2:
                        msg.append("观测点：观测点三\n");
                        msg.append("震 动：" + editText1.getText() + "\n");
                        msg.append("压 力：" + editText2.getText() + "\n");
                        msg.append("气 压：" + editText3.getText() + "\n");
                        msg.append("温 度：" + editText4.getText() + "\n");
                        msg.append("高 度：" + editText5.getText() + "\n");
                        msg.append("\n");
                        msg.append("确定要提交数据吗?");
                        builder.setMessage(msg);
                        builder.show();
                        break;
                    case 3:
                        msg.append("观测点：观测点四\n");
                        msg.append("压 力：" + editText1.getText() + "\n");
                        msg.append("速 度：" + editText2.getText() + "\n");
                        msg.append("流 量：" + editText3.getText() + "\n");
                        msg.append("\n");
                        msg.append("确定要提交数据吗?");
                        builder.setMessage(msg);
                        builder.show();
                        break;
                    case 4:
                        msg.append("观测点：观测点五\n");
                        msg.append("温 度：" + editText1.getText() + "\n");
                        msg.append("长 度：" + editText2.getText() + "\n");
                        msg.append("\n");
                        msg.append("确定要提交数据吗?");
                        builder.setMessage(msg);
                        builder.show();
                        break;
                    case 5:
                        msg.append("观测点：观测点六\n");
                        msg.append("速 度：" + editText1.getText() + "\n");
                        msg.append("温 度：" + editText2.getText() + "\n");
                        msg.append("湿 度：" + editText3.getText() + "\n");
                        msg.append("\n");
                        msg.append("确定要提交数据吗?");
                        builder.setMessage(msg);
                        builder.show();
                        break;
                }
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        textView1.setText("温度：");
                        textView2.setText("流量：");
                        textView3.setText("压力：");
                        textView4.setText("");
                        textView5.setText("");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.GONE);
                        editText5.setVisibility(View.GONE);
                        break;
                    case 1:
                        textView1.setText("速度：");
                        textView2.setText("高度：");
                        textView3.setText("宽度：");
                        textView4.setText("长度：");
                        textView5.setText("");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                        editText5.setVisibility(View.GONE);
                        break;
                    case 2:
                        textView1.setText("震动：");
                        textView2.setText("压力：");
                        textView3.setText("气压：");
                        textView4.setText("温度：");
                        textView5.setText("高度：");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.VISIBLE);
                        editText5.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        textView1.setText("压力：");
                        textView2.setText("速度：");
                        textView3.setText("流量：");
                        textView4.setText("");
                        textView5.setText("");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.GONE);
                        editText5.setVisibility(View.GONE);
                        break;
                    case 4:
                        textView1.setText("温度：");
                        textView2.setText("长度：");
                        textView3.setText("");
                        textView4.setText("");
                        textView5.setText("");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.GONE);
                        editText4.setVisibility(View.GONE);
                        editText5.setVisibility(View.GONE);
                        break;
                    case 5:
                        textView1.setText("速度：");
                        textView2.setText("温度：");
                        textView3.setText("湿度：");
                        textView4.setText("");
                        textView5.setText("");
                        editText1.setText("");
                        editText2.setText("");
                        editText3.setText("");
                        editText4.setText("");
                        editText5.setText("");
                        editText1.setVisibility(View.VISIBLE);
                        editText2.setVisibility(View.VISIBLE);
                        editText3.setVisibility(View.VISIBLE);
                        editText4.setVisibility(View.GONE);
                        editText5.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FormActivity.this);
            progressDialog.setTitle("正在提交");
            progressDialog.setMessage("请稍候...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Toast.makeText(FormActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String data1 = params[1];
            String data2 = params[2];
            String data3 = params[3];
            String data4 = params[4];
            String data5 = params[5];
            OkHttpClient client = new OkHttpClient();
            String url = "http://yyf.bootrun.cn/add/data?type=" + type + "&data1=" + data1 + "&data2=" + data2 + "&data3=" + data3 + "&data4=" + data4 + "&data5=" + data5;
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.isSuccessful() ? "提交成功" : "网络错误,请稍后重试！";
        }
    }

}
