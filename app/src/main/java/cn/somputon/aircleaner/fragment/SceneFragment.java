package cn.somputon.aircleaner.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.somputon.aircleaner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceneFragment extends Fragment {


    public SceneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scene, container, false);
    }


}
