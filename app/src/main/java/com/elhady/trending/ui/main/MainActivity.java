package com.elhady.trending.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.elhady.trending.R;
import com.elhady.trending.model.TrendModel;
import com.elhady.trending.ui.main.adapter.TrendAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TrendViewModel trendViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trendViewModel = ViewModelProviders.of(this).get(TrendViewModel.class);

        trendViewModel.getRepository();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final TrendAdapter adapter = new TrendAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        trendViewModel.trendMutableLiveData.observe(this, new Observer<List<TrendModel>>() {
            @Override
            public void onChanged(List<TrendModel> trendModels) {
                adapter.setTrendsList((ArrayList<TrendModel>) trendModels);
            }
        });


    }
}