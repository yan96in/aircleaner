package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import cn.somputon.aircleaner.R;

public class ServiceQueryActivity extends AppCompatActivity {
    ListView mServiceList;
    TextView empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_query);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("服务查询");
        mServiceList= ((ListView) findViewById(R.id.service_list));
        empty= ((TextView) findViewById(R.id.empty));
        mServiceList.setEmptyView(empty);
    }


    public void startActivity(Class<?> cls){
        Intent intent=new Intent(ServiceQueryActivity.this,cls);
        startActivity(intent);
    }
}
