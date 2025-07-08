package org.beyene.sep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionParserTest {

    private final ExpressionParser parser =  new ExpressionParser();

    @Test
    @DisplayName("Simple addition")
    void shouldCalculateSimpleAddition() {
        String expression = "2 + 3";
        int result = parser.buildAst(expression).evaluate();
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Operator precedence (multiplication before addition)")
    void shouldCorrectlyApplyOperatorPrecedence() {
        String expression = "3 * 2 + 1";
        int result = parser.buildAst(expression).evaluate();
        assertEquals(7, result);
    }

    @Test
    @DisplayName("Negative number and operator precedence")
    void shouldHandleNegativeNumbersAndPrecedence() {
        String expression = "3 * -2 + 6";
        int result = parser.buildAst(expression).evaluate();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Division by zero")
    void shouldThrowExceptionForDivisionByZero() {
        String expression = "5 / 0";
        assertThrows(ArithmeticException.class, () -> parser.buildAst(expression).evaluate());
    }

    @Test
    @DisplayName("Empty expression")
    void shouldThrowExceptionForEmptyExpression() {
        String expression = "";
        assertThrows(IllegalArgumentException.class, () -> parser.buildAst(expression).evaluate());
    }

    @Test
    @DisplayName("No space-separated expression")
    void shouldThrowExceptionForMissingSpaces() {
        String expression = "3+4";
        assertThrows(IllegalArgumentException.class, () -> parser.buildAst(expression).evaluate());
    }

    @Test
    @DisplayName("No fully space-separated expression")
    void shouldThrowExceptionForPartiallyMissingSpaces() {
        String expression = "3 + 4*5";
        assertThrows(IllegalArgumentException.class, () -> parser.buildAst(expression).evaluate());
    }

    @Test
    @DisplayName("Incomplete expression")
    void shouldThrowExceptionForMissingOperand() {
        String expression = "3 + ";
        assertThrows(IllegalArgumentException.class, () -> parser.buildAst(expression).evaluate());
    }
}