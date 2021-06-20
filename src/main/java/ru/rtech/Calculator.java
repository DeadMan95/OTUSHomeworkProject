package ru.rtech;

public class Calculator {

    public Integer squareRoot(Integer val1) {
        return (int) Math.sqrt((double) val1);
    }

    public Integer exponentiation(Integer val1, Integer val2) {
        int result = 1;
        for (int i = 1; i <= val2; i++) {
            result = result * val1;
        }
        return result;
    }

    public Integer factorial(Integer val1) {
        if (val1 <= 1) {
            return 1;
        } else {
            return val1 * factorial(val1 - 1);
        }
    }

}
