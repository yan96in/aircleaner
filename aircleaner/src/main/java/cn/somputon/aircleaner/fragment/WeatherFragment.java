package cn.somputon.aircleaner.fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.xutils.common.util.LogUtil;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.entity.CityFromJson;
import cn.somputon.aircleaner.utils.HttpManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    public WeatherFragment() {
        // Required empty public constructor
    }

    String httpUrl = "http://apis.baidu.com/apistore/aqiservice/citylist";
    String httpArg = "";
    Message message;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        final MyHandler handler = new MyHandler(this);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                message = handler.obtainMessage();
                message.what = 100;
                message.obj = HttpManager.request(httpUrl, httpArg);
                handler.sendMessage(message);
                Looper.loop();
            }
        }.start();

        // Inflate the layout for this fragment
        return view;
    }




    static class MyHandler extends Handler {
        WeakReference<WeatherFragment> wtv;
        TextView tv;

        public MyHandler(WeatherFragment context) {
            wtv = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100 && wtv != null) {
                //JSON.parseObject(msg.obj.toString());
//                LogUtil.v(msg.obj.toString());
                tv = (TextView) wtv.get().view.findViewById(R.id.test);
                tv.setText(msg.obj.toString());
                CityFromJson ctj = JSON.parseObject(msg.obj.toString(), CityFromJson.class);
                //convertToCC(ctj.getRetData().getCitylist());
                String[] strings = ctj.getRetData().getCitylist().split(",");
                Arrays.sort(strings, new SortedCity());
                HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
                try {
                    String[] test = PinyinHelper.toHanyuPinyinString(ctj.getRetData().getCitylist(), format, "").split(",");
                    Arrays.sort(test);

                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }

                // LogUtil.v(String.valueOf(strings.length));
//                ArrayList<String> list=new ArrayList();
                for (String si : strings) {
                    // LogUtil.v(si);
//                    list.add(si);
                    LogUtil.v("array:" + si);

                  /*  char[] chars=si.toCharArray();
                    for (char a:
                    chars){
                        String[] b=PinyinHelper.toHanyuPinyinStringArray(a);
                        for (String ib:b
                             ) {
                      //      LogUtil.v(ib);
                        }
                    }*/
                }
//                Collections.sort(list,new SortedCity());
//                for (String s:list
//                     ) {
//                    LogUtil.v(s);
//                }
                String after = convertToCC("\\u9f99\\u5ca9");
                LogUtil.v(after);
            }
        }
    }

    static class SortedCity implements Comparator<String> {

        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

        @Override
        public int compare(String a, String b) {
            if (cmp.compare(a, b) > 0) {
                return 1;
            } else if (cmp.compare(a, b) < 0) {
                return -1;
            }
            return 0;
        }
    }

    //
    static public String convertToCC(String dataStr) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end;

        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr;
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);

            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            sb.append(Character.toString(letter));
//            sb.append(Character.toString(letter));
            start = end;
        }
        return sb.toString();
    }
}
