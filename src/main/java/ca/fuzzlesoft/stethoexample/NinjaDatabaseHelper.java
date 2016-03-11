package ca.fuzzlesoft.stethoexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * @author mitch
 * @since 3/11/16.
 */
public class NinjaDatabaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String NAME = "Ninjas";

    private final List<Ninja> initialNinjas;

    public NinjaDatabaseHelper(Context context, List<Ninja> initialNinjas) {
        super(context, NAME, null, VERSION);
        this.initialNinjas = initialNinjas;
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

        db.beginTransaction();
        for (Ninja ninja : initialNinjas) {
            String sql = "INSERT INTO " + NAME + " VALUES ("
                    + "'" + ninja.getName() + "',"
                    + "'" + ninja.getEmail() + "',"
                    + "'" + ninja.getPictureUrl() + "'"
                    + ")";
            db.execSQL(sql);
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
