package com.example.vadim.temperature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_layout);
    }

    public void celciusButton (View v) {
        EditText tempView = (EditText) findViewById(R.id.tempInput);
        String temperature = tempView.getText().toString();
        TemperatureModel tempCalc = new TemperatureModel(temperature);
        String outputCelcius = tempCalc.computeCelcius();
        ((TextView)findViewById(R.id.tempInput)).setText(outputCelcius);
    }

    public void farenheitButton (View v) {
        EditText tempView = (EditText) findViewById(R.id.tempInput);
        String temperature = tempView.getText().toString();
        TemperatureModel tempCalc = new TemperatureModel(temperature);
        String outputFarenheit = tempCalc.computeFarenheit();
        ((TextView)findViewById(R.id.tempInput)).setText(outputFarenheit);
    }

    public void clearButton (View v) {
        ((TextView)findViewById(R.id.tempInput)).setText("");

    }
}
