package com.elhady.trending.network;

import com.elhady.trending.model.RepoModel;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface TrendingApiService {

    @GET("/search/repositories")
    Observable<RepoModel> getRepos(@QueryMap Map<String, String> map);
}
