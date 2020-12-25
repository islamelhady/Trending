package com.elhady.trending.repository;

import com.elhady.trending.model.RepoModel;
import com.elhady.trending.network.TrendingApiService;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private TrendingApiService trendingApiService;

    @Inject
    public Repository(TrendingApiService trendingApiService) {
        this.trendingApiService = trendingApiService;
    }

    public Observable<RepoModel> getTrending(Map<String, String> map) {
        return trendingApiService.getRepos(map);
    }
}
