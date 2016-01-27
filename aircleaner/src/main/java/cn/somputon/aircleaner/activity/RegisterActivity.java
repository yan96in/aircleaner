package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.PopupView;

public class RegisterActivity extends AppCompatActivity {
    EditText etPhoneNumber;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("注册");
        etPhoneNumber= ((EditText) findViewById(R.id.tv_phone_number));

    }

    public void gotoNext(View v){
        phoneNumber=etPhoneNumber.getText().toString();
        if(phoneNumber==""){
            new PopupView(this).popup("手机号不能为空！");
        }else if(phoneNumber.length()<11){
            new PopupView(this).popup("手机号长度不正确");
        }else {
            Intent intent=new Intent(RegisterActivity.this,SendSMSActivity.class);
            intent.putExtra("phoneNumber",phoneNumber);
            startActivityForResult(intent,0);
        }
    }
}
