package com.example.tmd.task_mvp.ListTaskActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tmd.task_mvp.R;
import com.example.tmd.task_mvp.Task.Model.Task;
import com.example.tmd.task_mvp.Task.data.TaskRepository;
import com.example.tmd.task_mvp.Task.data.local.TaskLocalDataSource;
import com.example.tmd.task_mvp.Task.data.remote.TaskRemoteDataSource;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private List<Task> mListTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPresenter = new TaskPresenter(this,
                new TaskRepository(new TaskLocalDataSource(this), new TaskRemoteDataSource(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initView() {
        mListTasks = new ArrayList<>();
        mTaskAdapter = new TaskAdapter(this, mListTasks);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mTaskAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                onShowAddTaskInputDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(TaskPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShowAddTaskInputDialog() {
        final EditText editText = new EditText(this);
        editText.setHint("Title");
        new AlertDialog.Builder(this).setTitle("New task")
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
    public void onShowEditTaskInputDialog(final Task task) {
        final EditText editText = new EditText(this);
        editText.setHint("Title");
        new AlertDialog.Builder(this).setTitle("New task")
                .setView(editText)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task.setTitle(editText.getText().toString());
                        mPresenter.editTask(task);
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
    public void onChangeCheckBox(Task task) {
        mPresenter.editTask(task);
    }

    @Override
    public void onShowDeleteTaskConfirmDialog(final Task task) {
        new AlertDialog.Builder(this).setTitle("New task")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteTask(task);
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
        mListTasks.add(task);
        mTaskAdapter.notifyItemInserted(mListTasks.size());
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetAllTaskSuccess(List<Task> listTasks) {
        mListTasks.clear();
        mListTasks.addAll(listTasks);
        mTaskAdapter.notifyItemInserted(mListTasks.size());
    }

    @Override
    public void onEditTaskSuccess(Task task) {
        int index = mListTasks.indexOf(task);
        mListTasks.set(index, task);
        mTaskAdapter.notifyItemChanged(index);
    }

    @Override
    public void onDeleteTaskSuccess(Task task) {
        int index = mListTasks.indexOf(task);
        mListTasks.remove(index);
        mTaskAdapter.notifyItemRemoved(index);
    }
}
