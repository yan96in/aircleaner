package cn.somputon.aircleaner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.somputon.aircleaner.R;

public class ProductServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_service);
    }

    public void applyService(){
        startActivity(ApplyServiceActivity.class);
    }

    public void queryService(){
        startActivity(ServiceQueryActivity.class);
    }

    public void complainOrSuggest(){
        startActivity(ComplainSuggestActivity.class);
    }

    public void startActivity(Class<?> cls){
        Intent intent=new Intent(ProductServiceActivity.this,cls);
        startActivity(intent);
    }
}
