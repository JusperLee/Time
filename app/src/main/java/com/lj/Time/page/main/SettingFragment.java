package com.lj.Time.page.main;

import android.Manifest;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.lj.Time.event.ThemeChangedEvent;
import com.zhangke.qrcodeview.QRCodeView;
import com.lj.Time.R;
import com.lj.Time.common.APPConfig;
import com.lj.Time.event.PosterHideChangedEvent;
import com.lj.Time.event.ThemeChangedEvent;

import com.lj.Time.page.base.BaseFragment;
import com.lj.Time.page.other.AboutActivity;


import com.lj.Time.widget.RippleAnimationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 设置界面
 */

public class SettingFragment extends BaseFragment {

    @BindView(R.id.nested_root_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.switch_compat)
    SwitchCompat switchCompat;

    Unbinder unbinder;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);

        initSwitch();

    }

    private void initSwitch() {
        if (APPConfig.getTheme() == 0) {
            mActivity.setTheme(R.style.NightTheme);
            switchCompat.setChecked(true);
            switchCompat.setText("夜间");
        } else {
            mActivity.setTheme(R.style.DayTheme);
            switchCompat.setChecked(false);
            switchCompat.setText("日间");
        }
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            changeTheme();
        });
    }

    private void changeTheme() {
        if (APPConfig.getTheme() == 0) {
            APPConfig.setTheme(1);
            mActivity.setTheme(R.style.DayTheme);
            switchCompat.setText("日间");
        } else {
            APPConfig.setTheme(0);
            mActivity.setTheme(R.style.NightTheme);
            switchCompat.setText("夜间");
        }
        RippleAnimationView.create(switchCompat).setDuration(600).start();
        EventBus.getDefault().post(new ThemeChangedEvent());
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PosterHideChangedEvent event) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
