package com.elhady.trending.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.elhady.trending.util.Util;
import com.elhady.trending.R;
import com.elhady.trending.adapter.TrendingAdapter;
import com.elhady.trending.model.ItemModel;
import com.elhady.trending.viewmodel.TrendingViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TrendingViewModel viewModel;
    private TrendingAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerFrameLayout;
    private CoordinatorLayout coordinatorLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);
        coordinatorLayout = findViewById(R.id.main_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        title.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        viewModel = new ViewModelProvider(this).get(TrendingViewModel.class);

        initView();
        setUpRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        observeData();
        errorData();

        updateRefreshLayout(true);
        displaySnackBar(false, "Loading...");
        loadTrendingList();
    }

    private void initView() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        shimmerFrameLayout.startShimmerAnimation();
    }

    private void observeData() {
        viewModel.getTrendingList().observe(this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(List<ItemModel> modelList) {
                adapter.setTrendsList(modelList);
                updateRefreshLayout(false);
            }
        });
    }

    private void errorData(){
        viewModel.getError().observe(this, isError -> {
            if (isError) {
                displaySnackBar(true, "Can't load more Github Repository");
                updateRefreshLayout(false);
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        });
    }

    private void loadTrendingList() {
        viewModel.loadTrendingList(Util.getFormattedDateOneMonthAgo());
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TrendingAdapter();
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRefresh() {
        Util.page = 1;
        adapter.clearData();
        if (Util.isNetworkAvailable(this)) {
            loadTrendingList();
        } else {
            updateRefreshLayout(false);
            showError();
            displaySnackBar(true, "No Internet Connection ");
        }
    }

    private void updateRefreshLayout(boolean refresh) {
        swipeRefreshLayout.setRefreshing(refresh);
    }

    private void showError() {
        startActivity(new Intent(MainActivity.this, ConnectionError.class));
        finish();
    }

    private void displaySnackBar(boolean isError, String message) {
        Util.showSnack(coordinatorLayout, isError, message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trend_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_stars:
                displaySnackBar(true, "Sort by stars");
                return true;
            case R.id.menu_name:
                displaySnackBar(true, "Sort by name");
                return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onClear();
    }
}