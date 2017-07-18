package com.example.tmd.task_mvp.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tmd.task_mvp.ListTaskActivity.MainContract;
import com.example.tmd.task_mvp.Task.TaskDataSource;

/**
 * Created by tmd on 17/07/2017.
 */

public class TaskViewModel {

    private Context mContext;
    private Task mTask;
    private MainContract.MainViewModel mMainViewModel;

    public TaskViewModel(Context context, Task task,
            MainContract.MainViewModel mainViewModel) {
        mContext = context;
        mTask = task;
        mMainViewModel = mainViewModel;
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

    public void onShowEditTaskInputDialog() {
        mMainViewModel.onShowEditTaskInputDialog(this);
    }

    public void onChangeCheckBox() {
        mMainViewModel.onChangeCheckBox(this);
    }

    public void onShowDeleteTaskConfirmDialog() {
        mMainViewModel.onShowDeleteTaskConfirmDialog(this);
    }
}
