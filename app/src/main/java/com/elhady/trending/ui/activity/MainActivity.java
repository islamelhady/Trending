package com.elhady.trending.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.elhady.trending.R;
import com.elhady.trending.model.TrendModel;
import com.elhady.trending.ui.adapter.TrendAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TrendViewModel trendViewModel;
    private SwipeRefreshLayout refreshLayout;
    private TrendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swipeRefreshLayout);
        trendViewModel = ViewModelProviders.of(this).get(TrendViewModel.class);


        trendViewModel.getRepository();
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new TrendAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        callRecyclerview();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                trendViewModel.getRepository();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void callRecyclerview() {
        trendViewModel.trendMutableLiveData.observe(this, new Observer<List<TrendModel>>() {
            @Override
            public void onChanged(List<TrendModel> trendModels) {
                adapter.setTrendsList((ArrayList<TrendModel>) trendModels);
            }
        });
    }
}