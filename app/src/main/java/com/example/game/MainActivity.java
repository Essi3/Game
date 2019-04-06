package com.example.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        mainToolbar.setTitle(R.string.main_menu);
        setSupportActionBar(mainToolbar);

        Button playButton = findViewById(R.id.game_button);
        playButton.setOnClickListener(this);
        Button infoButton = findViewById(R.id.info_button);
        infoButton.setOnClickListener(this);

        Log.i("MAIN", "onCreate is running, everything looks good");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.play_icon) {
            Log.d("MAIN", "PLAY is running, everything looks good");
            Intent play = new Intent(this, GameActivity.class);
            this.startActivity(play);
            return true;
        } else if (id == R.id.info_icon) {
            Log.d("MAIN", "INFO is running, everything looks good");
            Intent info = new Intent(this, InfoActivity.class);
            this.startActivity(info);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.game_button) {
            Log.d("MAIN", "PLAY is running, everything looks good");
            Intent playIntent = new Intent(this, GameActivity.class);
            this.startActivity(playIntent);
        } else if (v.getId() == R.id.info_button) {
            Log.d("MAIN", "INFO is running, everything looks good");
            Intent infoIntent = new Intent(this, InfoActivity.class);
            this.startActivity(infoIntent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MAIN", "Game paused");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MAIN", "Game resumed");

    }
}


