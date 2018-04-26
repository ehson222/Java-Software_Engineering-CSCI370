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

        else if (a < 0 || b > 0)
            return result;

        return result;
    }

    //TODO: Task 2
    public double buggyMethod2(double a, double b){
        return  a / b;

    }

    //TODO: Task 3
    public double buggyMethod3(double a, double b){
        return a/b;
    }

    //TODO: Task 4
    public double buggyMethod4(double a, double b){
        return a/b;

    }

    //TODO: Task 5
    public double buggyMethod5(double a, double b){
        return a/b;
    }
}
