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

public class AccountManageActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvInputDetail, tvChangePassword, tvCreditQuery, tvGetCredit, tvCorrelationAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("账号管理");
        findViewById(R.id.tv_input_detail).setOnClickListener(this);
        findViewById(R.id.tv_change_password).setOnClickListener(this);
        findViewById(R.id.tv_credit_query).setOnClickListener(this);
        findViewById(R.id.tv_get_credit).setOnClickListener(this);
        findViewById(R.id.tv_correlation_account).setOnClickListener(this);
    }


    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(AccountManageActivity.this, cls);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_input_detail:
                gotoInputDetail();
                break;
            case R.id.tv_change_password:
                gotoChangePassword();
                break;
            case R.id.tv_credit_query:
                gotoCreditQuery();
                break;
            case R.id.tv_get_credit:
                gotoCorrelationAccount();
                break;
            case R.id.tv_correlation_account:
                gotoCorrelationAccount();
                break;
            default:
                break;
        }
    }

    public void gotoChangePassword() {
        startActivity(ChangePasswordActivity.class);
    }

    public void gotoInputDetail() {
        startActivity(InputDetailActivity.class);
    }

    private void gotoCorrelationAccount() {
        //todo
    }

    private void gotoGetCredit() {
        //// TODO: 2015/12/17
    }

    private void gotoCreditQuery() {
        //// TODO: 2015/12/17
    }
}
