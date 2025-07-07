package org.beyene.sep;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();

        String expr = "2 + 3";
        int result = calculator.calculate(expr);
        System.out.printf("%s = %d%n", expr, result);

        expr = "3 * 2 + 1";
        result = calculator.calculate(expr);
        System.out.printf("%s = %d%n", expr, result);

        expr = "3 * -2 + 6";
        result = calculator.calculate(expr);
        System.out.printf("%s = %d%n", expr, result);
    }
}