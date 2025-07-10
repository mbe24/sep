package org.beyene.sep;

import org.beyene.sep.model.ast.Expression;
import org.beyene.sep.model.ast.construction.AstConstructionStrategy;
import org.beyene.sep.model.ast.construction.ShuntingYardAlgorithm;

/**
 * Reads a string expression and constructs an abstract syntax tree based on the given construction strategy.
 */
public class ExpressionParser {

    private final AstConstructionStrategy strategy;

    /**
     * Creates an expression parser with a given AST construction strategy.
     *
     * @param strategy underlying AST construction strategy
     */
    public ExpressionParser(AstConstructionStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Constructs an AST from a given expression.
     *
     * @param expression space-separated expression string
     * @return AST root node
     */
    public static Expression from(String expression) {
        return new ExpressionParser(new ShuntingYardAlgorithm()).buildAst(expression);
    }

    /**
     * Creates an AST for an integer expression given as a space-separated string.
     *
     * @param expression The string expression, e.g., "3 * -2 + 1"
     * @return The AST for the integer expression
     * @throws IllegalArgumentException if the expression is null, empty, or syntactically invalid
     * @throws IllegalStateException if AST construction reaches a state we can't recover from
     */
    public Expression buildAst(String expression) {
        if (expression == null || expression.isBlank())
            throw new IllegalArgumentException("Expression cannot be null or blank.");

        if (expression.indexOf(' ') == -1)
            throw new IllegalArgumentException("Expression does not contain any spaces.");

        String[] tokens = expression.split(" ");
        return strategy.buildAst(tokens);
    }

}