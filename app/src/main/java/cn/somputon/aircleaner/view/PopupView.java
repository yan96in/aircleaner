package cn.somputon.aircleaner.view;

import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.somputon.aircleaner.R;


public class PopupView {
    private Context context;

    public PopupView(Context context) {
        this.context = context;
    }

    public void popup(String hint) {
        LinearLayout layout = new LinearLayout(context);
        View popupView = layout.inflate(context, R.layout.a_base_popup_window, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(popupView);
        //设置提示内容
        TextView btnPopup = (TextView) popupView.findViewById(R.id.popup_window_btn);
        btnPopup.setText(hint);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }
}
