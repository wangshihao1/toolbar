package com.bawei.myapplication.di.model;

import android.util.Log;

import com.bawei.myapplication.data.bean.HotVideoBean;
import com.bawei.myapplication.di.contract.IHot;
import com.bawei.myapplication.utils.ApiService;
import com.bawei.myapplication.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IHotModelmp implements IHot.IHotModel {
    @Override
    public void requestData(int page,int count,final CallBack callBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        Observable<HotVideoBean> observable = apiService.getHot(page, count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotVideoBean>() {
                    @Override
                    public void accept(HotVideoBean hotVideoBean) throws Exception {
                        callBack.onCallBakc(hotVideoBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("WSH",throwable.getMessage()+"IXA");
                    }
                });
    }
}
