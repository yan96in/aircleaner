package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.x;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.PopupView;

public class SendSMSActivity extends AppCompatActivity {
    String baseURL="http://121.201.24.54:8081/Sumputom/";
    String method="getUserVerpwd";
    EditText etPhoneNumber;
    String phoneNumber;
    Button btnSendSMS;
    String userVerpwd="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("注册");
        etPhoneNumber=(EditText)findViewById(R.id.tv_phone_number_send);
        Intent intent=getIntent();
        phoneNumber=intent.getStringExtra("phoneNumber");
        etPhoneNumber.setText(phoneNumber);
        btnSendSMS=(Button)findViewById(R.id.btn_send_sms);
    }


    public void sendSMS(View v) {
//        Params params=new Params();
//        params.Mobile=this.phoneNumber;
//        Callback.Cancelable cancelable=x.http().get(params, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                userVerpwd=s;
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException e) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//
//            @Override
//            public boolean onCache(String s) {
//                return false;
//            }
//        });
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                btnSendSMS.setText(millisUntilFinished/1000+"s");
                btnSendSMS.setClickable(false);
            }
            @Override
            public void onFinish() {
                btnSendSMS.setText("重新发送验证码");
                btnSendSMS.setClickable(true);
            }
        }.start();
    }

    public void gotoNext(View view) {
        if (userVerpwd!="") {
            Intent intent = new Intent(SendSMSActivity.this, SetPasswordActivity.class);
            intent.putExtra("userVerpwd",userVerpwd);
            startActivityForResult(intent, 0);
        } else {
            new PopupView(this).popup("验证码错误请重新填写");
        }
    }

}
