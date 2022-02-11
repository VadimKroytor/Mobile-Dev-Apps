package com.example.vadim.caps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.roumani.i2c.CountryDB;

public class CapsActivity extends AppCompatActivity {

    private Game game;
    private String question;
    private Map<String, String> map = new TreeMap<String,String>();
    private String answer;

    CountryDB db = new CountryDB();
    List<String> capitals = db.getCapitals();
    int capitalsSize = capitals.size();
    private String s = "";
    private int score;
    private int qNum = 1;
    private  String inputtedAnswer;
    private String[] model;
    private String correctAnswer;
    public String randomAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);
        game = new Game();
        model = game.qa();
        String questionAndAnswer = model[0] + "split" + model[1] + "split" + model[2];
        String[] split = questionAndAnswer.split("split");
        correctAnswer = split[1];
        randomAnswer = split[2];
        ask();
    }

    private void ask(){
        if (this.qNum !=10 ) {
            ((EditText) findViewById(R.id.answer)).setText("");

            ((TextView) findViewById(R.id.question)).setText(this.model[0]);
            this.correctAnswer = this.model[1];
            ((TextView) findViewById(R.id.qNum)).setText("Q# " + this.qNum);
        }
        this.qNum += 1;
        model = game.qa();


    }




    public void onDone(View v)
    {
        this.inputtedAnswer = ((EditText)findViewById(R.id.answer)).getText().toString();
        s = s + ((TextView)findViewById(R.id.question)).getText().toString()+"\n"+
                "Your Answer: "+ inputtedAnswer.toUpperCase()+"\n"+"Correct Answer: " + this.correctAnswer+ "\n" + "\n";
        s = s + "\n\n";
        try {
            if (this.inputtedAnswer.equals("?")) {
                String statement = "The correct answer is one of these two possible options: " + correctAnswer + " or " + randomAnswer + "!";

                Toast label = Toast.makeText(getApplicationContext(), statement, Toast.LENGTH_LONG);
                label.show();
            }


            if (this.qNum == 10) {
                ((TextView) findViewById(R.id.qNum)).setText("Q#" + qNum);
                ((TextView) findViewById(R.id.score)).setText("Score= " + score);
                ((TextView) findViewById(R.id.question)).setText("FINAL SCORE: " + score);
                s = "GAME OVER! App will Close if \"DONE\" button is pressed!";

            } else if (this.qNum == 11) {
                finish();
            } else if (this.inputtedAnswer.equalsIgnoreCase(this.correctAnswer)) {
                this.score = this.score + 1;
                ((TextView) findViewById(R.id.score)).setText("Score= " + this.score);


                ((TextView) findViewById(R.id.qNum)).setText("Q# " + qNum);

            } else {
                ((TextView) findViewById(R.id.qNum)).setText("Q# " + qNum);
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