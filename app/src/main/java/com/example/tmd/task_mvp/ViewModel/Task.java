package com.example.tmd.task_mvp.ViewModel;

import android.databinding.BaseObservable;

/**
 * Created by tmd on 07/07/2017.
 */
public class Task extends BaseObservable {

    private int mId;
    private boolean mIsFinished;
    private String mTitle;

    public Task() {
    }

    public Task(int id, boolean isFinished, String title) {
        mId = id;
        mIsFinished = isFinished;
        mTitle = title;
    }

    public Task(String title) {
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        notifyChange();
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        notifyChange();
        mTitle = title;
    }

    public boolean isFinished() {
        return mIsFinished;
    }

    public void setFinished(boolean finished) {
        notifyChange();
        mIsFinished = finished;
    }
}
