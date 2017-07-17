package com.example.tmd.task_mvp.Task.remote;

import android.content.Context;
import com.example.tmd.task_mvp.Task.TaskDataSource;
import com.example.tmd.task_mvp.ViewModel.Task;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskRemoteDataSource implements TaskDataSource {

    private Context mContext;

    public TaskRemoteDataSource(Context context) {
        mContext = context;
    }

    @Override
    public void getAllTask(Callbacks<Task> callback) {

    }

    @Override
    public void addTask(Task task, Callback<Boolean> callback) {

    }

    @Override
    public void editTask(Task task, Callback<Boolean> callback) {

    }

    @Override
    public void deleteTask(Task task, Callback<Boolean> callback) {

    }
}
