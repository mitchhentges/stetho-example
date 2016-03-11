package ca.fuzzlesoft.stethoexample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements DatabaseReadyHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tretton37 Ninjas");
        setSupportActionBar(toolbar);

        RecyclerView ninjaView = (RecyclerView) findViewById(R.id.ninjaList);
        ninjaView.setHasFixedSize(true);
        ninjaView.setLayoutManager(new LinearLayoutManager(this));
        ninjaView.setAdapter(new NinjaAdapter());
        ninjaView.addItemDecoration(new DividerItemDecoration(this));

        new PrepareDatabaseTask(this, this).execute("ninjas.csv");
    }


    @Override
    public void onDatabaseReady(SQLiteDatabase db) {
        Log.i(MainApplication.TAG, "ayy");
    }
}
