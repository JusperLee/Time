package com.lj.Time.contract.plan;

import com.lj.Time.db.ClockPlan;
import com.lj.Time.db.RationPlan;
import com.lj.Time.model.plan.EditPlanDataEntity;
import com.lj.Time.page.base.IBasePage;

/**
 * 修改计划接口约束
 */
public interface IEditPlanContract {

    interface View extends IBasePage{
        void showRationPlan();
        void showClockPlan();
        void fillRationPlanData(RationPlan plan);
        void fillClockPlanData(ClockPlan plan);

    }

    interface Presenter{
        void initDate(long planId, int planType);
        void updatePlan(EditPlanDataEntity editData);
        void deletePeriod();
        void deletePlan();
        void onDestroy();
    }
}
