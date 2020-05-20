package com.skyplus.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.skyplus.topquiz.R;
import com.skyplus.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

   private TextView GreetingText;
   private EditText NameInput;
   private Button PlayButton;
   private User User;
   public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
   private SharedPreferences Preferences;

   public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
   public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score the Intent
            assert data != null;
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            Preferences.edit().putInt("PREF_KEY_SCORE", score).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lier les variables au id
        User = new User();
        Preferences = getPreferences(MODE_PRIVATE);
        GreetingText = findViewById(R.id.activity_main_greeting_txt);
        NameInput = findViewById(R.id.activity_main_name_input);
        PlayButton = findViewById(R.id.activity_main_play_btn);

        PlayButton.setEnabled(false);
        NameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PlayButton.setEnabled(s.toString().length() !=0);

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = NameInput.getText().toString();
                User.setFirstname(firstname);

                Preferences.edit().putString("PREF_KEY_FIRSTNAME", User.getFirstname()).apply();

                //user clicked the button
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class );
                startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });


    }
}
