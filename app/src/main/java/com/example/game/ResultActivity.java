package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean winOrLoose;
    private TextView result;
    private TextView resultWord;
    private TextView resultLivesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar resultToolbar = findViewById(R.id.main_toolbar);
        resultToolbar.setTitle("Result");
        setSupportActionBar(resultToolbar);
        updateView();

        result = findViewById(R.id.result_textView);

        if (winOrLoose)
            result.setText("Congrats! You guessed the secret word!");
        else
            result.setText("So close, try again!");
    }



    public void updateView() {

        winOrLoose = getIntent().getBooleanExtra("win", true);
        String word = getIntent().getStringExtra("WORD_IDENTIFIER");
        int livesLeft = getIntent().getIntExtra("POINTS_IDENTIFIER", 0);

        resultWord = findViewById(R.id.result2_textView);
        resultWord.setText("THE WORD WAS " + word);

        resultLivesLeft = findViewById(R.id.result3_textView);
        resultLivesLeft.setText("LIVES LEFT " + livesLeft);
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
            Intent play = new Intent(this, GameActivity.class);
            this.startActivity(play);
            return true;
        } else if (id == R.id.info_icon) {
            Intent info = new Intent(this, InfoActivity.class);
            this.startActivity(info);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu_button) {
            Intent playIntent = new Intent(this, MainActivity.class);
            this.startActivity(playIntent);
        }
    }
}