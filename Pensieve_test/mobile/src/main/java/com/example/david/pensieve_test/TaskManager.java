package com.example.david.pensieve_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.pensieve_test.database.TaskBaseHelper;
import com.example.david.pensieve_test.database.TasksCursorWrapper;
import com.example.david.pensieve_test.database.TasksDbSchema.TasksTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class TaskManager {
    private static TaskManager sTaskManager;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TaskManager get(Context context) {
        if(sTaskManager == null){
            sTaskManager = new TaskManager(context);
        }
        return sTaskManager;
    }

    private TaskManager(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
    }

    public void addTask(Tasks t){
        ContentValues values = getContentValues(t);

        mDatabase.insert(TasksTable.NAME, null, values);
    }


    public List<Tasks> getTasksList(){
        List<Tasks> tasks = new ArrayList<>();

        TasksCursorWrapper cursor = queryTasks(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tasks;
    }

    public Tasks getTask(UUID id) {
        TasksCursorWrapper cursor = queryTasks(
                TasksTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void updateTask(Tasks task){
        String uuidString = task.getId().toString();
        ContentValues values = getContentValues(task);

        mDatabase.update(TasksTable.NAME, values, TasksTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    private static ContentValues getContentValues(Tasks task){
        ContentValues values = new ContentValues();
        values.put(TasksTable.Cols.UUID, task.getId().toString());
        values.put(TasksTable.Cols.TITLE, task.getTitle());
        values.put(TasksTable.Cols.TIME, task.getTime());
        values.put(TasksTable.Cols.COMPLETED, task.isCompleted() ? 1 : 0);

        return values;
    }

    private TasksCursorWrapper queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TasksTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TasksCursorWrapper(cursor);
    }

}
