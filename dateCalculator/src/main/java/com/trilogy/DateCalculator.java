package com.trilogy;

public class DateCalculator {
    public static void main(String[] args) {
        System.out.println("Hello, world! I;m the date calculator");
    }
    public int calculateNumberofDaysInAMonth(int year, int month){
        int returnVal = 100;
        switch (month) {
            case 1:
                if(year % 4 == 0){
                    returnVal = 29;
                } else {
                    returnVal = 28;
                }
                if(year % 100 == 00){
                    returnVal = 28;
                }
                break;
                }

//            case 2:
//                returnVal = 28;
//                break;
//            case 12:
//                returnVal = 31;
//                break;
////            case 6:
////                returnVal = 30;
//                default:
//                    returnVal = 30;

        }
        return returnVal;
    }
    public int calculateLeapYear(int year){
        int retunVal = 28;
        switch(year) {
            case 1:
                retunVal = 29;
                break;
        }
        return retunVal;
    }
}
