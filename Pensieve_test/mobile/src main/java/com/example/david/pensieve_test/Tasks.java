package com.example.david.pensieve_test;

import java.util.UUID;

/**
 * Created by david on 4/17/16.
 */
public class Tasks {
    private UUID mId;
    private String mTitle;
    private String mTime;
    private boolean mCompleted;

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

    public void setTime(String time) {
        mTime = time;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}
