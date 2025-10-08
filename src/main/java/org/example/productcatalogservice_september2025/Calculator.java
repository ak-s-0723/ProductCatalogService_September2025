package org.example.productcatalogservice_september2025;

public class Calculator {
    public Calculator() {
    }

    int add(int a,int b) {
        return a+b;
    }

    int divide(int a,int b) {
        try {
            return a/b;
        }catch (ArithmeticException exception) {
            throw exception;
        }
    }
}
