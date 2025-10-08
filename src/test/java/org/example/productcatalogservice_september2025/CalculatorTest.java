package org.example.productcatalogservice_september2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class CalculatorTest {

    @Test
    public void TestAdditionOn2Integers_RunSuccessfully() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(1,2);

        //Assert
        assert(result == 3);
    }

    @Test
    public void TestDivideByZero_ResultsInArithmeticException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class,
                ()->calculator.divide(1,0));
    }

}