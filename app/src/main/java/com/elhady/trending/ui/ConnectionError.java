package com.elhady.trending.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.elhady.trending.R;
import com.elhady.trending.util.Util;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConnectionError extends AppCompatActivity {
    LinearLayout mainLayout;
    RelativeLayout error_connect;
    Button retryBtn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_connection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainLayout = findViewById(R.id.error_layout);
        error_connect = findViewById(R.id.error_connect);
        retryBtn = findViewById(R.id.btn_retry);

        retryBtn.setOnClickListener(new View.OnClickListener() {
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
            Util.showSnack(mainLayout,true,"No Internet Connection! ");
            error_connect.setVisibility(View.VISIBLE);
        }
    }
}
