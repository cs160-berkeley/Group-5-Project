package com.example.david.pensieve_test.database;

/**
 * Created by david on 4/17/16.
 */

// casey: modify to add repeating days and AMPM parameters

public class TasksDbSchema {
    public static final class TasksTable {
        public static final String NAME = "tasks";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String TIME = "time";
            public static final String NOTE = "note";
            public static final String PATIENT_NOTE = "patientNote";
            public static final String COMPLETED = "completed";

            public static final String MTIMEAMPM  = "mtimeampm"  ;
            public static final String REMINDTIME  = "remindtime"  ;
            public static final String REPEATSUNDAY  = "repeatsunday"  ;
            public static final String REPEATMONDAY  = "repeatmonday"  ;
            public static final String REPEATTUESDAY  = "repeattuesday"  ;
            public static final String REPEATWEDNESDAY  = "repeatwednesday"  ;
            public static final String REPEATTHURSDAY  = "repeatthursday"  ;
            public static final String REPEATFRIDAY  = "repeatfriday"  ;
            public static final String REPEATSATURDAY = "repeatsaturday" ;



        }
    }
}
