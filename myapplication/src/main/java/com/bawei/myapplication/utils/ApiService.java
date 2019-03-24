package com.bawei.myapplication.utils;


import com.bawei.myapplication.data.bean.HotVideoBean;
import com.bawei.myapplication.data.bean.NewVideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

   @GET("findHotMovieList")
    Observable<HotVideoBean> getHot(@Query("page") int page, @Query("count") int count);

   @GET("findReleaseMovieList")
    Observable<NewVideoBean> getNew(@Query("page") int page, @Query("count") int count);
}
