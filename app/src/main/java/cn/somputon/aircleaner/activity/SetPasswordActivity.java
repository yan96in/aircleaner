package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.somputon.aircleaner.R;

public class SetPasswordActivity extends AppCompatActivity {
    private boolean success=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
    }
    public void commit(){
        if(success){
            //// TODO: 2015/12/8  new dialog
            //gotoMain();
            inputDetail();
        }

    }

    public void gotoMain(){
        startActivity(MainActivity.class);
    }

    public void inputDetail(){
        startActivity(InputDetailActivity.class);
    }

    public void startActivity(Class<?> cls){
        Intent intent=new Intent(SetPasswordActivity.this,cls);
        startActivity(cls);
    }
}
