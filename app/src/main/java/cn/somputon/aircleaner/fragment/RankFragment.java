package cn.somputon.aircleaner.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.somputon.aircleaner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, null);
        title = ((TextView) view.findViewById(R.id.tv_title));
        title.setText("排名");
        return view;
    }


}
