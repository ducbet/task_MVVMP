package com.example.tmd.task_mvp.ListTaskActivity;

import com.example.tmd.task_mvp.ViewModel.Task;
import com.example.tmd.task_mvp.ViewModel.TaskViewModel;
import com.example.tmd.task_mvp.util.BasePresenter;
import com.example.tmd.task_mvp.util.BaseView;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */
public interface MainContract {
    interface MainViewModel extends BaseView {
        void onShowAddTaskInputDialog();
        void onShowEditTaskInputDialog(TaskViewModel taskViewModel);
        void onChangeCheckBox(TaskViewModel taskViewModel);
        void onShowDeleteTaskConfirmDialog(TaskViewModel taskViewModel);
        void onAddTaskSuccess(Task task);
        void onShowMsgFailed(String msg);
        void onGetAllTaskSuccess(List<Task> listTasks);
        void onEditTaskSuccess(TaskViewModel taskViewModel);
        void onDeleteTaskSuccess(TaskViewModel taskViewModel);
    }

    interface Presenter extends BasePresenter {
        void addTask(Task task);
        void editTask(TaskViewModel taskViewModel);
        void deleteTask(TaskViewModel taskViewModel);
    }
}
