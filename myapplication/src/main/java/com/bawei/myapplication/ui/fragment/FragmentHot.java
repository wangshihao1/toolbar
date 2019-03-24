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
import com.bawei.myapplication.data.bean.HotVideoBean;
import com.bawei.myapplication.di.contract.IHot;
import com.bawei.myapplication.di.presenter.IHotPresenterImp;
import com.bawei.myapplication.ui.adapter.HotAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentHot extends Fragment implements IHot.IHotView {

    @BindView(R.id.xr)
    XRecyclerView xr;
    Unbinder unbinder;
    private HotAdapter hotAdapter;
    private IHotPresenterImp hotPresenterImp;
    private int page = 1;
    private int count = 10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        hotPresenterImp = new IHotPresenterImp();
        hotPresenterImp.atteachView(this);
        hotPresenterImp.responseData(page,count);
        xr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                hotPresenterImp.responseData(page,count);

            }

            @Override
            public void onLoadMore() {
                hotPresenterImp.responseData(page,count);

                Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void showHotView(HotVideoBean hotVideoBean) {
        List<HotVideoBean.ResultBean> hotVideoBeanResult = hotVideoBean.getResult();
        hotAdapter = new HotAdapter();
        hotAdapter.setData(getActivity(),hotVideoBeanResult);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        xr.setLayoutManager(manager);
        xr.setAdapter(hotAdapter);
        xr.refreshComplete();
        xr.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        hotPresenterImp.detachView(this);
    }
}
