package org.beyene.sep.model.ast.construction;

import org.beyene.sep.model.ast.Expression;

/**
 * Interface for AST construction strategies
 */
public interface AstConstructionStrategy {

    /**
     * Reads a string token array and constructs an abstract syntax tree using the underlying strategy.
     *
     * @param tokens tokenized string representation of expression
     * @return The AST for the integer expression
     */
    Expression buildAst(String[] tokens);

}
