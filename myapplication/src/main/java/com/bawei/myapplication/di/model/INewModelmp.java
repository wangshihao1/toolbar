package com.bawei.myapplication.di.model;

import android.util.Log;

import com.bawei.myapplication.data.bean.NewVideoBean;
import com.bawei.myapplication.di.contract.INew;
import com.bawei.myapplication.utils.ApiService;
import com.bawei.myapplication.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class INewModelmp implements INew.INewModel {
    @Override
    public void requestData(int page,int count,final CallBack callBack) {
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        Observable<NewVideoBean> observable = apiService.getNew(page,count);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewVideoBean>() {
                    @Override
                    public void accept(NewVideoBean newVideoBean) throws Exception {
                        callBack.onCallBakc(newVideoBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("WSH",throwable.getMessage()+"IXA");
                    }
                });
    }
}
