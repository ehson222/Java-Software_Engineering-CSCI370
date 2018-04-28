package edu.qc.seclass;

public class BuggyClass {

    public double buggyMethod1(double numerator, double denominator) {

        double quotient = numerator / denominator;

        /*
            1.  100% statement coverage and does not reveal fault.
            2.  More than 50% statement coverage reveals fault
         */
        if (quotient > 0)
            System.out.println("result > 0, " + quotient);
        else if (quotient < 0)
            System.out.println("result < 0, " + quotient);

        return quotient;
    }

//        double quotient = 0;
//
//        /*
//         1. 100% statement coverage and does not reveal fault
//         2. less than 50% statement coverage, reveals fault - See TestSC1b for test.
//         */
//        if(numerator > 0) {
//            quotient = numerator / denominator;
//            return quotient;
//        }
//        else if(denominator > 0){
//            quotient = numerator / denominator;
//            return quotient;
//        }
//        else if(numerator < 0){
//            quotient = numerator / denominator;
//            return quotient;
//        }
//        else if(denominator < 0){
//            quotient = numerator / denominator;
//            return quotient;
//        }
//        else if(numerator == 0) {
//            quotient = numerator / denominator;
//            return quotient;
//        }
//
//        return quotient;

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
    public void buggyMethod3(){

        /*
         *  Not possible.
         *
         *
         *
         */

    }

    public int buggyMethod4(boolean a, boolean b){
        /**
         * 100% statement coverage reveals fault,
         * 100% branch coverage and does not reveal fault
         */
        double x = 5;
        double y = 0;

        if(a){
            x = x + y;
            System.out.println(x);
        }
        else if(!b){
            x = x / y;
            System.out.println("Error can't divide by 0");
        }
        else
            System.out.println("Do nothing");

        return (int) x;
    }


    //TODO: Task 5
    public double buggyMethod5(double a, double b){
        return a/b;
    }
}
