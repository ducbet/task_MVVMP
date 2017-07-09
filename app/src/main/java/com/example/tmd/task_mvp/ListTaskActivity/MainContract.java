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
        void onShowInputDialog();
        void onAddTaskSuccess(Task task);
        void onAddTaskFailed(String msg);
        void onGetAllTaskSuccess(List<Task> listTasks);
        void onGetAllTaskFailed(String msg);
    }

    interface Presenter extends BasePresenter {
        void addTask(Task task);
        void editTask();
        void deleteTask();
    }
}
