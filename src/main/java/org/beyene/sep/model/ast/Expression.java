package org.beyene.sep.model.ast;

public interface Expression {

    /**
     * Evaluates expression node and all its children
     *
     * @return integer result of expression
     */
    int evaluate();

}
