package ca.fuzzlesoft.stethoexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

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
    }
}
