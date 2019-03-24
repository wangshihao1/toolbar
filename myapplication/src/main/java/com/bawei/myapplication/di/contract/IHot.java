package com.bawei.myapplication.di.contract;


import com.bawei.myapplication.data.bean.HotVideoBean;

public interface IHot {

    public interface IHotView{

        void showHotView(HotVideoBean hotVideoBean);
    }

    public interface IHotPresenter<IHotView>{

        void atteachView(IHotView hotView);
        void detachView(IHotView hotView);
        void responseData(int page, int count);
    }

    public interface IHotModel{

        public interface CallBack{
            void onCallBakc(HotVideoBean hotVideoBean);
        }

        void requestData(int page, int count, CallBack callBack);
    }
}
