package com.example.tmd.task_mvp.Task.Model;

/**
 * Created by tmd on 07/07/2017.
 */
public class Task {
    private int mId;
    private String mTitle;

    public Task(int id, String title) {
        mId = id;
        mTitle = title;
    }

    public Task(String title) {
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
