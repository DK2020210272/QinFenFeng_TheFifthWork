package com.example.qinfenfeng_the_fifthwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RvData> list = new ArrayList<>();
    private RvAdapter rvAdapter = new RvAdapter(list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //适配状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.holo_blue_dark));

        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        recyclerView = (RecyclerView) findViewById(R.id.rv_demo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);

        //创建
        for(int i=1;i<=50;i++){
            list.add(new RvData("NUM"+i));
        }
        rvAdapter.notifyDataSetChanged();
    }
}