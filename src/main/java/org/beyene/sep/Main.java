package org.beyene.sep;

import org.beyene.sep.model.ast.Expression;
import org.beyene.sep.model.ast.construction.RecursiveDescentAlgorithm;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser(new RecursiveDescentAlgorithm());

        Stream.of(
                "2 + 3",
                "3 * 2 + 1",
                "3 * -2 + 6").forEach(stringExpr -> {
            Expression expression = parser.buildAst(stringExpr);
            int result = expression.evaluate();
            System.out.printf("%s = %d%n%s%n%n", stringExpr, result, expression);
        });
    }
}