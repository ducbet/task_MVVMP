package com.example.tmd.task_mvp.ListTaskActivity;

import com.example.tmd.task_mvp.Task.Model.Task;
import com.example.tmd.task_mvp.util.BasePresenter;
import com.example.tmd.task_mvp.util.BaseView;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */
public interface MainContract {
    interface View extends BaseView {
        void onShowAddTaskInputDialog();
        void onShowEditTaskInputDialog(Task task);
        void onChangeCheckBox(Task task);
        void onShowDeleteTaskConfirmDialog(Task task);
        void onAddTaskSuccess(Task task);
        void onFailed(String msg);
        void onGetAllTaskSuccess(List<Task> listTasks);
        void onEditTaskSuccess(Task task);
        void onDeleteTaskSuccess(Task task);
    }

    interface Presenter extends BasePresenter {
        void addTask(Task task);
        void editTask(Task task);
        void deleteTask(Task task);
    }
}
