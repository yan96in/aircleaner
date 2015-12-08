package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.somputon.aircleaner.R;

public class ServiceQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_query);
    }

    public void startActivity(Class<?> cls){
        Intent intent=new Intent(ServiceQueryActivity.this,cls);
        startActivity(intent);
    }
}
