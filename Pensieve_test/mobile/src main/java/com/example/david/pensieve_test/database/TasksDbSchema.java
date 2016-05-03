package com.example.david.pensieve_test.database;

/**
 * Created by david on 4/17/16.
 */
public class TasksDbSchema {
    public static final class TasksTable {
        public static final String NAME = "tasks";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String TIME = "time";
            public static final String COMPLETED = "completed";
        }
    }
}
