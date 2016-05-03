package com.example.david.pensieve_test.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.david.pensieve_test.Tasks;
import com.example.david.pensieve_test.database.TasksDbSchema.TasksTable;

import java.util.UUID;


/**
 * Created by david on 4/17/16.
 */
public class TasksCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TasksCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Tasks getTask() {
        String uuid = getString(getColumnIndex(TasksTable.Cols.UUID));
        String name = getString(getColumnIndex(TasksTable.Cols.TITLE));
        String time = getString(getColumnIndex(TasksTable.Cols.TIME));
        int isCompleted = getInt(getColumnIndex(TasksTable.Cols.COMPLETED));


        Tasks task = new Tasks(UUID.fromString(uuid));
        task.setTitle(name);
        task.setTime(time);
        task.setCompleted(isCompleted != 0);

        return task;
    }
}
