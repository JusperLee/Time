package com.lj.Time.contract.plan;

import com.lj.Time.model.plan.ShowPlanEntity;
import com.lj.Time.page.base.IBasePage;

import java.util.List;

/**
 * 显示计划界面的接口及业务逻辑约束类
 */

public interface IShowPlanContract {

    interface View extends IBasePage{
        void notifyPlanDataChanged(List<ShowPlanEntity> planList);
    }

    interface Presenter{
        void getPlanData();
        void onDestroy();
    }
}
