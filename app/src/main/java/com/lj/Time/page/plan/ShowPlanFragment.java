package com.lj.Time.page.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.Time.R;
import com.lj.Time.contract.plan.IShowPlanContract;
import com.lj.Time.event.PlanChangedEvent;
import com.lj.Time.event.ThemeChangedEvent;
import com.lj.Time.model.plan.ShowPlanEntity;
import com.lj.Time.page.base.BaseFragment;
import com.lj.Time.presenter.plan.ShowPlanPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 显示计划界面
 */

public class ShowPlanFragment extends BaseFragment implements IShowPlanContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private IShowPlanContract.Presenter showPlanPresenter;
    private List<ShowPlanEntity> planList = new ArrayList<>();
    private com.lj.Time.page.plan.ShowPlanAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_show;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        showPlanPresenter = new ShowPlanPresenterImpl(this);

        adapter = new com.lj.Time.page.plan.ShowPlanAdapter(mActivity, planList);

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            ShowPlanEntity plan = planList.get(position);
            if (plan.getType() == 2) {
                Intent intent = new Intent(mActivity, com.lj.Time.page.plan.AddPlanActivity.class);
                startActivity(intent);
            }
        });
        showPlanPresenter.getPlanData();
    }

    @Override
    public void notifyPlanDataChanged(List<ShowPlanEntity> list) {
        planList.clear();
        planList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ThemeChangedEvent event) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PlanChangedEvent event) {
        showPlanPresenter.getPlanData();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
