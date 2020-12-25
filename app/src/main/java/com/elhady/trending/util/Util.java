package com.elhady.trending.util;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.elhady.trending.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static int page = 1;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getFormattedDateOneMonthAgo(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date currentDate = calendar.getTime();

        return dateFormat.format(currentDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Test for connection
        NetworkCapabilities mCapabilities = mConnectivityManager.getNetworkCapabilities(mConnectivityManager.getActiveNetwork());
        return mCapabilities != null &&
                (mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        mCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }

    // Showing the status in SnackBar
    public static void showSnack(View view, boolean isError, String message) {
        int color = Color.WHITE;
        if (isError) color = Color.RED;

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        View snackBarView = snackbar.getView();
        TextView textView = snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

}
