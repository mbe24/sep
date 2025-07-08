package org.beyene.sep.model.ast;

import org.beyene.sep.model.op.Operator;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a binary expression node in an abstract syntax tree.
 * <p>
 * For its evaluation, the expression combines a left and right sub-expression using an operator.
 */
public class BinaryExpression implements Expression {

    private final Expression left;
    private final Operator operator;
    private final Expression right;

    /**
     * Constructs a new binary expression
     *
     * @param left The left-hand side expression (first operand)
     * @param operator The operator that is applied
     * @param right The right-hand side expression (second operator)
     */
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
