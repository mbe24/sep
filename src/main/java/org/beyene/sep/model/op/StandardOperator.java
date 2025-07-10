package org.beyene.sep.model.op;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

/**
 * Enum that encapsulates the four mathematical basic operations.
 */
public enum StandardOperator implements Operator {

    ADDITION('+', 1, (a, b) -> a + b),
    SUBTRACTION('-', 1, (a, b) -> a - b),
    MULTIPLICATION('*', 2, (a, b) -> a * b),
    DIVISION('/', 2, (a, b) -> a / b);

    private final char symbol;
    private final int precedence;
    private final IntBinaryOperator operation;

    StandardOperator(char symbol, int precedence, IntBinaryOperator operation) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.operation = operation;
    }

    @Override
    public int getPrecedence() {
        return this.precedence;
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public int apply(int left, int right) {
        return operation.applyAsInt(left, right);
    }

    private static final Map<Character, StandardOperator> SYMBOL_MAP = Arrays.stream(values()).collect(Collectors.toMap(StandardOperator::getSymbol, Function.identity()));

    public static StandardOperator fromSymbol(char symbol) {
        return SYMBOL_MAP.get(symbol);
    }
}