package edu.qc.seclass;

public class BuggyClass {

    //task1
    public double buggyMethod1(double numerator, double denominator){

        double quotient = 0;

        /*
         1. 100% statement coverage and does not reveal fault
         2. less than 50% statement coverage, reveals fault - See TestSC1b for test.
         */
        if(numerator > 0) {
            quotient = numerator / denominator;
            return quotient;
        }
        else if(denominator > 0){
            quotient = numerator / denominator;
            return quotient;
        }
        else if(numerator < 0){
            quotient = numerator / denominator;
            return quotient;
        }
        else if(denominator < 0){
            quotient = numerator / denominator;
            return quotient;
        }
        else if(numerator == 0) {
            quotient = numerator / denominator;
            return quotient;
        }

        return quotient;

//        if(a > 0 && b > 0 )
//            return result;
//
//        else if(a < 0 || b < 0)
//            return result;
//
//        else if (a > 0 || b < 0)
//            return result;
//
//        else if (a < 0 || b > 0)
//            return result;
    }

    //TODO: Task 2
    public double buggyMethod2(double numerator, double denominator){

        double quotient = numerator / denominator;

        /*
            1.  100% statement coverage and does not reveal fault.
            2.  More than 50% branch coverage reveals fault
         */
        if(quotient > 0)
            System.out.println("result > 0, " + quotient);
        else if(quotient < 0)
            System.out.println("result < 0, " + quotient);
        else
            System.out.println("K.");
        return quotient;


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
