package cn.somputon.aircleaner.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.adapter.RankAdapter;
import cn.somputon.aircleaner.entity.City;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    TextView title;
    ListView lvRank;
    City city;
    List<City> cityList;
    RankAdapter adapter;
    SwipeRefreshLayout srl;
    public void initData() {
        cityList = new ArrayList<>();
        city = new City("1", "石家庄", "河北", "425");
        for (int i = 0; i < 20; i++) {
            cityList.add(city);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_rank, null);
        title = ((TextView) view.findViewById(R.id.tv_title));
        title.setText("排名");
        srl=((SwipeRefreshLayout) view.findViewById(R.id.refresh_layout));
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //测试下拉刷新
                        city = new City("2", "杭州", "浙江", "125");
                        cityList.add(city);

                        adapter.notifyDataSetChanged();
                        srl.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        lvRank = ((ListView) view.findViewById(R.id.lv_rank));
        adapter = new RankAdapter(cityList, getActivity());
        lvRank.setAdapter(adapter);
        return view;
    }


}
