package ca.fuzzlesoft.stethoexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements DatabaseReadyHandler {

    private NinjaAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tretton37 Ninjas");
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        adapter = new NinjaAdapter(new ArrayList<Ninja>(), new OnNinjaTapped(this, client, progressBar));
        RecyclerView ninjaView = (RecyclerView) findViewById(R.id.ninjaList);
        ninjaView.setHasFixedSize(true);
        ninjaView.setLayoutManager(new LinearLayoutManager(this));
        ninjaView.addItemDecoration(new DividerItemDecoration(this));
        ninjaView.setAdapter(adapter);

        new PrepareDatabaseTask(this, this).execute("ninjas.csv");
    }

    @Override
    public void onDatabaseReady(SQLiteDatabase db) {
        List<Ninja> newNinjas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NinjaDatabaseHelper.NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            String pictureUrl = cursor.getString(2);
            newNinjas.add(new Ninja(name, email, pictureUrl));
            cursor.moveToNext();
        }

        cursor.close();
        adapter.addNinjas(newNinjas);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
