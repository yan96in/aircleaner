package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.Event;

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


    @Event(value = R.id.tv_change_password,
            type = View.OnClickListener.class)
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
