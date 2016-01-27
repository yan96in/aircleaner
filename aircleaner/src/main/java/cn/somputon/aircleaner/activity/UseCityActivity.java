package cn.somputon.aircleaner.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.xutils.common.util.LogUtil;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.adapter.ViewHolder;
import cn.somputon.aircleaner.entity.CityFromJson;
import cn.somputon.aircleaner.utils.HttpManager;

public class UseCityActivity extends AppCompatActivity{
    GridView gvCity;
    String[] array=null;
    ArrayList<String> list;
    MyHandler handler;
    Message message;
    TextView title;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_city);
        initData();
        handler=new MyHandler();
        initView();

    }


    String httpUrl = "http://apis.baidu.com/apistore/aqiservice/citylist";
    String httpArg = "";
    private void initData() {

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                message = handler.obtainMessage();
                message.what = 100;
                message.obj = HttpManager.request(httpUrl, httpArg);
                handler.sendMessage(message);
                Looper.loop();
            }
        }.start();
    }

    private void initView() {
//        title=((TextView) findViewById(R.id.tv_title));
//        title.setText("使用城市");
        gvCity= ((GridView) findViewById(R.id.gv_city));
        final MyAdapter adapter=new MyAdapter();
        gvCity.setAdapter(adapter);
        b= ((Button) findViewById(R.id.b_city));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });
    }



    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return (array==null)?0:array.length;
        }

        @Override
        public Object getItem(int position) {

            return array[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        TextView tv;
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView==null) {
                convertView=getLayoutInflater().inflate(R.layout.item_gridview_city,parent,false);
            }
             tv=ViewHolder.get(convertView,R.id.tv_item_city);
            tv.setText(array[position]);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.v(String.valueOf(position));

                    String text = tv.getText().toString();
                    LogUtil.v(text);
                    //必须要跟position关联
                    b.setText(/*text+*/array[position]);
                }
            });
            return convertView;
        }
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            CityFromJson ctj = JSON.parseObject(msg.obj.toString(), CityFromJson.class);
            array = ctj.getRetData().getCitylist().split(",");
            Arrays.sort(array, new Comparator<String>() {
                Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

                @Override
                public int compare(String lhs, String rhs) {
                    if (cmp.compare(lhs, rhs) > 0) {
                        return 1;
                    } else if (cmp.compare(lhs, rhs) < 0) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
    }
}




