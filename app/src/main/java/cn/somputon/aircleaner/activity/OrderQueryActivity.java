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

public class OrderQueryActivity extends AppCompatActivity {
    ExpandableListView mOrderList;
    List<String> order=null;
    Map<String, List<String>> map = null;
    TextView empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);
        initData();
        initView();
    }

    // 初始化数据
    public void initData() {
        order = new ArrayList<String>();
        order.add("order1");
        order.add("order2");
        order.add("order3");

        map = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        list1.add("child1-1");
        list1.add("child1-2");
        map.put("order1", list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("child2-1");
        list2.add("child2-2");
        map.put("order2", list2);

        List<String> list3 = new ArrayList<String>();
        list3.add("child3-1");
        list3.add("child3-2");
        map.put("order3", list3);

    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText("订单查询");
        mOrderList= ((ExpandableListView) findViewById(R.id.order_list));
        empty= ((TextView) findViewById(R.id.empty));
        mOrderList.setEmptyView(empty);
        mOrderList.setAdapter(new MyAdapter());

    }

    class MyAdapter extends BaseExpandableListAdapter {

        //得到子item需要关联的数据
        @Override
        public Object getChild(int orderPosition, int childPosition) {
            String key = order.get(orderPosition);
            return (map.get(key).get(childPosition));
        }

        //得到子item的ID
        @Override
        public long getChildId(int orderPosition, int childPosition) {
            return childPosition;
        }

        //设置子item的组件
        @Override
        public View getChildView(int orderPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup order) {
            String key = OrderQueryActivity.this.order.get(orderPosition);
            String info = map.get(key).get(childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) OrderQueryActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_order_child, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.tv_order_done);
            tv.setText(info);
            return convertView;
        }

        //获取当前父item下的子item的个数
        @Override
        public int getChildrenCount(int orderPosition) {
            String key = order.get(orderPosition);
            int size=map.get(key).size();
            return size;
        }
        //获取当前父item的数据
        @Override
        public Object getGroup(int orderPosition) {
            return order.get(orderPosition);
        }

        @Override
        public int getGroupCount() {
            return order.size();
        }

        @Override
        public long getGroupId(int orderPosition) {
            return orderPosition;
        }
        //设置父item组件
        @Override
        public View getGroupView(int orderPosition, boolean isExpanded,
                                 View convertView, ViewGroup order) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) OrderQueryActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_order_group, null);
            }
            TextView tv = (TextView) convertView
                    .findViewById(R.id.tv_order_number);
            tv.setText(OrderQueryActivity.this.order.get(orderPosition));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int orderPosition, int childPosition) {
            return true;
        }

    }

}
