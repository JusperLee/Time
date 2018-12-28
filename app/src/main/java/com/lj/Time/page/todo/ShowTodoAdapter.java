package com.lj.Time.page.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lj.Time.R;
import com.lj.Time.db.DBManager;
import com.lj.Time.db.Todo;
import com.lj.Time.db.TodoDao;
import com.lj.Time.event.TodoChangedEvent;
import com.lj.Time.model.todo.ShowTodoEntity;
import com.lj.Time.page.base.BaseRecyclerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 显示待办事项列表
 */
public class ShowTodoAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.ViewHolder, ShowTodoEntity> {

    public ShowTodoAdapter(Context context, List<ShowTodoEntity> listData) {
        super(context, listData);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == 0 ?
                new TodoHolder(inflater.inflate(R.layout.adapter_show_todo, parent, false)) :
                new AddHolder(inflater.inflate(R.layout.adapter_add_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter.ViewHolder holder, int position) {
        if (holder instanceof TodoHolder) {
            TodoHolder todoHolder = (TodoHolder) holder;
            todoHolder.tvTime.setText(listData.get(position).getTodo().getDate());
            todoHolder.tvTitle.setText(listData.get(position).getTodo().getTitle());
            switch (listData.get(position).getLevel()) {
                case 1:
                    todoHolder.llRoot.setBackground(context.getDrawable(R.color.plan_card_bg_color1));
                    break;
                case 2:
                    todoHolder.llRoot.setBackground(context.getDrawable(R.color.plan_card_bg_color2));
                    break;
                case 3:
                    todoHolder.llRoot.setBackground(context.getDrawable(R.color.plan_card_bg_color3));
                    break;
                case 4:
                    todoHolder.llRoot.setBackground(context.getDrawable(R.color.plan_card_bg_color4));
                    break;
                case 5:
                    todoHolder.llRoot.setBackground(context.getDrawable(R.color.plan_card_bg_color5));
                    break;
                case 6:
                    todoHolder.llRoot.setBackground(context.getDrawable(R.color.plan_card_bg_color6));
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listData.get(position).getType();
    }

    class TodoHolder extends ViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        TodoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AddHolder extends ViewHolder {

        AddHolder(View itemView) {
            super(itemView);
        }
    }
}
