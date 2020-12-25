package com.elhady.trending.viewmodel;


import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elhady.trending.model.ItemModel;
import com.elhady.trending.model.RepoModel;
import com.elhady.trending.repository.Repository;
import com.elhady.trending.util.Util;

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
                .map(RepoModel::getItems)
                .subscribe(itemModels -> {
                            if(itemModels !=null && !itemModels.isEmpty()){
                                reposList.postValue(itemModels);
                                error.postValue(false);
                            }
                        }, error -> this.error.postValue(true)
                )
        );
    }


    private void initMap(){
        map.put("q","created:>");
        map.put("sort","stars");
        map.put("order","desc");
        map.put("page",String.valueOf(Util.page));
    }

    public void onClear(){
        if(disposable != null ) disposable.dispose();
    }

}
