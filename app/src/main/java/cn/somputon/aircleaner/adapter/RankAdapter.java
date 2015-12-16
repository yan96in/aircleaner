package cn.somputon.aircleaner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.entity.City;

/**
 * Created by 002 on 2015/12/16.
 */
public class RankAdapter extends BaseAdapter {
    TextView rank, cityName, province;
    Button ditailNum;
    List<City> cities;
    Context context;

    public RankAdapter(List<City> cities, Context context) {
        this.cities = cities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city=cities.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rank_list, null);

        }
        rank = ViewHolder.get(convertView, R.id.rank);
        cityName = ViewHolder.get(convertView, R.id.city);
        province = ViewHolder.get(convertView, R.id.province);
        ditailNum = ViewHolder.get(convertView, R.id.ditail_num);
        rank.setText(city.getRank());
        cityName.setText(city.getCity());
        province.setText(city.getProvince());
        ditailNum.setText(city.getDetailNum());
        return convertView;
    }
}
