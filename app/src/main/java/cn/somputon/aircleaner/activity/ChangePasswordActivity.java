package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.PopupView;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText oldPassword, newPassword, newPasswordRepeat;
    String oldPWValue,newPWValue,newPWRValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("修改密码");

        oldPassword = ((EditText) findViewById(R.id.old_password));
        newPassword = ((EditText) findViewById(R.id.new_password));
        newPasswordRepeat = ((EditText) findViewById(R.id.new_password_repeat));
    }

    public void changePassword() {
        oldPWValue=oldPassword.getText().toString().trim();
        newPWValue=newPassword.getText().toString().trim();
        newPWRValue=newPasswordRepeat.getText().toString().trim();
        if(oldPWValue!=null&&false){
            new PopupView(this).popup("旧密码错误请重新填写！");
        }
        if (newPWRValue!=null&&newPWValue!=null){
            if(newPWRValue!=newPWValue){
                new PopupView(this).popup("新密码验证错误请重新填写！");
            }else {
                //// TODO: 2015/12/8 ChangePaswwordCommit
                new PopupView(this).popup("修改密码成功！");
            }
        }
    }

}
