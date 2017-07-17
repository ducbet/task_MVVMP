package com.example.tmd.task_mvp.Task;

import com.example.tmd.task_mvp.Task.local.TaskLocalDataSource;
import com.example.tmd.task_mvp.Task.remote.TaskRemoteDataSource;
import com.example.tmd.task_mvp.ViewModel.Task;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskRepository implements TaskDataSource {
    private TaskLocalDataSource mTaskLocalDataSource;
    private TaskRemoteDataSource mTaskRemotelDataSource;

    public TaskRepository(TaskLocalDataSource taskLocalDataSource,
            TaskRemoteDataSource taskRemotelDataSource) {
        mTaskLocalDataSource = taskLocalDataSource;
        mTaskRemotelDataSource = taskRemotelDataSource;
    }

    @Override
    public void getAllTask(Callbacks<Task> callback) {
        mTaskLocalDataSource.getAllTask(callback);
    }

    @Override
    public void addTask(Task task, Callback<Boolean> callback) {
        mTaskLocalDataSource.addTask(task, callback);
    }

    @Override
    public void editTask(Task task, Callback<Boolean> callback) {
        mTaskLocalDataSource.editTask(task, callback);
    }

    @Override
    public void deleteTask(Task task, Callback<Boolean> callback) {
        mTaskLocalDataSource.deleteTask(task, callback);
    }
}
