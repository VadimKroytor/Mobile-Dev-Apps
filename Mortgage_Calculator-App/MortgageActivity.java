package com.example.vadim.mcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MortgageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgage_layout);
    }

    public void buttonClicked(View v) {
        EditText principleView = (EditText)findViewById(R.id.principleInput);
        String principle = principleView.getText().toString();
        EditText amortizationView = (EditText) findViewById(R.id.amortizationInput);
        String amortization = amortizationView.getText().toString();
        EditText interestView = (EditText) findViewById(R.id.interestInput);
        String interest = interestView.getText().toString();
        MortgageModel mortgageCalc = new MortgageModel(principle, amortization, interest);
        String outputMortgage = mortgageCalc.computePayment();
        ((TextView)findViewById(R.id.mortgageAmt)).setText(outputMortgage);
    }

    public void secondButtonClicked (View v) {
        EditText principleView = (EditText)findViewById(R.id.principleInput);
        String principle = principleView.getText().toString();
        EditText amortizationView = (EditText) findViewById(R.id.amortizationInput);
        String amortization = amortizationView.getText().toString();
        EditText interestView = (EditText) findViewById(R.id.interestInput);
        String interest = interestView.getText().toString();
        MortgageModel mortgageCalc = new MortgageModel(principle, amortization, interest);

        String outputOutstanding = mortgageCalc.outstandingAfter("60");
        ((TextView)findViewById(R.id.outstandingAmt)).setText(outputOutstanding);
    }
}
