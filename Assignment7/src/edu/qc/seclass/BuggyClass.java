package edu.qc.seclass;

/**
 * Reference:  Udacity - https://classroom.udacity.com/courses/ud805
 */

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

    public double buggyMethod2(double numerator, double denominator) {

        double quotient = numerator / denominator;

        /*
            1.  100% statement coverage and does not reveal fault.
            2.  More than 50% branch coverage reveals fault
         */
        if (quotient > 0)
            System.out.println("result > 0, " + quotient);
        else if (quotient < 0)
            System.out.println("result < 0, " + quotient);
        else
            System.out.println("K.");
        return quotient;
    }

    public void buggyMethod3() {
        /*
         *  Having 100% branch coverage means that there is also a 100% statement coverage, and it
         *  can't be the other way around.  100% Branch coverage with does not reveal fault and a 100% statement coverage,
         *  not achieve full (100%) branch coverage reveals fault is not possible.  Not achieving full branch means
         *  it also not achieving full statement branch.  Full branch coverage no fault reveal also means full statement
         *  coverage no fault reveal.
         *
         *  Statement coverage falls under branch
         *  coverage because branch coverage is more expensive to do but it is more
         *  reliable method for testing while statement is less expensive, easier to do
         *  and it used mostly in the software testing industry.
         *
         *  Therefore, 100% branch does not reveal fault AND 100% statement coverage
         *  DOES NOT achieve 100% branch coverage reveals fault IS NOT possible.
         *
         */
    }

    public int buggyMethod4(boolean a, boolean b) {
        /**
         * 100% statement coverage reveals fault,
         * 100% branch coverage and does not reveal fault
         */
        double x = 5;
        double y = 0;

        if (a) {
            x = x + y;
            System.out.println(x);
        } else if (!b) {
            x = x / y;
            //System.out.println("Error can't divide by 0");
        } else
            System.out.println("Do nothing");

        return (int) x;
    }

    public void buggyMethod5() {
        //1. int x;
        //2. [point where you can add code]
        //4. x = i / 0;
        //5. [point where you can add code]

//        Creating a test suite that achieves 100% statement coverage and does not reveal fault
//        with constraints given is not possible with this method.  100% statement coverage means
//        every line in the code from line 1 - 4 in the method has to be executed.  At line 4,
//        the code will produce an error and won't execute line 5.  Whatever code is added in line 2,
//        it can't skip line 4 and always reveal the error.  Therefore, it is not possible for this
//        method to have a 100% coverage and not reveal fault
    }
}

