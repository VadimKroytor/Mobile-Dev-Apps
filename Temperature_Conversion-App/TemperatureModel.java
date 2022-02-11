package com.example.vadim.temperature;

public class TemperatureModel {
    private String celcius;
    private String farenheit;
    private double tempInput;

    public TemperatureModel(String temp) { //constructor class initializing global variables :-)
        tempInput = Double.parseDouble(temp);
    }

    public String computeCelcius() {


        double conversionToCelcius = 5*(this.tempInput - 32)/9;
        String farenheitToCelcius = String.format("%.1f", conversionToCelcius);
        return farenheitToCelcius;



    }

    public String computeFarenheit() {

        double conversionToFarenheight = 32 + 9*this.tempInput/5;
        String celciusToFarenheight = String.format("%.1f", conversionToFarenheight);
        return celciusToFarenheight;
    }

    public static void main (String[] args) {
        TemperatureModel variables = new TemperatureModel("55");
        String biscuitsOne = variables.computeCelcius();
        String biscuitsTwo = variables.computeFarenheit();

        System.out.println(biscuitsOne + " " + biscuitsTwo);
    }
}
