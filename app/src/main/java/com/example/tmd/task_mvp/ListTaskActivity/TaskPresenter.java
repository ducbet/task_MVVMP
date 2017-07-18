package com.example.tmd.task_mvp.ListTaskActivity;

import com.example.tmd.task_mvp.Task.TaskDataSource;
import com.example.tmd.task_mvp.Task.TaskRepository;
import com.example.tmd.task_mvp.ViewModel.MainViewModel;
import com.example.tmd.task_mvp.ViewModel.Task;
import com.example.tmd.task_mvp.ViewModel.TaskViewModel;
import java.util.List;

/**
 * Created by tmd on 07/07/2017.
 */
public class TaskPresenter implements MainContract.Presenter {

    private MainViewModel mMainViewModel;
    private TaskDataSource mTaskDataSource;

    public TaskPresenter(MainViewModel mainViewModelModel, TaskRepository taskRepository) {
        mMainViewModel = mainViewModelModel;
        mMainViewModel.setPresenter(this);
        mTaskDataSource = taskRepository;
    }

    @Override
    public void start() {
        // get task from database
        mTaskDataSource.getAllTask(new TaskDataSource.Callbacks<Task>() {
            @Override
            public void onSuccessfull(List<Task> data) {
                mMainViewModel.onGetAllTaskSuccess(data);
            }

            @Override
            public void onFailed(String msg) {
                mMainViewModel.onShowMsgFailed(msg);
            }
        });
    }

    @Override
    public void addTask(final Task task) {
        mTaskDataSource.addTask(task, new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mMainViewModel.onAddTaskSuccess(task);
            }

            @Override
            public void onFailed(String msg) {
                mMainViewModel.onShowMsgFailed(msg);
            }
        });
    }

    @Override
    public void editTask(final TaskViewModel taskViewModel) {
        mTaskDataSource.editTask(taskViewModel.getTask(), new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mMainViewModel.onEditTaskSuccess(taskViewModel);
            }

            @Override
            public void onFailed(String msg) {
                mMainViewModel.onShowMsgFailed(msg);
            }
        });
    }

    @Override
    public void deleteTask(final TaskViewModel taskViewModel) {
        mTaskDataSource.deleteTask(taskViewModel.getTask(), new TaskDataSource.Callback<Boolean>() {
            @Override
            public void onSuccessfull(Boolean data) {
                mMainViewModel.onDeleteTaskSuccess(taskViewModel);
            }

            @Override
            public void onFailed(String msg) {
                mMainViewModel.onShowMsgFailed(msg);
            }
        });
    }
}
