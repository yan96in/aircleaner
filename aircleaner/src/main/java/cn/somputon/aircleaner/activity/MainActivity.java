package cn.somputon.aircleaner.activity;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.fragment.MeFragment;
import cn.somputon.aircleaner.fragment.RankFragment;
import cn.somputon.aircleaner.fragment.SceneFragment;
import cn.somputon.aircleaner.fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private DrawerLayout drawerLayout;
    private View mainContent;
    private View drawerContent;
    ViewPager pager;
    private ActionBarDrawerToggle toggle;
    //private RadioGroup radioGroup;
    private RadioButton rbWeater, rbScene, rbRank, rbMe;
    private FragmentManager fragmentManager;

    // private boolean isMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        initData();
        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initData() {
        // isMain=true;
    }

    private void initView() {
        initPager();
        drawerLayout = ((DrawerLayout) findViewById(R.id.container_drawer));
        drawerContent = findViewById(R.id.drawer_main);
        mainContent = findViewById(R.id.container_root);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                //必须有这句不知道为啥
                supportInvalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(toggle);


        fragmentManager = getSupportFragmentManager();

        // radioGroup= ((RadioGroup) findViewById(R.id.rg_bottom));
        ((RadioGroup) findViewById(R.id.rg_bottom)).setOnCheckedChangeListener(this);
    }

    private void initPager() {
        pager= ((ViewPager) findViewById(R.id.viewpager));
        final List<View> views = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.pager1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.pager1, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.pager1, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }
            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(views.get(position));
            }
            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            drawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    supportInvalidateOptionsMenu();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_weather:
                changeFragment(new WeatherFragment());
                break;
            case R.id.rb_scene:
                changeFragment(new SceneFragment());
                break;
            case R.id.rb_rank:
                changeFragment(new RankFragment());
                break;
            case R.id.rb_me:
                changeFragment(new MeFragment());
                //((TextView) findViewById(R.id.tv_title)).setText("排名");
                break;
            default:
                break;
        }

    }

    String backStack;

    public void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction;

//        if(isMain){
        fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(backStack);
//        }else {
//            //todo 把isMain合理的置成true,正确实现两次back键返回冲突的逻辑;
//            fragmentTransaction = fragmentManager.beginTransaction();
//        }
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commit();
    }

   /* 按两次退出
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
