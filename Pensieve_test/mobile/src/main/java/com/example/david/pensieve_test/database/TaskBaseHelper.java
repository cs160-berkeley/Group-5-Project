package com.example.david.pensieve_test.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.david.pensieve_test.database.TasksDbSchema.TasksTable;

/**
 * Created by david on 4/17/16.
 */
public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "taskBaseCN.db";

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
                        TasksTable.Cols.NOTE + ", " +
                        TasksTable.Cols.COMPLETED + ", " +

                        TasksTable.Cols.MTIMEAMPM + ", " +
                        TasksTable.Cols.REMINDTIME + ", " +
                        TasksTable.Cols.REPEATSUNDAY + ", " +
                        TasksTable.Cols.REPEATMONDAY + ", " +
                        TasksTable.Cols.REPEATTUESDAY + ", " +
                        TasksTable.Cols.REPEATWEDNESDAY + ", " +
                        TasksTable.Cols.REPEATTHURSDAY + ", " +
                        TasksTable.Cols.REPEATFRIDAY + ", " +
                        TasksTable.Cols.REPEATSATURDAY +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("ON_UPGRADE", "Upgrading database to new version");

        // If you need to add a new column
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE " + TasksTable.NAME + " ADD COLUMN " + TasksTable.Cols.NOTE + " STRING;");
        }
    }
}
