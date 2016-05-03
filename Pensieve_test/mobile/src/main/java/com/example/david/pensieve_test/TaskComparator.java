package com.example.david.pensieve_test;

import java.util.Comparator;

/**
 * Created by Graham on 5/2/2016.
 */
public class TaskComparator implements Comparator<Tasks> {
    public int compare (Tasks left, Tasks right) {
        try {
            String leftTimeString = left.getTime();
            String leftHalf = left.getTimeAMPM();
            int leftTime = parseTime(leftTimeString, leftHalf);

            String rightTimeString = right.getTime();
            String rightHalf = right.getTimeAMPM();
            int rightTime = parseTime(rightTimeString, rightHalf);

            if (leftTime > rightTime) {
                return 1;
            } else if (leftTime == rightTime) {
                return 0;
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    // http://stackoverflow.com/questions/8909075/convert-time-field-hm-into-integer-field-minutes-in-java
    int parseTime (String time, String whichHalf) {
        String[] hourMin = time.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int min = Integer.parseInt(hourMin[1]);

        // Scale time to half day
        if (whichHalf.equals("PM")) {
            hour += 12;
        } else if (whichHalf.equals("AM") && (hour == 12)) {
            hour = 0;
        }
        return (hour * 60) + min;
    }
}
