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
public class MeFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, null);

    }
    // TODO: 2015/12/5 设置监听
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void meirirenwu(){
        Toast.makeText(getActivity(),"tmep test",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meirirenwu:
                meirirenwu();
                break;
            default:break;
        }
    }
}
