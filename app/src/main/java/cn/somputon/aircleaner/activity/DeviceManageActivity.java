package cn.somputon.aircleaner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.view.SwipeListView;

public class DeviceManageActivity extends AppCompatActivity {
    //一级权限。。设备列表
    SwipeListView lv1, lv2, lv3, lvno;

    //todo swipelistview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_manage);
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("设备管理");
    }


}
