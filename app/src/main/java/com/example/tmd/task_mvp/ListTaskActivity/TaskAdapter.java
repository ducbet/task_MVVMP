package com.example.tmd.task_mvp.ListTaskActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.tmd.task_mvp.R;
import com.example.tmd.task_mvp.Task.Model.Task;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> mList;

    public TaskAdapter(List<Task> list) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtId;
        private TextView mTxtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtId = (TextView) itemView.findViewById(R.id.text_view_id);
            mTxtTitle = (TextView) itemView.findViewById(R.id.text_view_title);
        }

        public void bindData(Task task) {
            mTxtId.setText(String.valueOf(task.getId()));
            mTxtTitle.setText(task.getTitle());
        }
    }
}
