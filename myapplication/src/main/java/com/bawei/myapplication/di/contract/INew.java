package com.bawei.myapplication.di.contract;


import com.bawei.myapplication.data.bean.NewVideoBean;

public interface INew {

    public interface INewView{

        void showNewView(NewVideoBean newVideoBean);
    }

    public interface INewPresenter<IHoINewViewtView>{

        void atteachView(INewView newView);
        void detachView(INewView newView);
        void responseData(int page, int count);
    }

    public interface INewModel{

        public interface CallBack{
            void onCallBakc(NewVideoBean newVideoBean);
        }

        void requestData(int page, int count, CallBack callBack);
    }
}
