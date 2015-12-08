package cn.somputon.aircleaner.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.activity.AccountManageActivity;
import cn.somputon.aircleaner.activity.CreditMallActivity;
import cn.somputon.aircleaner.activity.DeviceManageActivity;
import cn.somputon.aircleaner.activity.MainActivity;
import cn.somputon.aircleaner.activity.OrderQueryActivity;
import cn.somputon.aircleaner.activity.ServiceQueryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_me, null);
        initView(v);
        return v;

    }

    // TODO: 2015/12/5 设置监听
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View v) {
        v.findViewById(R.id.head_photo).setOnClickListener(this);
        v.findViewById(R.id.daily_task).setOnClickListener(this);
        v.findViewById(R.id.tv_credit_mall).setOnClickListener(this);
        v.findViewById(R.id.account_management).setOnClickListener(this);
        v.findViewById(R.id.device_management).setOnClickListener(this);
        v.findViewById(R.id.order_query).setOnClickListener(this);
        v.findViewById(R.id.service_query).setOnClickListener(this);
    }

    public void gotoDailyTask() {
        startActivity(CreditMallActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daily_task:
                gotoDailyTask();
                break;
            case R.id.tv_credit_mall:
                gotoCreditMall();
                break;
            case R.id.account_management:
                gotoAccountManagement();
                break;
            case R.id.device_management:
                gotoDeviceManagement();
                break;
            case R.id.order_query:
                gotOrderQuery();
                break;
            case R.id.service_query:
                gotServiceQuery();
                break;
            case R.id.head_photo:
                changeHeadPhoto();
            default:
                break;
        }
    }

    private void changeHeadPhoto() {

    }

    public void gotoCreditMall(){
        startActivity(CreditMallActivity.class);
    }

    public void gotoAccountManagement(){
        startActivity(AccountManageActivity.class);
    }

    public void gotoDeviceManagement(){
        startActivity(DeviceManageActivity.class);
    }

    public void gotOrderQuery(){
        startActivity(OrderQueryActivity.class);
    }

    public void gotServiceQuery(){
        startActivity(ServiceQueryActivity.class);
    }

    public void startActivity(Class<?> cls){
        Intent intent=new Intent(getActivity(),cls);
        startActivity(intent);
    }
}
