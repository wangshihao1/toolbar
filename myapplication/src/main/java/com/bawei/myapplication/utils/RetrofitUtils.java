package com.bawei.myapplication.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

     private static volatile RetrofitUtils instance;
    private final Retrofit retrofit;

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Apis.BASE_URL)
                .build();
     }

    public static RetrofitUtils getInstance() {

        if (instance == null){
            synchronized (RetrofitUtils.class){
                if (instance == null){
                    instance = new RetrofitUtils();
                }
            }
        }

        return instance;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
