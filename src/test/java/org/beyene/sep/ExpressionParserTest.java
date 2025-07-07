package org.beyene.sep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionParserTest {

    private ExpressionParser calculator =  new ExpressionParser();

    @Test
    @DisplayName("Simple addition")
    void shouldCalculateSimpleAddition() {
        String expression = "2 + 3";
        int result = calculator.buildAst(expression).evaluate();
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Operator precedence (multiplication before addition)")
    void shouldCorrectlyApplyOperatorPrecedence() {
        String expression = "3 * 2 + 1";
        int result = calculator.buildAst(expression).evaluate();
        assertEquals(7, result);
    }

    @Test
    @DisplayName("Negative number and operator precedence")
    void shouldHandleNegativeNumbersAndPrecedence() {
        String expression = "3 * -2 + 6";
        int result = calculator.buildAst(expression).evaluate();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Division by zero")
    void shouldThrowExceptionForDivisionByZero() {
        String expression = "5 / 0";
        assertThrows(ArithmeticException.class, () -> calculator.buildAst(expression).evaluate());
    }
}