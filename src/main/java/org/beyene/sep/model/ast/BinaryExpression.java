package org.beyene.sep.model.ast;

import org.beyene.sep.model.op.Operator;

import java.util.Objects;
import java.util.StringJoiner;

public class BinaryExpression implements Expression {

    private final Expression left;
    private final Operator operator;
    private final Expression right;

    public BinaryExpression(Expression left, Operator operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public int evaluate() {
        int leftValue = left.evaluate();
        int rightValue = right.evaluate();
        return operator.apply(leftValue, rightValue);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BinaryExpression.class.getSimpleName() + "[", "]")
                .add(Objects.toString(left))
                .add(Objects.toString(operator.getSymbol()))
                .add(Objects.toString(right))
                .toString();
    }
}
