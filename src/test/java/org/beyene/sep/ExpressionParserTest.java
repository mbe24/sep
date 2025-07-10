package org.beyene.sep;

import org.beyene.sep.model.ast.construction.ShuntingYardAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionParserTest {

    private final ExpressionParser strategy =  new ExpressionParser(new ShuntingYardAlgorithm());

    @Test
    @DisplayName("Empty expression")
    void shouldThrowExceptionForEmptyExpression() {
        String expression = "";
        assertThrows(IllegalArgumentException.class, () -> strategy.buildAst(expression).evaluate());
    }

    @Test
    @DisplayName("No space-separated expression")
    void shouldThrowExceptionForMissingSpaces() {
        String expression = "3+4";
        assertThrows(IllegalArgumentException.class, () -> strategy.buildAst(expression).evaluate());
    }

}