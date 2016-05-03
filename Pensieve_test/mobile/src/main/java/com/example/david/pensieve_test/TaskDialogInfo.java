package com.example.david.pensieve_test;

import java.util.ArrayList;

/**
 * Created by casey on 4/29/16.
 */
public class TaskDialogInfo {

    public static  ArrayList<String> mTitle;
    public static  int hour ;
    public static  int minute ;
    public static  boolean am ;

    public static  int remindTime ; // in minutes
    public static  boolean repeatSunday;
    public static  boolean repeatMonday;
    public static  boolean repeatTuesday;
    public static  boolean repeatWednesday;
    public static  boolean repeatThursday;
    public static  boolean repeatFriday;
    public static  boolean repeatSaturday;

    public static  boolean ok ;

    public String getmTitle() {
        if(this.mTitle.size()==0)return("") ;
        return mTitle.get(0);
    }

    public  void setmTitle(String mTitle) {
        this.mTitle.clear();
        this.mTitle.add(mTitle);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isAm() {
        return am;
    }

    public void setAm(boolean am) {
        this.am = am;
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

    public boolean isRepeatSaturday() {
        return repeatSaturday;
    }

    public void setRepeatSaturday(boolean repeatSaturday) {
        this.repeatSaturday = repeatSaturday;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
