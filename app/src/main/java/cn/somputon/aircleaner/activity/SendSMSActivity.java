package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.PopupWindow;

import cn.somputon.aircleaner.R;

public class SendSMSActivity extends AppCompatActivity {
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
    }

    public void sendSMS(){

    }

    public void gotoNext(){
        if(true){}
        Intent intent=new Intent(SendSMSActivity.this,SetPasswordActivity.class);
        startActivity(intent);
    }


}
