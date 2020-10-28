package com.elhady.trending.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.TextViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elhady.trending.OnClickError;
import com.elhady.trending.R;
import com.elhady.trending.model.TrendModel;
import com.elhady.trending.ui.adapter.TrendAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ShimmerFrameLayout mShimmerViewContainer;
    private TrendViewModel trendViewModel;
    private SwipeRefreshLayout refreshLayout;
    private TrendAdapter adapter;
    private RelativeLayout errorLayout;
    private ImageView errorImg;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShimmerViewContainer = findViewById(R.id.shimmerFrameLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        refreshLayout = findViewById(R.id.swipeRefreshLayout);
        errorLayout = findViewById(R.id.error_layout);
        errorImg = findViewById(R.id.error_img);
        errorTitle = findViewById(R.id.erorr_title);
        errorMessage = findViewById(R.id.erorr_message);
        btnRetry = findViewById(R.id.btn_retry);
        trendViewModel = ViewModelProviders.of(this).get(TrendViewModel.class);


        trendViewModel.getRepository();
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new TrendAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        setLoadSwipeRefresh();
        callRecyclerview();
        adapter.notifyDataSetChanged();
        mShimmerViewContainer.stopShimmerAnimation();
    }

    private void setLoadSwipeRefresh() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mShimmerViewContainer.startShimmerAnimation();
                trendViewModel.getRepository();
                callRecyclerview();
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
                mShimmerViewContainer.stopShimmerAnimation();

            }
        });

    }

    // handle configuration changes (like rotation)
    private void callRecyclerview() {
        trendViewModel.trendMutableLiveData.observe(this, new Observer<List<TrendModel>>() {
            @Override
            public void onChanged(List<TrendModel> trendModels) {
                adapter.setTrendsList((ArrayList<TrendModel>) trendModels);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_start:
                Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_name:
                Toast.makeText(this, "NAME", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}