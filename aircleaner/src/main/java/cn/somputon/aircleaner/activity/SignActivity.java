package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;

import cn.somputon.aircleaner.R;
import cn.yan96in.signcalendar.MonthSignData;
import cn.yan96in.signcalendar.SignCalendar;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    SignCalendar signCalendar;
    Button button;
    ArrayList<Date> signDates3;
    ArrayList<MonthSignData> monthDatas;
    Date today;
    MonthSignData monthData3;
    Intent intent;
    boolean signed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        signCalendar = (SignCalendar) findViewById(R.id.my_sign_calendar);
        monthDatas = new ArrayList<>();
        monthData3 = new MonthSignData();
        today = new Date();
        int year = today.getYear();
        int month = today.getMonth();
        monthData3.setYear(year + 1900);
        monthData3.setMonth(month);
        signDates3 = new ArrayList<>();
        Date date31 = new Date(year, month, 1);
        Date date32 = new Date(year, month, 2);
        Date date33 = new Date(year, month, 10);
        signDates3.add(date31);
        signDates3.add(date32);
        signDates3.add(date33);
        monthData3.setSignDates(signDates3);
        monthDatas.add(monthData3);
        signCalendar.setToday(today);
        signCalendar.setSignDatas(monthDatas);
        button = ((Button) findViewById(R.id.button));
        button.setOnClickListener(this);
        intent = getIntent();
       signed=intent.getExtras().getBoolean("sign");
        if (signed == true) {
            isSigned();
        }else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                signDates3.add(today);
                monthData3.setSignDates(signDates3);
                monthDatas.add(monthData3);
                signCalendar.setSignDatas(monthDatas);
                isSigned();
                finish();
        }
    }


    private void isSigned() {
        button.setText("已签到");
        button.setClickable(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                button.setBackgroundTintList(getColorStateList(R.color.gray));
            }
        intent.putExtra("sign", true);
        setResult(0, intent);
    }


}
