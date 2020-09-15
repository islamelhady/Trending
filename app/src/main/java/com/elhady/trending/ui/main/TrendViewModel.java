package com.elhady.trending.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elhady.trending.data.TrendClient;
import com.elhady.trending.model.TrendModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendViewModel extends ViewModel {
    MutableLiveData <List<TrendModel>> trendMutableLiveData = new MutableLiveData<>();

    public void getRepository(){
        TrendClient.getINSTANCE().getTrending().enqueue(new Callback<List<TrendModel>>() {
            @Override
            public void onResponse(Call<List<TrendModel>> call, Response<List<TrendModel>> response) {
                trendMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TrendModel>> call, Throwable t) {

            }
        });

    }

}
