package org.beyene.sep.model.ast.construction;

import org.beyene.sep.model.ast.BinaryExpression;
import org.beyene.sep.model.ast.Expression;
import org.beyene.sep.model.ast.NumberExpression;
import org.beyene.sep.model.op.Operator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Reads a string token array and constructs an abstract syntax tree using the Shunting-yard algorithm.
 */
public class ShuntingYardAlgorithm implements AstConstructionStrategy {

    @Override
    public Expression buildAst(String[] tokens) {
        // AST nodes
        Deque<Expression> expressionStack = new ArrayDeque<>();
        Deque<Operator> operatorStack = new ArrayDeque<>();

        // The expression  must start with a number.
        boolean expectNumber = true;
        for (String token : tokens) {
            if (expectNumber) {
                handleNumber(expressionStack, token);
                expectNumber = false;
            } else {
                handleOperatorAndPrecedence(expressionStack, operatorStack, token);
                expectNumber = true;
            }
        }

        // The expression must end with a number
        if (expectNumber)
            throw new IllegalArgumentException("Expression cannot end with an operator.");

        // Apply any remaining operators on the stack.
        while (!operatorStack.isEmpty())
            buildBinaryExpressionFromOperator(expressionStack, operatorStack);

        // There must be exactly one expression node left on the stack.
        if (expressionStack.size() != 1)
            throw new IllegalStateException("Malformed expression: The final expression is invalid.");

        return expressionStack.pop();
    }

    private void handleNumber(Deque<Expression> expressionStack, String token) {
        try {
            int value = Integer.parseInt(token);
            expressionStack.push(new NumberExpression(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid expression: Expected a number but got '" + token + "'.");
        }
    }

    private void handleOperatorAndPrecedence(Deque<Expression> expressionStack, Deque<Operator> operatorStack, String token) {
        if (token.length() != 1)
            throw new IllegalArgumentException("Invalid expression: Operator token '" + token + "' is invalid.");

        Operator currentOp = Operator.fromSymbol(token.charAt(0));
        // This is the core Shunting-yard logic for precedence.
        // If the current operator has higher precedence, we create the high-precedence expression on the spot
        while (!operatorStack.isEmpty() && operatorStack.peek().getPrecedence() >= currentOp.getPrecedence())
            buildBinaryExpressionFromOperator(expressionStack, operatorStack);

        operatorStack.push(currentOp);
    }


    private void buildBinaryExpressionFromOperator(Deque<Expression> expressionStack, Deque<Operator> operatorStack) {
        try {
            Operator op = operatorStack.pop();
            Expression right = expressionStack.pop();
            Expression left = expressionStack.pop();

            expressionStack.push(new BinaryExpression(left, op, right));
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Malformed expression: Missing an operand for an operator.", e);
        }
    }
}
