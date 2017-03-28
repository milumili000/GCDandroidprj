package cn.bootrun.financier;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Home extends TabActivity {

    private TabHost tabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        tabHost = getTabHost();
        TabSpec page1 = tabHost.newTabSpec("tab1").setIndicator("数据录入").setContent(new Intent(this, FormActivity.class));
        TabSpec page2 = tabHost.newTabSpec("tab2").setIndicator("查询").setContent(new Intent(this, QueryActivity.class));
        tabHost.addTab(page1);
        tabHost.addTab(page2);
    }
}
