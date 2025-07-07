package org.beyene.sep.model.ast;

import java.util.StringJoiner;

public class NumberExpression implements Expression {

    private final int value;

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
