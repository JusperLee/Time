package com.lj.Time.contract.todo;

import com.lj.Time.db.Todo;
import com.lj.Time.model.todo.ShowTodoEntity;
import com.lj.Time.page.base.IBasePage;

import java.util.List;

/**
 * 待办事项
 */
public interface IShowTodoContract {

    interface View extends IBasePage{
        void notifyTodoChanged(List<ShowTodoEntity> todoList);
    }

    interface Presenter{
        void update();
    }
}
