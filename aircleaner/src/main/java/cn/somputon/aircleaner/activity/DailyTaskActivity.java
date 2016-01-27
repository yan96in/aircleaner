package cn.somputon.aircleaner.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.somputon.aircleaner.Applic;
import cn.somputon.aircleaner.CallBack;
import cn.somputon.aircleaner.Constants;
import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.adapter.ViewHolder;
import cn.somputon.aircleaner.entity.TotalTask;
import cn.somputon.aircleaner.utils.UploadAvatarUtil;

@ContentView(R.layout.activity_credit_task)
public class DailyTaskActivity extends AppCompatActivity {
    static final String Method = "getTotalTaskList";
    Context context = DailyTaskActivity.this;
    List<String> parent;
    Map<String, List<String>> group;
    ExpandableListView elvTask;
    String 积分任务;
    String token = "";
    TotalTask totalTask;
    int array[][];
    MyHandler handler;
    boolean initialized = false;
    MyAdapter adapter;
    TextView tvCheckIn;
      @ViewInject(R.id.task_perfect_info)
  TextView tvPerfectInfo;
    boolean signed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_task);
        initData();
        //findViewById(R.id.leftBtn).setVisibility(View.VISIBLE);
        initViews();
        handler = new MyHandler();
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                RequestParams params = new RequestParams(Constants.HOST +
                        Method);
                token = Applic.getToken();
                if (token != "") {
                    params.addBodyParameter("Token", token);
                    LogUtil.v(token);
                }
                Callback.Cancelable cancelable = x.http().post(params, new CallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
//                        Gson gson = new Gson();
//                        totalTask = gson.fromJson(s, TotalTask.class);
                        totalTask= JSON.parseObject("s",TotalTask.class);
                        LogUtil.v("get totalTask");
                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        LogUtil.v("msg.what==1");
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        LogUtil.v("Error");
                        Message msg = handler.obtainMessage();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }
                });
            }
        }.start();


