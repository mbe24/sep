package org.beyene.sep.model.ast;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberExpressionTest {

    @Test
    @DisplayName("Number expression evaluation")
    void testEvaluate() {
        int value = 5;
        NumberExpression ne = new NumberExpression(value);
        assertEquals(value, ne.evaluate());
    }
}