package com.example.tmd.task_mvp.Task.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tmd.task_mvp.Task.Model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmd on 09/07/2017.
 */

public class _CRUDHelper extends DatabaseHelper {
    public _CRUDHelper(Context context) {
        super(context);
    }

    public List<Task> getAllTask() {
        List<Task> mList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor =
                database.query(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, new String[] {
                        TaskContractDatabase.TaskDatabaseEntry._ID,
                        TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE
                }, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(
                        cursor.getColumnIndex(TaskContractDatabase.TaskDatabaseEntry._ID));
                String title = cursor.getString(
                        cursor.getColumnIndex(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE));
                mList.add(new Task(id, title));
            }
        }
        cursor.close();
        database.close();
        return mList;
    }

    public boolean insertTask(Task task) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContractDatabase.TaskDatabaseEntry.COLUMN_TITLE, task.getTitle());
        long result =
                database.insert(TaskContractDatabase.TaskDatabaseEntry.TABLE_NAME, null, values);
        if (result >= 0) {
            task.setId((int) result);
        }
        database.close();
        return result >= 0;
    }
}
