package cn.somputon.aircleaner.activity;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cn.somputon.aircleaner.R;
import cn.somputon.aircleaner.fragment.MeFragment;
import cn.somputon.aircleaner.fragment.RankFragment;
import cn.somputon.aircleaner.fragment.SceneFragment;
import cn.somputon.aircleaner.fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private DrawerLayout drawerLayout;
    private View mainContent;
    private View drawerContent;
    private ActionBarDrawerToggle toggle;
    //private RadioGroup radioGroup;
    private RadioButton rbWeater, rbScene, rbRank, rbMe;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void initView() {
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
                ((TextView) findViewById(R.id.tv_title)).setText("排名");
                break;
            default:
                break;
        }

    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commit();
    }
}
