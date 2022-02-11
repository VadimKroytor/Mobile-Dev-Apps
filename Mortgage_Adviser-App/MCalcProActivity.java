package com.example.vadim.mcalcpro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ca.roumani.i2c.MPro;

public class MCalcProActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener {

    private TextToSpeech tts;
    private MPro mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalc_pro_layout);
        this.tts = new TextToSpeech(this, this);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mp = new MPro();

    }

    public void onAccuracyChanged(Sensor arg0, int at1) {
    }

    public void onSensorChanged(SensorEvent event) {
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];

        double a = Math.sqrt(Math.pow(ax, 2) + Math.pow(ay, 2) + Math.pow(az, 2));
        if (a > 20) {
            ((EditText) findViewById(R.id.cashPriceId)).setText("");
            ((EditText) findViewById(R.id.amortizationId)).setText("");
            ((EditText) findViewById(R.id.interestId)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }
        ((TextView) findViewById(R.id.accelValues)).setText(String.format("%.0f", ax) + ", " + String.format("%.0f", ay) + ", " + String.format("%.0f", az));
    }

    public void onInit(int initStatus) {
        this.tts.setLanguage(Locale.US);
    }

    public String getErrorMessage() {
        EditText priceView = (EditText) findViewById(R.id.cashPriceId);
        double price = Double.parseDouble(priceView.getText().toString());
        EditText amortizationView = (EditText) findViewById(R.id.amortizationId);
        double amortization = Double.parseDouble(amortizationView.getText().toString());
        EditText interestView = (EditText) findViewById(R.id.interestId);
        double interest = Double.parseDouble(interestView.getText().toString());
        String statement = "";
        if (amortization < MPro.AMORT_MIN || amortization > MPro.AMORT_MAX) {
            statement = "Please input an amortization in the range of 20 to 30, inclusively.";
        } else if (interest > MPro.INTEREST_MAX || interest < 0) {
            statement = "Please input an interest value in the range of 0 to 50, inclusively.";
        }
        return statement;
    }

    public void analyzeButtonClicked(View v) {

        EditText priceView = (EditText) findViewById(R.id.cashPriceId);
        String price = priceView.getText().toString();
        EditText amortizationView = (EditText) findViewById(R.id.amortizationId);
        String amortization = amortizationView.getText().toString();
        EditText interestView = (EditText) findViewById(R.id.interestId);
        String interest = interestView.getText().toString();
        try {

            mp.setPrinciple(price);
            mp.setAmortization(amortization);
            mp.setInterest(interest);
            String s = "Monthly Payment = " + mp.computePayment("%,.2f");
            s = s + "\n\n";
            s = s + "By making this payments monthly for " + amortization + " years, the mortgage " +
                    "will be paid in full. But if you terminate the mortgage on its nth anniversary, " +
                    "the balance still owing depends on n as shown below:" + "\n\n";
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
            int counter = 0;
            s += String.format("%5s", "n") + String.format("%16s", "Balance") + "\n\n";
            for (; counter <= Integer.parseInt(amortization); ) {
                String nthYear = "n";
                String month = "month";

                s += String.format("%5d", counter) + mp.outstandingAfter(counter, "%,16.0f");
                s += "\n\n";
                counter++;
                ((TextView) findViewById(R.id.output)).setText(s);
            }
        } catch (Exception e) {
            Toast label = Toast.makeText(this, /*getErrorMessage() is the error message I created*/ e.getMessage(),
                    /*is the message that was pre-created by someone else with its own API */ Toast.LENGTH_SHORT);
            label.show();

        }
    }
}