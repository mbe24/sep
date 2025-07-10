package org.beyene.sep.model.ast.construction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveDescentAlgorithmTest {

    private final AstConstructionStrategy strategy =  new RecursiveDescentAlgorithm();

    @Test
    @DisplayName("Simple addition")
    void shouldCalculateSimpleAddition() {
        String expression = "2 + 3";
        int result = strategy.buildAst(expression.split(" ")).evaluate();
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Long addition")
    void shouldCalculateLongAddition() {
        String expression = "1 + 2 + 3 + 4 + 5";
        int result = strategy.buildAst(expression.split(" ")).evaluate();
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Operator precedence (multiplication before addition)")
    void shouldCorrectlyApplyOperatorPrecedence() {
        String expression = "3 * 2 + 1";
        int result = strategy.buildAst(expression.split(" ")).evaluate();
        assertEquals(7, result);
    }

    @Test
    @DisplayName("Operation chain (addition and multiplication)")
    void shouldCalculateOperationChain() {
        String expression = "0 + 1 * 2 + 3 * 4 + 5 * 6 + 7";
        int result = strategy.buildAst(expression.split(" ")).evaluate();
        assertEquals(51, result);
    }

    @Test
    @DisplayName("Negative number and operator precedence")
    void shouldHandleNegativeNumbersAndPrecedence() {
        String expression = "3 * -2 + 6";
        int result = strategy.buildAst(expression.split(" ")).evaluate();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Division by zero")
    void shouldThrowExceptionForDivisionByZero() {
        String expression = "5 / 0";
        assertThrows(ArithmeticException.class, () -> strategy.buildAst(expression.split(" ")).evaluate());
    }

    @Test
    @DisplayName("No fully space-separated expression")
    void shouldThrowExceptionForPartiallyMissingSpaces() {
        String expression = "3 + 4*5";
        assertThrows(IllegalArgumentException.class, () -> strategy.buildAst(expression.split(" ")).evaluate());
    }

    @Test
    @DisplayName("Incomplete expression")
    void shouldThrowExceptionForMissingOperand() {
        String expression = "3 + ";
        assertThrows(IllegalArgumentException.class, () -> strategy.buildAst(expression.split(" ")).evaluate());
    }

}