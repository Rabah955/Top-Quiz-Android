package com.skyplus.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.skyplus.topquiz.R;
import com.skyplus.topquiz.model.Question;
import com.skyplus.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView gameQuestionText;
    private Button gameAnswer1btn;
    private Button gameAanswer2btn;
    private Button gameAnswer3btn;
    private Button gameAnswer4btn;

    private QuestionBank QuestionBank;
    private Question CurrentQuestion;

    private int Score;
    private int NumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean EnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");

        QuestionBank = this.generateQuestions();
        if (savedInstanceState != null) {
            Score = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            NumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            Score = 0;
            NumberOfQuestions = 4;
        }

        EnableTouchEvents = true;

        // Wire widgets
        gameQuestionText = findViewById(R.id.activity_game_question_text);
        gameAnswer1btn = findViewById(R.id.activity_game_answer1_btn);
        gameAanswer2btn = findViewById(R.id.activity_game_answer2_btn);
        gameAnswer3btn = findViewById(R.id.activity_game_answer3_btn);
        gameAnswer4btn = findViewById(R.id.activity_game_answer4_btn);

        // Use the tag property to 'name' the buttons
        gameAnswer1btn.setTag(0);
        gameAanswer2btn.setTag(1);
        gameAnswer3btn.setTag(2);
        gameAnswer4btn.setTag(3);


        gameAnswer1btn.setOnClickListener(this);
        gameAanswer2btn.setOnClickListener(this);
        gameAnswer3btn.setOnClickListener(this);
        gameAnswer4btn.setOnClickListener(this);


        CurrentQuestion = QuestionBank.getQuestion();
        this.displayQuestion(CurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, Score);
        outState.putInt(BUNDLE_STATE_QUESTION, NumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == CurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            Score++;
        } else {
            // Wrong answer
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        EnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EnableTouchEvents = true;

                // If this is the last question, ends the game.
                // Else, display the next question.
                if (--NumberOfQuestions == 0) {
                    // End the game
                    endGame();
                } else {
                    CurrentQuestion = QuestionBank.getQuestion();
                    displayQuestion(CurrentQuestion);
                }
            }
        }, 2000); // LENGTH_SHORT is usually 2 second long
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return EnableTouchEvents && super.dispatchTouchEvent(ev);
    }


    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bien joué!")
                .setMessage("Ton score est de " + Score)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, Score);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }


    private void displayQuestion(final Question question){
        gameQuestionText.setText(question.getQuestion());
        gameAnswer1btn.setText(question.getChoiceList().get(0));
        gameAanswer2btn.setText(question.getChoiceList().get(1));
        gameAnswer3btn.setText(question.getChoiceList().get(2));
        gameAnswer4btn.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions(){
        Question question1 = new Question(" Quelle est l'entreprise à l'origine de League of Legends ?",
                Arrays.asList("Blizzard", "Riot Games", "Hi-Rez Studios", "Rito Please"),1);

       Question question2 = new Question(" Quel champion a la plus grande portée d'auto-attaque au niveau 1 ? ",
                Arrays.asList("Annie", "Caitlyn", "Jinx", "Anivia"),1);

        Question question3 = new Question(" Depuis son rework, quelle est la nouvelle danse de Twisted Fate ?  ",
                Arrays.asList("Moonwalk ", "Cotton Eyed Joe", "French Cancan ", "Gangnam Style"),3);

        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }


}
