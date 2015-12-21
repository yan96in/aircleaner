package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.somputon.aircleaner.Applic;
import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.PopupView;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("somputon新松");
    }

    public void login() {

        if (true) {
            new PopupView(this).popup("登录成功！");
            startActivity(MainActivity.class);
            Applic.isLogin = true;
            finish();
        }

    }

    public void register(View v) {
        startActivity(RegisterActivity.class);
    }

    public void findPassword(View v) {
        //todo 找回密码页面

    }

    public void loginFromWeibo(View v) {

    }

    public void loginFromQQ(View v) {

    }

    public void loginFromWeixin(View v) {

    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(LoginActivity.this, cls);
        startActivity(intent);
    }
}
