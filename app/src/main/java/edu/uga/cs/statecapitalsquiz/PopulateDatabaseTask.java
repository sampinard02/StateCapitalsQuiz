package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class PopulateDatabaseTask extends AsyncTask<Void, Void> {
    private static final String TAG = "PopulateDatabaseTask";
    private Context context;
    private DatabaseHelper dbHelper;

    public PopulateDatabaseTask(Context context, DatabaseHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<String[]> data = CSVReader.readCSV(context, "state_capitals.csv");

        for (String[] row : data) {
            ContentValues values = new ContentValues();
            values.put("state_name", row[0]);
            values.put("capital", row[1]);
            values.put("city1", row[2]);
            values.put("city2", row[3]);
            db.insert("questions", null, values);
            Log.d(TAG, "Inserted row into questions table: " + row[0] + ", " + row[1]);
        }
        db.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Log.d(TAG, "Database population completed.");
    }
}
