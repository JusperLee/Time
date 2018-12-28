package com.lj.Time.presenter.todo;

import com.lj.Time.contract.todo.ICompletedTodoContract;
import com.lj.Time.db.DBManager;
import com.lj.Time.db.Todo;
import com.lj.Time.db.TodoDao;
import com.lj.Time.model.todo.ShowTodoEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 已完成的待办事项
 */
public class CompletedTodoPresenterImpl implements ICompletedTodoContract.Presenter {

    private ICompletedTodoContract.View view;


    private TodoDao mTodoDao;
    private Observable<Integer> updateTodoObservable;

    private List<ShowTodoEntity> listData = new ArrayList<>();

    public CompletedTodoPresenterImpl(ICompletedTodoContract.View view) {
        this.view = view;

        mTodoDao = DBManager.getInstance().getTodoDao();

        updateTodoObservable = Observable.create(e -> {
            listData.clear();
            List<Todo> todoList = mTodoDao.queryBuilder()
                    .where(TodoDao.Properties.Completed.eq(true))
                    .orderAsc(TodoDao.Properties.Date)
                    .list();
            if (!todoList.isEmpty()) {
                listData.addAll(Observable.fromIterable(todoList)
                        .map(todo -> {
                            ShowTodoEntity showTodo = new ShowTodoEntity();
                            showTodo.setType(0);
                            showTodo.setTodo(todo);
                            return showTodo;
                        })
                        .toList()
                        .blockingGet());
            }
            e.onNext(0);
            e.onComplete();
        });
    }

    @Override
    public void update() {
        updateTodoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> view.notifyTodoChanged(listData));
    }
}
