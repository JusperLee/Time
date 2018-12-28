package com.lj.Time.contract.plan;

import com.lj.Time.db.ClockPlan;
import com.lj.Time.db.ClockRecord;
import com.lj.Time.page.base.IBasePage;

import java.util.List;

/**
 * 打卡计划详情
 */
public interface IClockPlanDetailContract {

    interface View extends IBasePage{
        void updateClockPlan(ClockPlan clockPlan);
        void notifyRecordChanged(List<ClockRecord> list);
    }

    interface Presenter{
        void clearCache();
        void update(String month);
    }
}
