package cn.somputon.aircleaner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.somputon.aircleaner.R;

public class PermitAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permitted_account);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("授权账户");
    }

}
