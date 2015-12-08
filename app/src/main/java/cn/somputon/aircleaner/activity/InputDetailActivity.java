package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.PopupView;

public class InputDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_detail);
    }

    public void showOrHide(){

    }

    public void isSure(){
        //// TODO: 2015/12/8 save detail
        gotoMain();
    }

    private void gotoMain() {

        Intent intent=new Intent(InputDetailActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
