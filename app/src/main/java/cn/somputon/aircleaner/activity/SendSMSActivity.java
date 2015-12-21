package cn.somputon.aircleaner.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.PopupView;

public class SendSMSActivity extends AppCompatActivity {
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("注册");
    }


    public void sendSMS() {

    }

    public void gotoNext(View view) {
        if (false) {
            Intent intent = new Intent(SendSMSActivity.this, SetPasswordActivity.class);
            startActivity(intent);
        } else {
            new PopupView(this).popup("验证码错误请重新填写");

        }

    }
}
