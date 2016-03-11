package ca.fuzzlesoft.stethoexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mitch
 * @since 3/11/16.
 */
public class PrepareDatabaseTask extends AsyncTask<String, Integer, SQLiteDatabase> {
    private final Context context;
    private final DatabaseReadyHandler callback;

    public PrepareDatabaseTask(Context context, DatabaseReadyHandler callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected SQLiteDatabase doInBackground(String... params) {
        if (params.length != 1) {
            return null;
        }
        String filename = params[0];
        InputStream stream;

        try {
            stream = context.getAssets().open(filename);
        } catch (IOException e) {
            Log.e(MainApplication.TAG, "Couldn't preload ninjas: " + e.getMessage());
            return null;
        }

        NinjaDatabaseHelper helper = new NinjaDatabaseHelper(context, stream);
        return helper.getWritableDatabase();
    }

    @Override
    protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {
        callback.onDatabaseReady(sqLiteDatabase);
    }
}
