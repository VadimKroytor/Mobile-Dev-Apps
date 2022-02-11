package com.example.vadim.randomcolours;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.roumani.i2c.Utility;

public class RandomColorsActivity extends AppCompatActivity {
    private int redTint;
    private int greenTint;
    private int blueTint;
    private int increasingPushValue = 0;
    private double timer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_colors_layout);

        buttonClicked(findViewById(R.id.buttonId)); //invoking method
    }

    public void buttonClicked( View v)
    {
        int rgbValue = randomColorGenerator(); //invocation
        findViewById(R.id.buttonId).setBackgroundColor(rgbValue);

            double timerOn = Utility.mark()/1000;
            String timeSincePush = String.format("%.0f", timerOn);
            if (increasingPushValue == 0) {
                ((TextView) findViewById(R.id.timerId)).
                        setText("");
            }
            else {
                ((TextView) findViewById(R.id.timerId)).
                        setText(timeSincePush + " seconds since last push");
            }
            String convertedPushValue = Integer.toString(increasingPushValue);
        ((TextView) findViewById(R.id.pushValueId)).setText(convertedPushValue);
        increasingPushValue++;

        }
        /*
        double redPercent =  (double) this.redTint/255*100;
        double bluePercent = (double) this.blueTint/255*100;
        double greenPercent= (double) this.greenTint/255*100;
        String outputedPhrase = "R=" + String.format("%.0f",redPercent) + "%, G=" + String.format("%.0f",greenPercent) + "%, B=" +
                String.format("%.0f", bluePercent) + "%";
        ((TextView)findViewById(R.id.rgbListId)).setText(outputedPhrase); */

    public int randomColorGenerator()
    {
        this.redTint = (int) (256*Math.random());
        this.blueTint = (int) (256*Math.random());
        this.greenTint = (int) (256*Math.random());

        int rgbValue = Color.rgb(redTint, blueTint, greenTint);
        return rgbValue;
    }
}