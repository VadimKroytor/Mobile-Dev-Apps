package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);
    }

    public void buttonClicked(View v)
    {
        EditText weightView = (EditText)findViewById(R.id.weightBox);
        String weight = weightView.getText().toString();
        EditText heightView = (EditText)findViewById(R.id.heightBox);
        String height = heightView.getText().toString();

        BMIModel myModel = new BMIModel(weight, height);
        String myBMI = myModel.getBMI();

        ((TextView)findViewById(R.id.answer)).setText(myBMI);
    }
}


/* RandomColorsModel.java file
package com.example.vadim.randomcolours;

import android.graphics.Color;

public class RandomColorsModel {
    private int redTint;
    private int greenTint;
    private int blueTint;

    public RandomColorsModel(int r, int g, int b)
    {
        this.blueTint = r;
        this.greenTint = g;
        this.blueTint = b;
    }

    public int randomColorGenerator()
    {
        this.redTint = (int) (256*Math.random());
        this.blueTint = (int) (256*Math.random());
        this.greenTint = (int) (256*Math.random());

        int rgbValue = Color.rgb(redTint, blueTint, greenTint);
        return rgbValue;
    }

    public static void main(String[] args)
    {
        RandomColorsModel colorChanger = new RandomColorsModel(int value);

    }
}
 */