package ca.fuzzlesoft.stethoexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author mitch
 * @since 3/11/16.
 */
public class NinjaDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "Ninjas";

    private final InputStream inputStream;

    public NinjaDatabaseHelper(Context context, InputStream inputStream) {
        super(context, NAME, null, VERSION);
        this.inputStream = inputStream;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + NAME + "("
                + "name TEXT,"
                + "email TEXT,"
                + "picture_url"
                + ")"
        );

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        db.beginTransaction();
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] vals = line.split(",");
                String sql = "INSERT INTO " + NAME + " VALUES ("
                        + "'" + vals[0] + "',"
                        + "'" + vals[1] + "',"
                        + "'" + vals[2] + "'"
                        + ")";
                db.execSQL(sql);
            }
        } catch (IOException e) {
            Log.e(MainApplication.TAG, "Preloading ninjas failed: " + e.getMessage());
            db.endTransaction();
            return;
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAME); // YOLO
        onCreate(db);
    }
}
