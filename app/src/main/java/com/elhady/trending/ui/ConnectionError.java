package com.elhady.trending.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.elhady.trending.R;
import com.elhady.trending.databinding.ErrorConnectionBinding;
import com.elhady.trending.util.Util;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConnectionError extends AppCompatActivity {
    private ErrorConnectionBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.error_connection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRetryBtn();
            }
        });
        setRetryBtn();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setRetryBtn() {
        if (Util.isNetworkAvailable(getApplicationContext())){
            startActivity(new Intent(ConnectionError.this, MainActivity.class));
            finish();
        }

        else{
            Util.showSnack(binding.errorLayout,true,"No Internet Connection! ");
            binding.errorConnect.setVisibility(View.VISIBLE);
        }
    }
}
