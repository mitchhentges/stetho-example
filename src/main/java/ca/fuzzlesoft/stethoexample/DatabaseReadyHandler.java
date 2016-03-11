package ca.fuzzlesoft.stethoexample;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author mitch
 * @since 3/11/16.
 */
public interface DatabaseReadyHandler {
    void onDatabaseReady(SQLiteDatabase db);
}
