package com.ngocdai.tikitest;

import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerText);
        recyclerView.setHasFixedSize(true);
        data = new String[]{
                "xiaomi",
                "bitis hunter",
                "bts",
                "balo",
                "bitis hunter x",
                "tai nghe",
                "harry potter",
                "anker",
                "iphone",
                "balo nữ",
                "nguyễn nhật ánh",
                "đắc nhân tâm",
                "ipad",
                "senka",
                "tai nghe bluetooth",
                "son",
                "maybelline",
                "laneige",
                "kem chống nắng",
                "anh chính là thanh xuân của em"
        };

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.getRecycledViewPool().setMaxRecycledViews(Type, 0);



        // specify an adapter (see also next example)
        mAdapter = new MainAdapter(data);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(View.KEEP_SCREEN_ON, data.length);
        mAdapter.notifyDataSetChanged();
    }
}
