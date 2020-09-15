package com.elhady.trending.data;

import com.elhady.trending.model.TrendModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendClient {
    public static final String BASE_URL = "https://ghapi.huchen.dev/";
    private TrendInterface trendInterface;
    private static TrendClient INSTANCE;

    public TrendClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        trendInterface = retrofit.create(TrendInterface.class);
    }

    public static TrendClient getINSTANCE() {
        if (null == INSTANCE){
            INSTANCE = new TrendClient();
        }
        return INSTANCE;
    }

    public Call <List<TrendModel>> getTrending(){
        return trendInterface.getTrending();
    }

}
