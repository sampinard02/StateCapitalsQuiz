package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

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


    public List<Question> getRandomQuestions(int count) {

        List<Question> questions = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM questions ORDER BY RANDOM() LIMIT " + count, null);

        while (cursor.moveToNext()) {

            String stateName = cursor.getString(cursor.getColumnIndexOrThrow("state_name"));

            String capital = cursor.getString(cursor.getColumnIndexOrThrow("capital"));

            String city1 = cursor.getString(cursor.getColumnIndexOrThrow("city1"));

            String city2 = cursor.getString(cursor.getColumnIndexOrThrow("city2"));

            questions.add(new Question(stateName, capital, city1, city2));

        }

        cursor.close();

        db.close();

        return questions;
    }
}

