package ca.fuzzlesoft.stethoexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(params[0])));
        } catch (IOException e) {
            Log.e(MainApplication.TAG, "Couldn't preload ninjas: " + e.getMessage());
            return null;
        }

        List<Ninja> initialNinjas = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] vals = line.split(",");
                if (vals.length < 3) {
                    continue; // Is a blank or incomplete line
                }

                Ninja ninja = new Ninja(vals[0], Ninja.deobfuscateEmail(vals[1]), vals[2]);
                initialNinjas.add(ninja);
            }
        } catch (IOException e) {
            Log.e(MainApplication.TAG, "Failed to read line from ninja csv: " + e.getMessage());
        }

        NinjaDatabaseHelper helper = new NinjaDatabaseHelper(context, initialNinjas);
        return helper.getWritableDatabase();
    }

    @Override
    protected void onPostExecute(SQLiteDatabase sqLiteDatabase) {
        callback.onDatabaseReady(sqLiteDatabase);
    }
}
