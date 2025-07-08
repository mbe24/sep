package org.beyene.sep.model.ast;

import java.util.StringJoiner;

/**
 * Represents a number expression leaf node in an abstract syntax tree.
 * <p>
 * The expression evaluates to a given number.
 */
public class NumberExpression implements Expression {

    private final int value;

    /**
     * Constructs a new number expression
     *
     * @param value
     */
    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NumberExpression.class.getSimpleName() + "[", "]")
                .add(Integer.toString(value))
                .toString();
    }
}
