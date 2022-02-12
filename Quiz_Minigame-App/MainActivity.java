package com.example.quiz_minigame_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.roumani.i2c.CountryDB;

public class MainActivity extends AppCompatActivity {

    private GameModel game;
    private String question;
    private Map<String, String> map = new TreeMap<String,String>();
    private String answer;

    CountryDB db = new CountryDB();
    List<String> capitals = db.getCapitals();
    int capitalsSize = capitals.size();
    private String s = "";
    private int score = 0;
    private int qNum = 1;

    private  String inputtedAnswer;
    private String[] model;
    private String correctAnswer;
    public String randomAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);
        game = new GameModel();
        model = game.qa();
        question = model[0];
        correctAnswer = model[1];
        randomAnswer = model[2];
        ask();
    }

    private void ask(){
        if (this.qNum !=5 ) {
            ((EditText) findViewById(R.id.answer)).setText("");

            ((TextView) findViewById(R.id.question)).setText(question);
            this.correctAnswer = this.model[1];
            ((TextView) findViewById(R.id.qNum)).setText("Q# " + this.qNum);
        }
        this.qNum += 1;
        model = game.qa();
        question = model[0];


    }




    public void onDone(View v)
    {
        this.inputtedAnswer = ((EditText)findViewById(R.id.answer)).getText().toString();
        s = s + (qNum - 1) + ") " + ((TextView)findViewById(R.id.question)).getText().toString()+"\n"+
                "Your Answer: "+ inputtedAnswer.toUpperCase()+"\n"+"Correct Answer: " + this.correctAnswer+ "\n" + "\n";
        s = s + "\n\n";
        try {
            if (this.inputtedAnswer.equals("?")) {
                String statement = "The correct answer is one of these two possible options: " + correctAnswer + " or " + randomAnswer + "!";

                Toast label = Toast.makeText(getApplicationContext(), statement, Toast.LENGTH_LONG);
                label.show();
            }


            if (this.qNum == 5) {
                //((TextView) findViewById(R.id.qNum)).setText("Q#" + qNum);
                ((TextView) findViewById(R.id.score)).setText("SCORE = " + score);
                ((TextView) findViewById(R.id.question)).setText("GAME OVER! App will Close if \"DONE\" button is pressed!");

            } else if (this.qNum == 6) {
                finish();
            } else if (this.inputtedAnswer.equalsIgnoreCase(this.correctAnswer)) {
                this.score = this.score + 1;
                ((TextView) findViewById(R.id.score)).setText("SCORE = " + this.score);


                //((TextView) findViewById(R.id.qNum)).setText("Q# " + qNum);

            } else {
                ((TextView) findViewById(R.id.qNum)).setText("Q# " + qNum);
                ((TextView) findViewById(R.id.score)).setText("SCORE = " + this.score);
            }
            ((TextView) findViewById(R.id.log)).setText(s);
            ask();
        }
        catch (Exception e) {
            String statement = "Error! Please try again!";
            Toast label = Toast.makeText(this, statement, Toast.LENGTH_SHORT);
            label.show();
        }
    }

}