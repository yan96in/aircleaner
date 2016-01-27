package cn.somputon.aircleaner.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.FileNotFoundException;

import cn.somputon.aircleaner.Applic;
import cn.somputon.aircleaner.CallBack;
import cn.somputon.aircleaner.Constants;

/**
 */
public class UploadAvatarUtil {
    Context context;
    Uri uri;
    UploadAvatarUtil uaUtil;

    public UploadAvatarUtil getInstance() {
        if (uaUtil == null) {
            uaUtil = new UploadAvatarUtil(context, uri);
        }
        return uaUtil;
    }

    public UploadAvatarUtil(Context context, Uri uri) {
        this.context = context;
        this.uri = uri;
    }

    public static void openAlbum(Context context, int code) {
        Intent intent = new Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_GET_CONTENT);
        //类型强转
        ((Activity) context).startActivityForResult(intent, code);
    }

    public static void openCamera(Context context, int code) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    .putExtra(MediaStore.EXTRA_OUTPUT, context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues()));
            // intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + imagePath)));
            ((Activity) context).startActivityForResult(intent, code);

        } else Toast.makeText(context, "no sdcard!", Toast.LENGTH_SHORT).show();
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
//        cursor.isNull(100);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }
    public static Drawable changeLocalAvatar(Context context, Uri uri) {
        ContentResolver cr = context.getContentResolver();
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new BitmapDrawable(bitmap);
    }

    public static void uploadAvatar(final Context context, Uri uri) {
        final String picPath = getRealPathFromURI(context, uri);
        if (picPath != null && (picPath.endsWith(".png") || picPath.endsWith(".PNG") || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
            new Thread() {
                @Override
                public void run() {
                    String token = Applic.getToken();
                    RequestParams params = new RequestParams(Constants.HOST + "/updateUserAccount");
                    params.addBodyParameter("Token", token);
                    params.addBodyParameter("Avatar", picPath);
                    params.setMultipart(true);
                    LogUtil.v(token + "+" + picPath);
                    Callback.Cancelable cancelable = x.http().post(params, new CallBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(context, "上传头像成功", Toast.LENGTH_SHORT).show();
                            LogUtil.v(s);
                        }

                        @Override
                        public void onError(Throwable throwable, boolean b) {
                            Toast.makeText(context, "sth. is wrong!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }.start();
        }
    }
}
