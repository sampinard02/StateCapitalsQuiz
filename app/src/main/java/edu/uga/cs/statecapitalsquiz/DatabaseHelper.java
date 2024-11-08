package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statements to create tables
    private static final String CREATE_QUESTIONS_TABLE = "CREATE TABLE questions (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "state_name TEXT, " +
            "capital TEXT, " +
            "city1 TEXT, " +
            "city2 TEXT);";

    private static final String CREATE_QUIZZES_TABLE = "CREATE TABLE quizzes (" +
            "quiz_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "quiz_date TEXT, " +
            "score INTEGER DEFAULT 0, " +
            "answered_count INTEGER DEFAULT 0);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_QUIZZES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS quizzes");
        onCreate(db);
    }
}
