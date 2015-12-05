package cn.somputon.aircleaner.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.somputon.aircleaner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.fragment_rank,null);
        return view;
    }


}
