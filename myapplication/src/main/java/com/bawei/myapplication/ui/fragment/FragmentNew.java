package com.bawei.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bawei.myapplication.R;
import com.bawei.myapplication.data.bean.NewVideoBean;
import com.bawei.myapplication.di.contract.INew;
import com.bawei.myapplication.di.presenter.INewPresenterImp;
import com.bawei.myapplication.ui.adapter.NewAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentNew extends Fragment implements INew.INewView {

    @BindView(R.id.xr)
    XRecyclerView xr;
    Unbinder unbinder;
    private NewAdapter newAdapter;
    private INewPresenterImp newPresenterImp;
    private int page = 1;
    private int count = 20;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new, container, false);
        unbinder = ButterKnife.bind(this, view);
        newPresenterImp = new INewPresenterImp();
        newPresenterImp.atteachView(this);
        newPresenterImp.responseData(page,count);
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                newPresenterImp.responseData(page,count);

            }

            @Override
            public void onLoadMore() {
                newPresenterImp.responseData(page,count);

                Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void showNewView(NewVideoBean newVideoBean) {
        List<NewVideoBean.ResultBean> hotVideoBeanResult = newVideoBean.getResult();
        newAdapter = new NewAdapter();
        newAdapter.setData(getActivity(),hotVideoBeanResult);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        xr.setLayoutManager(manager);
        xr.setAdapter(newAdapter);
        xr.refreshComplete();
        xr.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        newPresenterImp.detachView(this);
    }
}
