package org.beyene.sep.model.op;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardOperatorTest {

    @Test
    @DisplayName("Test addition")
    void testAddition() {
        int result = StandardOperator.ADDITION.apply(1, 1);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Test subtraction")
    void testSubtraction() {
        int result = StandardOperator.SUBTRACTION.apply(1, 1);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiplication() {
        int result = StandardOperator.MULTIPLICATION.apply(2, 2);
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Test division")
    void testDivision() {
        int result = StandardOperator.DIVISION.apply(4, 2);
        assertEquals(2, result);
    }

}