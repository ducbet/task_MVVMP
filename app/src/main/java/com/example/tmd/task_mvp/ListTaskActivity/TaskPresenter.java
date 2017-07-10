package com.example.tmd.task_mvp.ListTaskActivity;

import com.example.tmd.task_mvp.Task.Model.Task;
import com.example.tmd.task_mvp.Task.data.TaskDataSource;
import com.example.tmd.task_mvp.Task.data.TaskRepository;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */
public class TaskPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private TaskDataSource mTaskDataSource;

    public TaskPresenter(MainContract.View view, TaskRepository taskRepository) {
        mView = view;
        mView.setPresenter(this);
        mTaskDataSource = taskRepository;
    }

    @Override
    public void start() {
        // get task from database
        mTaskDataSource.getAllTask(new TaskDataSource.Callbacks<Task>() {
            @Override
            public void onSuccessfull(List<Task> data) {
                mView.onGetAllTaskSuccess(data);
            }

            @Override
            public void onFailed(String msg) {
                mView.onGetAllTaskFailed(msg);
            }
        });
    }

    @Override
    public void addTask(final Task task) {
        mTaskDataSource.addTask(task, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mView.onAddTaskSuccess(task);
            }

            @Override
            public void onFailed(String msg) {
                mView.onAddTaskFailed(msg);
            }
        });
    }

    @Override
    public void editTask() {
    }

    @Override
    public void deleteTask() {
    }
}
