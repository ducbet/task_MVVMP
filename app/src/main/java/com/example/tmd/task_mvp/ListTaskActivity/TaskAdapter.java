package com.example.tmd.task_mvp.ListTaskActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tmd.task_mvp.R;
import com.example.tmd.task_mvp.Task.Model.Task;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mList;
    private MainContract.View mView;

    public TaskAdapter(MainContract.View view, List<Task> list) {
        mView = view;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = mList.get(position);
        holder.bindData(task);
    }

    @Override
    public int getItemCount() {
        if (mList != null) return mList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Task mTask;
        private CheckBox mCheckBox;
        private TextView mTxtTitle;
        private ImageView mImgEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.check_box);
            mCheckBox.setOnClickListener(this);
            mTxtTitle = (TextView) itemView.findViewById(R.id.text_view_title);
            itemView.findViewById(R.id.image_view_edit).setOnClickListener(this);
            itemView.findViewById(R.id.image_view_delete).setOnClickListener(this);
        }

        public void bindData(Task task) {
            mTask = task;
            mCheckBox.setChecked(task.isFinished());
            mTxtTitle.setText(task.getTitle());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_view_edit:
                    mView.onShowEditTaskInputDialog(mTask);
                    break;
                case R.id.check_box:
                    mTask.setFinished(!mTask.isFinished());
                    mCheckBox.setChecked(mTask.isFinished());
                    mView.onChangeCheckBox(mTask);
                    break;
                case R.id.image_view_delete:
                    mView.onShowDeleteTaskConfirmDialog(mTask);
                    break;
                default:
                    break;
            }
        }
    }
}
