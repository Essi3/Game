package com.example.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar infoToolbar = findViewById(R.id.info_toolbar);
        infoToolbar.setTitle(R.string.info);
        setSupportActionBar(infoToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.play_icon) {
            Intent info = new Intent(this, GameActivity.class);
            this.startActivity(info);
            return true;
        } else if (id == R.id.home_icon) {
            Intent info = new Intent(this, MainActivity.class);
            this.startActivity(info);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
