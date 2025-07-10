package org.beyene.sep.model.op;

import java.util.Optional;

/**
 * Interface that represents mathematical binary operators
 */
public sealed interface Operator permits StandardOperator {
    int getPrecedence();
    int apply(int left, int right);
    char getSymbol();

    /**
     * A centralized factory to find any operator implementation by its symbol.
     * Currently, it only supports standard operators.
     */
    static Operator fromSymbol(char symbol) {
        Optional<StandardOperator> standardOp = Optional.ofNullable(StandardOperator.fromSymbol(symbol));
        if (standardOp.isPresent())
            return standardOp.get();

        throw new IllegalArgumentException("Operator not supported: " + symbol);
    }

}