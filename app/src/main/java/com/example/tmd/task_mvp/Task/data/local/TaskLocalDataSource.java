package com.example.tmd.task_mvp.Task.data.local;

import android.content.Context;
import com.example.tmd.task_mvp.Task.Model.Task;
import com.example.tmd.task_mvp.Task.data.TaskDataSource;

/**
 * Created by tmd on 07/07/2017.
 */

public class TaskLocalDataSource implements TaskDataSource {

    private Context mContext;
    private _CRUDHelper mCRUDHelper;

    public TaskLocalDataSource(Context context) {
        mContext = context;
        mCRUDHelper = new _CRUDHelper(context);
    }

    @Override
    public void getAllTask(Callbacks<Task> callback) {
        callback.onSuccessfull(mCRUDHelper.getAllTask());
    }

    @Override
    public void addTask(Task task, Callback<Boolean> callback) {
        if (mCRUDHelper.insertTask(task)) {
            callback.onSuccessfull(Boolean.TRUE);
        } else {
            callback.onFailed("FAILED");
        }
    }

    @Override
    public void editTask(Task task, Callback<Boolean> callback) {

    }

    @Override
    public void deleteTask(Task task, Callback<Boolean> callback) {

    }
}
