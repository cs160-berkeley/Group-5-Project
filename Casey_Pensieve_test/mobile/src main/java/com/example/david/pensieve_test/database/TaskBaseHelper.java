package com.example.david.pensieve_test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.pensieve_test.database.TasksDbSchema.TasksTable;

/**
 * Created by david on 4/17/16.
 */
public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TasksTable.NAME + "(" +
                " _id integer primary key autoincrement," +
                TasksTable.Cols.UUID + ", " +
                TasksTable.Cols.TITLE + ", " +
                TasksTable.Cols.TIME + ", " +
                TasksTable.Cols.COMPLETED + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
