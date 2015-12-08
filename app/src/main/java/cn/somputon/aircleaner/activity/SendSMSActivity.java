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
//            popup("验证码错误请重新填写");

            new PopupView("验证码错误请重新填写").popup(SendSMSActivity.this);

        }

    }


    private void popup(String hint) {
        View popupView = getLayoutInflater().inflate(R.layout.a_base_popup_window, null);
        final PopupWindow popupErr = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        //设置按钮内容
        Button btnPopup = (Button) popupView.findViewById(R.id.popup_window_btn);
        btnPopup.setText(hint);
        popupErr.setTouchable(true);
        popupErr.setOutsideTouchable(true);
        popupErr.showAsDropDown(popupView);
        popupErr.getContentView().setFocusable(true);
        popupErr.getContentView().setOnClickListener(new View.OnClickListener() {

                                                         @Override
                                                         public void onClick(View v) {
                                                             if (popupErr.isShowing()) {
                                                                 popupErr.dismiss();
                                                             }
                                                         }
                                                     }
        );

    }
}
