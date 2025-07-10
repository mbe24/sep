package org.beyene.sep.model.ast.construction;

import org.beyene.sep.model.ast.BinaryExpression;
import org.beyene.sep.model.ast.Expression;
import org.beyene.sep.model.ast.NumberExpression;
import org.beyene.sep.model.op.Operator;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Reads a string token array and constructs an abstract syntax tree using recursive descent.
 * <p>
 * The algorithm is based on three grammar production rules:
 * (1) Expression = Term (( '+' | '-') Term)*
 * (2) Term = Factor (('*'| '/') Factor)*
 * (3) Factor = n
 */
public class RecursiveDescentAlgorithm implements AstConstructionStrategy {

    @Override
    public Expression buildAst(String[] tokens) {
        Deque<String> queue = new LinkedList<>(Arrays.asList(tokens));
        return parseExpression(queue);
    }

    // Expression = Term (( '+' | '-') Term)*
    // Represents lowest operator precedence
    private Expression parseExpression(Deque<String> tokens) {
        Expression left = parseTerm(tokens);

        while ("+".equals(tokens.peek()) || "-".equals(tokens.peek())) {
            String token = tokens.poll();
            Operator op = getOperator(token);

            Expression right = parseTerm(tokens);
            left = new BinaryExpression(left, op, right);
        }

        return left;
    }

    // Term = Factor (('*'| '/') Factor)*
    // Represents medium operator precedence
    private Expression parseTerm(Deque<String> tokens) {
        Expression left = parseFactor(tokens);

        while ("*".equals(tokens.peek()) || "/".equals(tokens.peek())) {
            String token = tokens.poll();
            Operator op = getOperator(token);

            Expression right = parseFactor(tokens);
            left = new BinaryExpression(left, op, right);
        }

        return left;
    }

    private Operator getOperator(String token) {
        if (token.length() != 1)
            throw new IllegalArgumentException("Invalid expression: Operator token '" + token + "' is invalid.");

        return Operator.fromSymbol(token.charAt(0));
    }

    // Factor = n
    // Represents highest operator precedence
    private Expression parseFactor(Deque<String> tokens) {
        String token = tokens.poll();
        if (Objects.isNull(token))
            throw new IllegalArgumentException("Invalid expression: Expected a number");

        try {
            int value = Integer.parseInt(token);
            return new NumberExpression(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid expression: Expected a number but got '" + token + "'.");
        }
    }
}
