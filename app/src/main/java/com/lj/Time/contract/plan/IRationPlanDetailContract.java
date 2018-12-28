package com.lj.Time.contract.plan;

import com.lj.Time.db.RationPlan;
import com.lj.Time.db.RationRecord;
import com.lj.Time.page.base.IBasePage;

import java.util.List;

/**
 * 定量计划详情
 */
public interface IRationPlanDetailContract {

    interface View extends IBasePage{
        void fillRationPlanData(RationPlan rationPlan);
        void notifyDataChanged(List<RationRecord> list);
    }

    interface Presenter{
        void update();
    }
}
