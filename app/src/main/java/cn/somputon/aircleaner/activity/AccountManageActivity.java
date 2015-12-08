package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.somputon.aircleaner.R;

public class AccountManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("账号管理");
    }


    public void gotoChangePassword() {
        startActivity(ChangePasswordActivity.class);
    }

    public void gotoInputDetail() {
        startActivity(InputDetailActivity.class);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(AccountManageActivity.this, cls);
        startActivity(intent);
    }
}
