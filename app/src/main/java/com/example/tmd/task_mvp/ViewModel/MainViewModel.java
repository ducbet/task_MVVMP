package com.example.tmd.task_mvp.ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tmd.task_mvp.ListTaskActivity.MainContract;
import com.example.tmd.task_mvp.ListTaskActivity.TaskAdapter;
import com.example.tmd.task_mvp.ListTaskActivity.TaskPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 17/07/2017.
 */

public class MainViewModel extends BaseObservable implements MainContract.MainViewModel {
    private Context mContext;
    private TaskAdapter mAdapter;
    private TaskPresenter mPresenter;

    public MainViewModel(Context context) {
        mContext = context;
        mAdapter = new TaskAdapter();
    }

    public void setContext(Context context) {
        mContext = context;
        notifyChange();
    }

    public void setAdapter(TaskAdapter adapter) {
        mAdapter = adapter;
        notifyChange();
    }

    @Override
    public void setPresenter(TaskPresenter presenter) {
        mPresenter = presenter;
        notifyChange();
    }

    @Override
    public void onShowAddTaskInputDialog() {
        final EditText editText = new EditText(mContext);
        editText.setHint("Title");
        new AlertDialog.Builder(mContext).setTitle("New task")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Task task = new Task(editText.getText().toString());
                        mPresenter.addTask(task);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onShowEditTaskInputDialog(final TaskViewModel taskViewModel) {
        final EditText editText = new EditText(mContext);
        editText.setHint("Title");
        new AlertDialog.Builder(mContext).setTitle("New task")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taskViewModel.getTask().setTitle(editText.getText().toString());
                        mPresenter.editTask(taskViewModel);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onChangeCheckBox(TaskViewModel taskViewModel) {
        taskViewModel.getTask().setFinished(!taskViewModel.getTask().isFinished());
        mPresenter.editTask(taskViewModel);
    }

    @Override
    public void onShowDeleteTaskConfirmDialog(final TaskViewModel taskViewModel) {
        new AlertDialog.Builder(mContext).setTitle("New task")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteTask(taskViewModel);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onAddTaskSuccess(Task task) {
        mAdapter.updateData(new TaskViewModel(mContext, task, this));
    }

    @Override
    public void onShowMsgFailed(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetAllTaskSuccess(List<Task> listTasks) {
        List<TaskViewModel> listTaskViewModel = new ArrayList<>();
        for (int i = 0; i < listTasks.size(); i++) {
            listTaskViewModel.add(new TaskViewModel(mContext, listTasks.get(i), this));
        }
        mAdapter.updateData(listTaskViewModel);
    }

    @Override
    public void onEditTaskSuccess(TaskViewModel taskViewModel) {
        mAdapter.updateData(taskViewModel);
    }

    @Override
    public void onDeleteTaskSuccess(TaskViewModel taskViewModel) {
        mAdapter.deleteTask(taskViewModel);
    }

    public TaskAdapter getAdapter() {
        return mAdapter;
    }
}
