package cn.somputon.aircleaner.fragment;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import cn.somputon.aircleaner.Applic;
import cn.somputon.aircleaner.CallBack;
import cn.somputon.aircleaner.Constants;
import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.activity.AccountManageActivity;
import cn.somputon.aircleaner.activity.CreditMallActivity;
import cn.somputon.aircleaner.activity.DailyTaskActivity;
import cn.somputon.aircleaner.activity.DeviceManageActivity;
import cn.somputon.aircleaner.activity.OrderQueryActivity;
import cn.somputon.aircleaner.activity.ServiceQueryActivity;
import cn.somputon.aircleaner.utils.UploadAvatarUtil;
import cn.somputon.aircleaner.view.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    View v;
    String token = "";
    Handler handler;
    Button popShot;
    Button popAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_me, null);
        initView(v);
        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View v) {
        final CircleImageView civ = (CircleImageView) v.findViewById(R.id.avatar);
        new Thread() {
            @Override
            public void run() {
                token = Applic.getToken();
                RequestParams params = new RequestParams(Constants.HOST + "/getUserInfoByToken");
                params.addBodyParameter("Token", token);
                Callback.Cancelable cancelable = x.http().post(params, new CallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        changeLocalAvatar(Uri.parse(s));
                        LogUtil.v(s);
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(getActivity(), "sth. is wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
        civ.setOnClickListener(this);
        v.findViewById(R.id.daily_task).setOnClickListener(this);
        v.findViewById(R.id.tv_credit_mall).setOnClickListener(this);
        v.findViewById(R.id.account_management).setOnClickListener(this);
        v.findViewById(R.id.device_management).setOnClickListener(this);
        v.findViewById(R.id.order_query).setOnClickListener(this);
        v.findViewById(R.id.service_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daily_task:
                gotoDailyTask();
                break;
            case R.id.tv_credit_mall:
                gotoCreditMall();
                break;
            case R.id.account_management:
                gotoAccountManagement();
                break;
            case R.id.device_management:
                gotoDeviceManagement();
                break;
            case R.id.order_query:
                gotOrderQuery();
                break;
            case R.id.service_query:
                gotServiceQuery();
                break;
            case R.id.avatar:
                changeAvatar();
            default:
                break;
        }
    }

    PopupWindow popupWindow;

    private void changeAvatar() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.popwin_photo, null);
        //todo
        popShot = ((Button) view.findViewById(R.id.btn_pop_shot));
        popAlbum = ((Button) view.findViewById(R.id.btn_pop_album));
        popShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadAvatarUtil.openCamera(getActivity(), 2);
                if (popupWindow != null) popupWindow.dismiss();
            }
        });
        popAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadAvatarUtil.openAlbum(getActivity(), 1);
                if (popupWindow != null) popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(v, 0, 220, 200);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);


    }

    static String imagePath = "/my_photo.png";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    //     if (requestCode==-1) {
                    uri = data.getData();
                    if (uri != null) {
                        changeLocalAvatar(uri);
                        uploadAvatar(uri);
                    }
                    //     }

                    break;
                case 2:

                    //  if (resultCode!=-1) {
                    File temp = new File(Environment.getExternalStorageDirectory() + imagePath);
                    if (temp != null) {
                        changeLocalAvatar(Uri.fromFile(temp));
                        uploadAvatar(Uri.fromFile(temp));
                    }
                    //      }
                default:
                    break;

            }
        }
    }

    private void changeLocalAvatar(Uri uri) {

//        ContentResolver cr = getContext().getContentResolver();
//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Drawable drawable = UploadAvatarUtil.changeLocalAvatar(getActivity(), uri);
        ((CircleImageView) v.findViewById(R.id.avatar)).setImageDrawable(drawable);
    }

    private void uploadAvatar(Uri uri) {
        UploadAvatarUtil.uploadAvatar(getActivity(), uri);
  /*      String[] pojo = {MediaStore.Images.Media.DATA};
        //某些机型不支持
//        Cursor cursor = getActivity().managedQuery(uri, pojo, null, null, null);
        Cursor cursor=getActivity().getContentResolver().query(uri,pojo,null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            LogUtil.v(columnIndex+"");
            picPath = cursor.getString(columnIndex);
            LogUtil.v(picPath==null?"picPath is null":picPath);
            cursor.close();


        }
        if (picPath != null && (picPath.endsWith(".png") || picPath.endsWith(".PNG") || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
            new Thread() {
                @Override
                public void run() {
                    token = Applic.getToken();
                    RequestParams params = new RequestParams(Constants.HOST + "/updateUserAccount");
                    params.addBodyParameter("Token", token);
                    params.addBodyParameter("Avatar", picPath);
                    LogUtil.v(token+"+"+picPath);
                    Callback.Cancelable cancelable = x.http().post(params, new CallBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(getActivity(),"上传头像成功",Toast.LENGTH_SHORT).show();
                            LogUtil.v(s);
                        }

                        @Override
                        public void onError(Throwable throwable, boolean b) {
                            Toast.makeText(getActivity(),"sth. is wrong!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }.start();
        }*/

    }

    public void gotoDailyTask() {
        startActivity(DailyTaskActivity.class);
    }

    public void gotoCreditMall() {
        startActivity(CreditMallActivity.class);
    }

    public void gotoAccountManagement() {
        startActivity(AccountManageActivity.class);
    }

    public void gotoDeviceManagement() {
        startActivity(DeviceManageActivity.class);
    }

    public void gotOrderQuery() {
        startActivity(OrderQueryActivity.class);
    }

    public void gotServiceQuery() {
        startActivity(ServiceQueryActivity.class);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }
}
