package cn.somputon.aircleaner.activity;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.app.ParamsBuilder;
import org.xutils.x;

import java.io.IOException;

import cn.somputon.aircleaner.Applic;
import cn.somputon.aircleaner.CallBack;
import cn.somputon.aircleaner.Constants;
import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.entity.UserInfo;


public class InputDetailActivity extends AppCompatActivity {
    int toWhere = 1;
    EditText etNikename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_detail);
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("完善信息");
        etNikename = (EditText) findViewById(R.id.et_nikename);
        getInfo();

    }


    public void showOrHide(View view) {

    }

    public void onClickSure(View v) {
        if (toWhere == 1) {
            updateInfo();
        } else {
            gotoMain();
        }

    }

    void getInfo() {
        RequestParams params = new RequestParams(Constants.HOST + "/getUserInfoByToken");
        params.addBodyParameter("Token", Applic.getToken());
        Callback.Cancelable cancelable = x.http().get(params, new CallBack<String>() {
            @Override
            public void onSuccess(String s) {
                LogUtil.v(s);
//                Gson gson = new Gson();
                UserInfo userInfo = JSON.parseObject(s, UserInfo.class);
                String nikename;
                nikename = userInfo.getBaseModle().getNickName();
                String phoneNumber = userInfo.getBaseModle().getMobile();
                if (nikename != null) {

                    LogUtil.v("nikename:" + nikename + ";phone:" + phoneNumber);
                    etNikename.setHint(nikename);
                }
                if (phoneNumber != null) {
                    ((EditText) findViewById(R.id.et_phonenum)).setHint(phoneNumber);
                }

            }
        });
//使用retrofit （未完成）
//        final service=null;
//        new Thread(){
//            @Override
//            public void run() {
//                Call<UserInfo> call=service.info();
//                try {
//                    UserInfo info=call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

    }
//    public interface get{
//        @GET("/getUserInfoByToken")
//        Call<UserInfo> info();
//    }


    private void updateInfo() {
        new Thread() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(Constants.HOST + "/updateUserAccount");
                params.addBodyParameter("Token", Applic.getToken());
                params.addBodyParameter("Nikename", etNikename.getText().toString() == null ? "ppp" : etNikename.getText().toString().trim());
//                params.addBodyParameter("Mobile","12345678900");
                Callback.Cancelable cancelable = x.http().post(params, new CallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtil.v("update success!--->" + s);
                    }
                });
            }
        }.start();
//使用okhttp
       /* new Thread() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormEncodingBuilder()
                        .add("Token", Applic.getToken())
                        .add("Nikename", etNikename.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url(Constants.HOST + "/updateUserAccount")
                        .post(formBody)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        LogUtil.v("okhttp:"+response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/


//        public interface sample{
//            @GET("updateUserAccount{}")
//        }
        //使用Volley
       /* RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.HOST + "/updateUserAccount", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtil.v("update success!--->" + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.v("update success!--->" + error);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Token", Applic.getToken());
                map.put("Nikename", "www"*//*etNikename.getText().toString() == null ? "" : etNikename.getText().toString()*//*);
                return map;
            }
        };
        queue.add(stringRequest);*/
    }

    private void gotoMain() {
        Intent intent = new Intent(InputDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}