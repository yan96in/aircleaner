package cn.somputon.aircleaner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.somputon.aircleaner.R;

public class ReportFaultRepairActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_fault_repair);
        initView();
    }

    private void initView() {
        (findViewById(R.id.leftBtn)).setBackgroundResource(R.drawable.back);
        (findViewById(R.id.leftBtn)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_title)).setText("故障维修");
    }

}
