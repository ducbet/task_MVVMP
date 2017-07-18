package com.example.tmd.task_mvp.ListTaskActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.tmd.task_mvp.R;
import com.example.tmd.task_mvp.Task.TaskRepository;
import com.example.tmd.task_mvp.Task.local.TaskLocalDataSource;
import com.example.tmd.task_mvp.Task.remote.TaskRemoteDataSource;
import com.example.tmd.task_mvp.ViewModel.MainViewModel;
import com.example.tmd.task_mvp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mMainViewModel;
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new MainViewModel(this);
        mainBinding.setViewModel(mMainViewModel);

        mPresenter = new TaskPresenter(mMainViewModel,
                new TaskRepository(new TaskLocalDataSource(this), new TaskRemoteDataSource(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
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
                mMainViewModel.onShowAddTaskInputDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}