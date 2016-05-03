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

        String mtimeam_pm = getString(getColumnIndex(TasksTable.Cols.MTIMEAMPM));
        String remind_time = getString(getColumnIndex(TasksTable.Cols.REMINDTIME));
        int isRepeatsunday = getInt(getColumnIndex(TasksTable.Cols.REPEATSUNDAY));
        int isRepeatmonday = getInt(getColumnIndex(TasksTable.Cols.REPEATMONDAY));
        int isRepeattuesday = getInt(getColumnIndex(TasksTable.Cols.REPEATTUESDAY));
        int isRepeatwednesday = getInt(getColumnIndex(TasksTable.Cols.REPEATWEDNESDAY));
        int isRepeatthursday = getInt(getColumnIndex(TasksTable.Cols.REPEATTHURSDAY));
        int isRepeatfriday = getInt(getColumnIndex(TasksTable.Cols.REPEATFRIDAY));
        int isRepeatsaturday = getInt(getColumnIndex(TasksTable.Cols.REPEATSATURDAY));


        Tasks task = new Tasks(UUID.fromString(uuid));
        task.setTitle(name);
        task.setTime(time);

        task.setCompleted(isCompleted);

        task.setTimeAMPM(mtimeam_pm);
        task.setRemindTime(remind_time);

        task.setRepeatSunday(isRepeatsunday != 0);
        task.setRepeatMonday(isRepeatmonday != 0);
        task.setRepeatTuesday(isRepeattuesday != 0);
        task.setRepeatWednesday(isRepeatwednesday != 0);
        task.setRepeatThursday(isRepeatthursday != 0);
        task.setRepeatFriday(isRepeatfriday != 0);
        task.setRepeatSaturday(isRepeatsaturday != 0);

        return task;
    }
}
