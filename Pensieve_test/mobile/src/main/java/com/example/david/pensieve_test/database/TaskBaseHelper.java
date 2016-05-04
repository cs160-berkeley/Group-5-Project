package com.example.david.pensieve_test.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.david.pensieve_test.database.TasksDbSchema.TasksTable;

/**
 * Created by david on 4/17/16.
 */
public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 5;
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
                        TasksTable.Cols.PATIENT_NOTE + ", " +
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
            if (!columnExists(db, TasksTable.NAME, TasksTable.Cols.NOTE)) {
                Log.d("ON_UPGRADE", "Adding note column to database");
                db.execSQL("ALTER TABLE " + TasksTable.NAME + " ADD COLUMN " + TasksTable.Cols.NOTE + " STRING;");
            }

            if (!columnExists(db, TasksTable.NAME, TasksTable.Cols.PATIENT_NOTE)) {
                Log.d("ON_UPGRADE", "Adding patient_note column to database");
                db.execSQL("ALTER TABLE " + TasksTable.NAME + " ADD COLUMN " + TasksTable.Cols.PATIENT_NOTE + " STRING;");
            }
        }
    }


    // This method will return if the column exists in the table or not
    // http://stackoverflow.com/questions/4719594/checking-if-a-column-exists-in-an-application-database-in-android
    private boolean columnExists(SQLiteDatabase inDatabase, String inTable, String columnToCheck) {
        Cursor mCursor = null;
        try {
            // Query 1 row
            mCursor = inDatabase.rawQuery("SELECT * FROM " + inTable + " LIMIT 0", null);
            Log.d("columnExists", "All column names: " + getColumnNames(mCursor.getColumnNames()));

            // getColumnIndex() gives us the index (0 to ...) of the column - otherwise we get a -1
            if (mCursor.getColumnIndex(columnToCheck) != -1)
                return true;
            else
                return false;

        } catch (Exception Exp) {
            // Something went wrong. Missing the database? The table?
            Log.d("columnExists", Exp.getMessage());
            return false;
        } finally {
            if (mCursor != null) mCursor.close();
        }
    }

    private String getColumnNames(String[] names) {
        String strNames = "[";
        for (String name : names) {
            strNames += name + ", ";
        }
        strNames += "]";
        return strNames;
    }
}
