package com.lj.Time.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.lj.Time.db.DBManager;
import com.lj.Time.db.RationPlan;
import com.lj.Time.db.RationPlanDao;
import com.lj.Time.event.PlanChangedEvent;
import com.lj.Time.presenter.plan.PlanHelper;
import com.lj.Time.util.DateUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 计算计划数据
 */
public class ProcessPlanService extends IntentService {

    private RationPlanDao rationPlanDao;
    private String curDate = DateUtils.getCurrentDate("yyyy-MM-dd");
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public ProcessPlanService() {
        super("ProcessPlanService");
        rationPlanDao = DBManager.getInstance().getRationPlanDao();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        List<RationPlan> rationPlanList = rationPlanDao.loadAll();
        if (rationPlanList != null && !rationPlanList.isEmpty()) {
            for (RationPlan plan : rationPlanList) {
                processRationPlan(plan);
            }
        }
        DBManager.getInstance().clear();
        EventBus.getDefault().post(new PlanChangedEvent());
    }

    private void processRationPlan(RationPlan plan) {
        if (plan.getPeriodIsOpen() &&
                (TextUtils.isEmpty(plan.getLastUpdatePeriodDate()) ||
                        !PlanHelper.isCurPeriod(plan.getPeriodPlanType(), plan.getLastUpdatePeriodDate(), "yyyy-MM-dd"))) {
            //短期计划存在且短期计划上次更新时间为空或者上次更新时间不在当前周期
            plan.setPeriodPlanTarget(Double.valueOf(decimalFormat.format(PlanHelper.getPeriodTarget(plan, plan.getPeriodPlanType()))));
            plan.setLastUpdatePeriodDate(curDate);
            rationPlanDao.update(plan);
        }
    }
}