//        if( ViewConfiguration.get(this).hasPermanentMenuKey()){
//        }else{
//            Window window = getWindow();
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.systemUiVisibility = (
//                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//            window.setAttributes(params);
//        }
    }

    private void initViews() {
        积分任务 = "积分任务";
        ((TextView) findViewById(R.id.tv_title)).setText(积分任务);
        elvTask = (ExpandableListView) findViewById(R.id.elv_accumulative_task);
        adapter = new MyAdapter();
        elvTask.setAdapter(adapter);
        tvCheckIn = ((TextView) findViewById(R.id.tv_check_in));

        tvCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SignActivity.class);
                intent.putExtra("sign", signed);
                startActivityForResult(intent, 0);

            }
        });

        findViewById(R.id.task_perfect_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(InputDetailActivity.class);
            }
        });

        findViewById(R.id.task_upload_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (false) {

                } else {
                    uploadAvatarTask();
                }
            }
        });


    }

    @Event(value = R.id.task_perfect_info)
    private void onPerfectInfoClick(View view) {
        startActivity(InputDetailActivity.class);
    }

    PopupWindow popupWindow;

    private void uploadAvatarTask() {
        View view = getLayoutInflater().inflate(R.layout.popwin_photo, null);

        Button popShot = ((Button) view.findViewById(R.id.btn_pop_shot));
        Button popAlbum = ((Button) view.findViewById(R.id.btn_pop_album));
        popShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadAvatarUtil.openCamera(DailyTaskActivity.this, 2);
                if (popupWindow != null) popupWindow.dismiss();
            }
        });
        popAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadAvatarUtil.openAlbum(DailyTaskActivity.this, 1);
                if (popupWindow != null) popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown(getLayoutInflater().inflate(R.layout.activity_credit_task, null),
                getWindowManager().getDefaultDisplay().getWidth() / 2 - 100,
                getWindowManager().getDefaultDisplay().getHeight() - 300);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        String imagePath = "/my_photo.png";
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    signed = data.getBooleanExtra("sign", false);
                    break;

                case 1:
                    uri = data.getData();
                    if (uri != null) {
                        UploadAvatarUtil.uploadAvatar(this, uri);
                    }

                    break;
                case 2:
                    File temp = new File(Environment.getExternalStorageDirectory() + imagePath);
                    if (temp != null) {
                        uri = Uri.fromFile(temp);
                        UploadAvatarUtil.uploadAvatar(this, uri);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initData() {
        parent = new ArrayList<>();
        parent.add("连续签到");
        parent.add("累计分享时景");
        parent.add("累计点赞");
        parent.add("累计评论");
        parent.add("累计分享朋友圈");
        group = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("连续7天+10分");
        list.add("连续30天+100分");
        list.add("连续100天+1000分");
        list.add("连续365天+5000分");
        group.put("连续签到", list);
        list = new ArrayList<>();
        list.add("累计10次+100分");
        list.add("累计50次+200分");
        list.add("累计100次+500分");
        list.add("累计200次+1000分");
        list.add("累计500次+2000分");
        list.add("累计1000次+5000分");
        list.add("累计2000次+10000分");
        list.add("累计5000次+20000分");
        group.put("累计分享时景", list);
        list = new ArrayList<>();
        list.add("累计10次+20分");
        list.add("累计50次+50分");
        list.add("累计100次+100分");
        list.add("累计200次+200分");
        list.add("累计500次+500分");
        list.add("累计1000次+1000分");
        list.add("累计2000次+2000分");
        list.add("累计5000次+5000分");
        group.put("累计点赞", list);
        list = new ArrayList<>();
        list.add("累计10次+50分");
        list.add("累计50次+100分");
        list.add("累计100次+200分");
        list.add("累计200次+500分");
        list.add("累计500次+1000分");
        list.add("累计1000次+2000分");
        list.add("累计2000次+5000分");
        list.add("累计5000次+10000分");
        group.put("累计评论", list);
        list = new ArrayList<>();
        list.add("累计10次+200分");
        list.add("累计50次+500分");
        list.add("累计100次+1000分");
        list.add("累计200次+2000分");
        list.add("累计500次+5000分");
        list.add("累计1000次+10000分");
        list.add("累计2000次+20000分");
        list.add("累计5000次+50000分");
        group.put("累计分享朋友圈", list);
    }


    private void initArray() {
        array = new int[][]{{
                totalTask.getBaseModle().getSign().getT7day(),
                totalTask.getBaseModle().getSign().getT30day(),
                totalTask.getBaseModle().getSign().getT100day(),
                totalTask.getBaseModle().getSign().getT365day()
        }, {
                totalTask.getBaseModle().getShare().getT10times(),
                totalTask.getBaseModle().getShare().getT50times(),
                totalTask.getBaseModle().getShare().getT100times(),
                totalTask.getBaseModle().getShare().getT200times(),
                totalTask.getBaseModle().getShare().getT500times(),
                totalTask.getBaseModle().getShare().getT1000times(),
                totalTask.getBaseModle().getShare().getT2000times(),
                totalTask.getBaseModle().getShare().getT5000times()
        }, {
                totalTask.getBaseModle().getZan().getT10times(),
                totalTask.getBaseModle().getZan().getT50times(),
                totalTask.getBaseModle().getZan().getT100times(),
                totalTask.getBaseModle().getZan().getT200times(),
                totalTask.getBaseModle().getZan().getT500times(),
                totalTask.getBaseModle().getZan().getT1000times(),
                totalTask.getBaseModle().getZan().getT2000times(),
                totalTask.getBaseModle().getZan().getT5000times()
        }, {
                totalTask.getBaseModle().getComment().getT10times(),
                totalTask.getBaseModle().getComment().getT50times(),
                totalTask.getBaseModle().getComment().getT100times(),
                totalTask.getBaseModle().getComment().getT200times(),
                totalTask.getBaseModle().getComment().getT500times(),
                totalTask.getBaseModle().getComment().getT1000times(),
                totalTask.getBaseModle().getComment().getT2000times(),
                totalTask.getBaseModle().getComment().getT5000times()
        }, {
                totalTask.getBaseModle().getShareFriend().getT10times(),
                totalTask.getBaseModle().getShareFriend().getT50times(),
                totalTask.getBaseModle().getShareFriend().getT100times(),
                totalTask.getBaseModle().getShareFriend().getT200times(),
                totalTask.getBaseModle().getShareFriend().getT500times(),
                totalTask.getBaseModle().getShareFriend().getT1000times(),
                totalTask.getBaseModle().getShareFriend().getT2000times(),
                totalTask.getBaseModle().getShareFriend().getT5000times()
        }};
        LogUtil.v("initized");
    }


    class MyAdapter extends BaseExpandableListAdapter {
        Drawable drawable;

        @Override
        public int getGroupCount() {
            return parent.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return group.get(parent.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parent.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return group.get(parent.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_task, null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.task_parent);
            textView.setText(DailyTaskActivity.this.parent.get(groupPosition));

            if (isExpanded) {
                textView.setCompoundDrawables(null, null, null, null);
            } else {
                drawable = getDrawable(R.drawable.expander_open);
                drawable.setBounds(0, 0, drawable.getMinimumWidth() / 2, drawable.getMinimumHeight() / 2);
                textView.setCompoundDrawables(null, null, drawable, null);
            }
            return textView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_task_child, parent, false);
            }
            TextView textView = ViewHolder.get(convertView, R.id.task_child);
            textView.setText(DailyTaskActivity.this.group.get(DailyTaskActivity.this.parent.get(groupPosition)).get(childPosition));
            if (initialized && array[groupPosition][childPosition] == 1) {
                Drawable drawable = getDrawable(R.drawable.clicked);
                drawable.setBounds(0, 0, drawable.getMinimumWidth() / 2, drawable.getMinimumHeight() / 2);
                textView.setCompoundDrawables(null, null, drawable, null);
            }

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);

    }

//    public void gotoInputDetail(View v) {
//        startActivity(InputDetailActivity.class);
//    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                initArray();
                LogUtil.v(String.valueOf(array[3][3]));
                initialized = true;
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();
            } else {
                LogUtil.v("not initied,check code");
            }
            super.handleMessage(msg);
        }
    }
}
