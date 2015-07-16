package com.example.ale.groceryshoppingassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;





    public class TaskDBHelper extends SQLiteOpenHelper {

        public TaskDBHelper(Context context) {
            super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqlDB) {
            String sqlQuery =
                    String.format("CREATE TABLE %s (" +
                                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "%s TEXT)", TaskContract.TABLE,
                            TaskContract.Columns.TASK);

            Log.d("TaskDBHelper", "Query to form table: " + sqlQuery);
            sqlDB.execSQL(sqlQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
            sqlDB.execSQL("DROP TABLE IF EXISTS " + TaskContract.TABLE);
            onCreate(sqlDB);
        }



        public class TaskContract {
            public static final String DB_NAME = "com.example.TodoList.db.tasks";
            public static final int DB_VERSION = 1;
            public static final String TABLE = "tasks";


            public class Columns {
                public static final String TASK = "task";
                public static final String _ID = BaseColumns._ID;
            }
        }
    }



