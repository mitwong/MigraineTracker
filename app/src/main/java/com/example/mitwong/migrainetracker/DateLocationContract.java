package com.example.mitwong.migrainetracker;

import android.provider.BaseColumns;

/**
 * Created by mitwong on 6/6/2015.
 */
public class DateLocationContract {
    // To prevent someone from accidentally instantiating the contract class
    public DateLocationContract() {}

    // Inner class defines the table contents
    public static abstract class DateLocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "DateLocationTable";
        public static final String COLUMN_NAME_MIGRAINE_ID = "migraineid";
    }
}
