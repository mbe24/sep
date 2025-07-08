package org.beyene.sep.model.ast;

import org.beyene.sep.model.op.StandardOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryExpressionTest {

    @Test
    @DisplayName("Number expressions with addition")
    void testEvaluateWithNumberExpressions() {
        int value = 5;
        NumberExpression ne = new NumberExpression(value);
        NumberExpression ne2 = new NumberExpression(value);
        BinaryExpression be = new BinaryExpression(ne, StandardOperator.ADDITION, ne2);

        assertEquals(value + value, be.evaluate());
    }

    @Test
    @DisplayName("Binary expressions with addition")
    void testEvaluateWithBinaryExpressions() {
        int value = 3;
        NumberExpression ne = new NumberExpression(value);
        NumberExpression ne2 = new NumberExpression(value);
        BinaryExpression be = new BinaryExpression(ne, StandardOperator.ADDITION, ne2);

        NumberExpression ne3 = new NumberExpression(value);
        NumberExpression ne4 = new NumberExpression(value);
        BinaryExpression be2 = new BinaryExpression(ne3, StandardOperator.MULTIPLICATION, ne4);

        BinaryExpression be3 = new BinaryExpression(be, StandardOperator.ADDITION, be2);

        assertEquals((value + value) + (value * value), be3.evaluate());
    }
}