package com.elhady.trending.ui.activity;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elhady.trending.OnClickError;
import com.elhady.trending.data.TrendClient;
import com.elhady.trending.model.TrendModel;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendViewModel extends ViewModel {
    MutableLiveData<List<TrendModel>> trendMutableLiveData = new MutableLiveData<>();
    final static String TAG = "viewModel";


    public void getRepository() {
        Single<List<TrendModel>> observable = TrendClient.getINSTANCE().getTrending()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(o -> trendMutableLiveData.setValue(o), e -> Log.d(TAG, "getRepository"+e));

    }

}
