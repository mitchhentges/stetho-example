package ca.fuzzlesoft.stethoexample;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @author mitch
 * @since 3/10/16.
 */
public class MainApplication extends Application {
    public static final String TAG = "StethoExample";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
