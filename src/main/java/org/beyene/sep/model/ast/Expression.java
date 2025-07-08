package org.beyene.sep.model.ast;

/**
 * Represents a node in an abstract syntax tree.
 *
 */
public interface Expression {

    /**
     * Evaluates the expression node and all its children.
     *
     * @return calculated integer result of the expression
     */
    int evaluate();

}
