package com.example.game;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final int MAX_LIVES = 10;

    private TextView wordTextView;
    private ImageView hangmanImage;
    private TextView leftLivesTextView;
    private TextView usedCharactersTextView;
    private EditText letterEditText;

    private String word;
    private char[] chars;
    private List<Character> used = new ArrayList<>();
    private List<Character> wrong = new ArrayList<>();
    private TypedArray hangmanImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Toolbar gameToolbar = findViewById(R.id.game_toolbar);
        gameToolbar.setTitle(R.string.the_hangman_game);
        setSupportActionBar(gameToolbar);

        wordTextView = findViewById(R.id.wordTextView);
        hangmanImage = findViewById(R.id.hangmanImageView);
        leftLivesTextView = findViewById(R.id.leftLivesTextView);
        usedCharactersTextView = findViewById(R.id.usedCharactersTextView);
        letterEditText = findViewById(R.id.guessLetterEditText);
        Button guessButton = findViewById(R.id.guess_button);

        word = getRandom();
        chars = word.toUpperCase().toCharArray();

        selectChar(chars[0]);
        selectChar(chars[chars.length - 1]);

        hangmanImages = getResources().obtainTypedArray(R.array.images);

        updateView();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String character = letterEditText.getText().toString().trim().toUpperCase();
                if (character.isEmpty()) return;
                char c = character.charAt(0);
                selectChar(c);
                updateView();
            }
        });
    }

    private String getRandom() {
        String[] words = getResources().getStringArray(R.array.words);
        Random random = new Random();
        random.nextInt(words.length);

        return words[random.nextInt(words.length)];

    }

    private void selectChar(char character) {

        boolean isCorrect = false;
        if (isCorrect ||
                !isAlphabetic(character)) {
        return;
        }

        if (used.contains(character)) {
            Toast.makeText(this, "You already used this letter!", Toast.LENGTH_LONG).show();
            return;
        }

        used.add(character);

        boolean charUsed = false;
        boolean solved = true;

        for (char c : chars) {
            if (c == character) {
                charUsed = true;
            }
            if (!used.contains(c)) {
                solved = false;
            }
        }
        if (!charUsed)
        wrong.add(character);

        Intent gameOver = new Intent(this, ResultActivity.class);
        gameOver.putExtra("WORD_IDENTIFIER", word);
        gameOver.putExtra("POINTS_IDENTIFIER", getLivesLeft());

        //TODO Prints out only win
        if (solved) {
            gameOver.putExtra("win", true);
            startActivity(gameOver);
        } else if (getLivesLeft() == 0) {
            gameOver.putExtra("win", false);
            startActivity(gameOver);
        }

    }

    private String getDisplayedWord() {
        Log.d("GAME", "WORD " + word);
        Log.d("GAME", "CHAR" + wrong);
        Log.d("GAME", "CHAR" + used);

        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = chars.length - 1; i <= j; i++) {
            char c = chars[i];
            if (!isAlphabetic(c) || used.contains(c)) {
                sb.append(c);
            } else {
                sb.append('_');
            }
            if (i != j) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }


    private void updateView() {

        wordTextView.setText(getDisplayedWord());
        letterEditText.setText("");
        leftLivesTextView.setText(String.valueOf(getLivesLeft()));
        usedCharactersTextView.setText(String.valueOf(used));
        setHangManImage();
    }

    private int getLivesLeft() {
        return MAX_LIVES - wrong.size();
    }


    private void setHangManImage() {
        int livesLeft = getLivesLeft();
        hangmanImage.setImageResource(hangmanImages.getResourceId(livesLeft, -1));

    }

    private boolean isAlphabetic(char c) {
        return c >= 'A' && c <= 'Z';
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.info_icon) {
            Intent info = new Intent(this, InfoActivity.class);
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

