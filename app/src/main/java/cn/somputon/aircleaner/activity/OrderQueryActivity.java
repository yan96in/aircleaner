package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import cn.somputon.aircleaner.R;

public class OrderQueryActivity extends AppCompatActivity {
    ListView mOrderList;
    TextView empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);
        mOrderList= ((ListView) findViewById(R.id.order_list));
        empty= ((TextView) findViewById(R.id.empty));
        mOrderList.setEmptyView(empty);
    }


}
