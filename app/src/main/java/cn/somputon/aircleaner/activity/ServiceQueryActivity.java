package cn.somputon.aircleaner.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.somputon.aircleaner.R;

public class ServiceQueryActivity extends AppCompatActivity {
    ExpandableListView mServiceList;

    TextView empty;
    List<String> service = null;
    List<String> child=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_query);
        initData();
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("服务查询");
        mServiceList = ((ExpandableListView) findViewById(R.id.service_list));
        empty = ((TextView) findViewById(R.id.empty));
        mServiceList.setEmptyView(empty);
        mServiceList.setAdapter(new MyAdapter());
    }

    // 初始化数据
    public void initData() {
        service = new ArrayList<String>();
        service.add("服务编号xxx");
        service.add("服务编号xxx");
        service.add("服务编号xxx");
    }


    class MyAdapter extends BaseExpandableListAdapter {

        //得到子item需要关联的数据

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child;
        }

        //得到子item的ID
        @Override
        public long getChildId(int servicePosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int servicePosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup service) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) ServiceQueryActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_service_child, null);
            }
           //todo 逻辑判断该显示什么内容

            return convertView;
        }

        //获取当前父item下的子item的个数

        @Override
        public int getChildrenCount(int groupPosition) {
            return 0;
        }

        //获取当前父item的数据
        @Override
        public Object getGroup(int servicePosition) {
            return service.get(servicePosition);
        }

        @Override
        public int getGroupCount() {
            return service.size();
        }

        @Override
        public long getGroupId(int servicePosition) {
            return servicePosition;
        }

        //设置父item组件
        @Override
        public View getGroupView(int servicePosition, boolean isExpanded,
                                 View convertView, ViewGroup service) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) ServiceQueryActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_service_group, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.tv_service_number);
            tv.setText(ServiceQueryActivity.this.service.get(servicePosition));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int servicePosition, int childPosition) {
            return true;
        }

    }
}
