package com.elhady.trending.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elhady.trending.model.ItemModel;
import com.elhady.trending.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class TrendingViewModel extends ViewModel {
    private static final String TAG = "TrendingViewModel";

    private Repository repository;
    private MutableLiveData<List<ItemModel>> reposList = new MutableLiveData<>();
    private MutableLiveData<Boolean> error = new MutableLiveData<>();
    private static Map<String, String> map = new HashMap<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private int PAGE_COUNT = 1;


    @ViewModelInject
    public TrendingViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<ItemModel>> getTrendingList() {
        return reposList;
    }

    public MutableLiveData<Boolean> getError() {
        return error;
    }


    public void loadTrendingList(String date){
        initMap();
        if( date != null && !date.isEmpty()) map.put("q","created:>"+date);
        disposable.add(repository.getTrending(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->reposList.setValue(result.getItems()),
                        error-> Log.e(TAG, "getPopularMovies: " + error.getMessage() ))
        );
    }


    private void initMap(){
        map.put("q","created:>");
        map.put("sort","stars");
        map.put("order","desc");
        map.put("page",String.valueOf(PAGE_COUNT));
    }

    public void onClear(){
        if(disposable != null ) disposable.dispose();
    }

}
