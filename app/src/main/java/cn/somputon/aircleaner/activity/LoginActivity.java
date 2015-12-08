package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.somputon.aircleaner.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(){
        startActivity(MainActivity.class);
    }

    public void register(){
        startActivity(RegisterActivity.class);
    }

    public void findPassword(){
        //todo
        startActivity(RegisterActivity.class);
    }

    public void loginFromWeibo(){

    }
    public void loginFromQQ(){

    }
    public void loginFromWeixin(){

    }

    public void startActivity(Class<?> cls){
        Intent intent=new Intent(LoginActivity.this,cls);
        startActivity(intent);
    }
}
