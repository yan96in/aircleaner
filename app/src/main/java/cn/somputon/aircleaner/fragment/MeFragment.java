package cn.somputon.aircleaner.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.activity.MainActivity;

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
        Toast.makeText(getActivity(), "just tmep test", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daily_task:
                gotoDailyTask();
                break;
            case R.id.tv_credit_mall:
                gotoDailyTask();
                break;
            case R.id.account_management:
                gotoDailyTask();
                break;
            case R.id.device_management:
                gotoDailyTask();
                break;
            case R.id.order_query:
                gotoDailyTask();
                break;
            case R.id.service_query:
                gotoDailyTask();
                break;
            default:
                break;
        }
    }
}
