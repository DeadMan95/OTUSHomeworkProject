package ru.rtech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

    Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("test sqrt")
    void test1() {
        Integer val1 = 25;
        Integer result = 5;
        assert calculator.squareRoot(val1).equals(result);
    }

    @Test
    @DisplayName("test exponentiation")
    void test2() {
        Integer val1 = 10;
        Integer val2 = 3;
        Integer result = 1000;
        assert calculator.exponentiation(val1, val2).equals(result);
    }

    @Test
    @DisplayName("test factorial")
    void test3() {
        Integer val1 = 5;
        Integer result = 120;
        assert calculator.factorial(val1).equals(result);
    }

}
