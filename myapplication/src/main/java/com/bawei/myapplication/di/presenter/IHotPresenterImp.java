package com.bawei.myapplication.di.presenter;



import com.bawei.myapplication.data.bean.HotVideoBean;
import com.bawei.myapplication.di.contract.IHot;
import com.bawei.myapplication.di.model.IHotModelmp;

import java.lang.ref.SoftReference;

public class IHotPresenterImp implements IHot.IHotPresenter<IHot.IHotView> {

    private IHot.IHotModel hotModel;
    private IHot.IHotView hotView;
    private SoftReference<IHot.IHotView> reference;


    @Override
    public void atteachView(IHot.IHotView iHotView) {
            this.hotView =iHotView;
            hotModel = new IHotModelmp();
            reference = new SoftReference<>(hotView);
    }

    @Override
    public void detachView(IHot.IHotView iHotView) {
            reference.clear();
    }

    @Override
    public void responseData(int page,int count) {
            hotModel.requestData(page,count,new IHot.IHotModel.CallBack() {
                @Override
                public void onCallBakc(HotVideoBean hotVideoBean) {
                    hotView.showHotView(hotVideoBean);
                }
            });
    }
}
