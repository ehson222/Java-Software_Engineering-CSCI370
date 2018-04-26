package edu.qc.seclass;

public class BuggyClass {

    //task1
    public double buggyMethod1(double a, double b){

        double result = a / b;

        if(a > 0 && b > 0 )
            return result;

        else if(a < 0 || b < 0)
            return result;

        else if (a > 0 || b < 0)
            return result;
        else
            return 0;

    }
}
