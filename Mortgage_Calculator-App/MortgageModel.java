package com.example.vadim.mcalc;

public class MortgageModel {
    private double principle;
    private double amortization;
    private double interest;
    private double monthlyPayment;
    private double x;
    public MortgageModel (String p, String a, String i)
    {
        this.principle = Double.parseDouble(p);
        this.amortization = Double.parseDouble(a);
        this.interest = Double.parseDouble(i);
    }

    public String computePayment() {
        double r =  (this.interest/100)/12;
        double n =  (this.amortization)*12;
        double mortgage = (r*this.principle)/(1 - (Math.pow(1+r, -n)));
        this.monthlyPayment = mortgage;
        String mortgageInString = String.format("$%,.2f", mortgage);
        return mortgageInString;
    }

    public String outstandingAfter(String x) {
        computePayment();
        int xAsInt = Integer.parseInt(x);
        double r =  (this.interest/100)/12;
        double outstandingAmt = this.principle - (((this.monthlyPayment)/r)- this.principle)*(((Math.pow(1+r, xAsInt)) - 1));
        String getOutstanding = String.format("$%,.0f", outstandingAmt);
        return getOutstanding;
    }
    public static void main(String[] args) {
        MortgageModel mortgageCalc = new MortgageModel("700000", "25", "2.75");
        String computeMortgage = mortgageCalc.computePayment();
        String computeOutstanding = mortgageCalc.outstandingAfter("60");
        System.out.println(computeMortgage + " & outstanding of " + computeOutstanding);

        }
    }

