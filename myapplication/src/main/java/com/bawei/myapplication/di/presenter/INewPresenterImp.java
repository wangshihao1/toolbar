package com.bawei.myapplication.di.presenter;



import com.bawei.myapplication.data.bean.NewVideoBean;
import com.bawei.myapplication.di.contract.INew;
import com.bawei.myapplication.di.model.INewModelmp;

import java.lang.ref.SoftReference;

public class INewPresenterImp implements INew.INewPresenter<INew.INewView> {

    private INew.INewView newView;
    private INew.INewModel newModel;
    private SoftReference<INew.INewView> reference;

    @Override
    public void atteachView(INew.INewView newView) {
         this.newView =newView;
         newModel = new INewModelmp();
        reference = new SoftReference<>(newView);
    }

    @Override
    public void detachView(INew.INewView newView) {
           reference.clear();
    }

    @Override
    public void responseData(int page,int count) {
          newModel.requestData(page,count,new INew.INewModel.CallBack() {
              @Override
              public void onCallBakc(NewVideoBean newVideoBean) {
                  newView.showNewView(newVideoBean);
              }
          });
    }
}
