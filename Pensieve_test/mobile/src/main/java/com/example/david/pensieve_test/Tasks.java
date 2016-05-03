package com.example.david.pensieve_test;

import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */


// Casey: add additional parameters needed for Tasks
// repeat of days in week
// time to reminder
//
public class Tasks {
    private UUID mId;
    private String mTitle;
    private String mTime;
    private boolean mCompleted;
    private String note;

    private String mTimeAMPM ;
    private String remindTime ;
    private boolean repeatSunday;
    private boolean repeatMonday;
    private boolean repeatTuesday;
    private boolean repeatWednesday;
    private boolean repeatThursday;
    private boolean repeatFriday;
    private boolean repeatSaturday;


    public Tasks() {
        this(UUID.randomUUID());
    }

    public Tasks(UUID id){
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTime() {
        return mTime;
    }

    public String getTimeAMPM() {
        return mTimeAMPM;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public void setTimeAMPM(String AMPM) {
        mTimeAMPM = AMPM;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public boolean isRepeatSaturday() {
        return repeatSaturday;
    }

    public void setRepeatSaturday(boolean repeatSaturday) {
        this.repeatSaturday = repeatSaturday;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public boolean isRepeatSunday() {
        return repeatSunday;
    }

    public void setRepeatSunday(boolean repeatSunday) {
        this.repeatSunday = repeatSunday;
    }

    public boolean isRepeatMonday() {
        return repeatMonday;
    }

    public void setRepeatMonday(boolean repeatMonday) {
        this.repeatMonday = repeatMonday;
    }

    public boolean isRepeatTuesday() {
        return repeatTuesday;
    }

    public void setRepeatTuesday(boolean repeatTuesday) {
        this.repeatTuesday = repeatTuesday;
    }

    public boolean isRepeatWednesday() {
        return repeatWednesday;
    }

    public void setRepeatWednesday(boolean repeatWednesday) {
        this.repeatWednesday = repeatWednesday;
    }

    public boolean isRepeatThursday() {
        return repeatThursday;
    }

    public void setRepeatThursday(boolean repeatThursday) {
        this.repeatThursday = repeatThursday;
    }

    public boolean isRepeatFriday() {
        return repeatFriday;
    }

    public void setRepeatFriday(boolean repeatFriday) {
        this.repeatFriday = repeatFriday;
    }
}




