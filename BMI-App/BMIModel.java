package com.example.vadim.bmi;


import ca.roumani.i2c.Utility;

public class BMIModel {
    private double weight;
    private double height;

    public BMIModel(String w, String h)
    {
        this.weight = Double.parseDouble(w);
        this.height = Double.parseDouble(h);
    }

    public String getBMI()
    {
        double index = this.weight / (this.height * this.height);
        String result = String.format("%.1f", index);
        return result;


    }

    public String toPound() {
        double convertKgToPd = this.weight/0.454;

        //String weightinPd = Double.toString(convertKgToPd);
        String weightinPd = String.format("%.1f", convertKgToPd);
        return weightinPd;
    }

    public String toFeetInch()
    {

        String convertToFtInch = Utility.m2FtInch(this.height);

        return convertToFtInch;

    }

    public static void main(String[] args)
    {
        BMIModel myModel = new BMIModel("100","1.8");
        System.out.println(myModel.getBMI());

        myModel = new BMIModel("45", "1.35");
        System.out.println(myModel.getBMI());

        myModel = new BMIModel("80", "1.2");
        System.out.println(myModel.getBMI());

        myModel = new BMIModel("59", "1.83");
        System.out.println("Your weight is " + myModel.toPound() + " lb, your height is " +
        myModel.toFeetInch() + ", and your BMI is " + myModel.getBMI());



    }

}


