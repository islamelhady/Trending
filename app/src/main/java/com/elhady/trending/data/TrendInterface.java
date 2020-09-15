package com.elhady.trending.data;

import com.elhady.trending.model.TrendModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrendInterface {
    @GET("repositories")
    public Call <List<TrendModel>> getTrending();
}
