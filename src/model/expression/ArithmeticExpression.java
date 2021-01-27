package model.expression;

import exceptions.DivisionByZero;
import exceptions.UnrecognizedOperator;
import exceptions.InvalidType;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticExpression implements Expression{
    private final Expression left;
    private final Expression right;
    private final String operator;

    public ArithmeticExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public IntValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception{
        IntValue firstValue = (IntValue)left.evaluate(symbolsTable, heapTable);
        IntValue secondValue = (IntValue)right.evaluate(symbolsTable, heapTable);
        int firstInteger = firstValue.getValue();
        int secondInteger = secondValue.getValue();
        if (operator.equals("+")){
            return new IntValue(firstInteger + secondInteger);
        }
        if (operator.equals("-")){
            return new IntValue(firstInteger - secondInteger);
        }
        if (operator.equals("*")){
            return new IntValue(firstInteger * secondInteger);
        }
        else if (operator.equals("/")){
            if (secondInteger == 0){
                throw new DivisionByZero("Cannot divide by zero!");
            }
            return new IntValue(firstInteger/secondInteger);
        }
        else{
            throw new UnrecognizedOperator("Operator is invalid!");
        }
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType type1, type2;
        type1 = left.typeCheck(typeEnv);
        type2 = right.typeCheck(typeEnv);
        if (type1.equals(new IntType())){
            if (type2.equals(new IntType())){
                return new IntType();
            }
            else{
                throw new InvalidType("Second operand is not an integer!");
            }
        }
        else{
            throw new InvalidType("First operand is not an integer!");
        }
    }


    public String toString() {
        switch (operator) {
            case "+":
                return "(" + left.toString() + "+" + right.toString() + ")";
            case "-":
                return "(" + left.toString() + "-" + right.toString() + ")";
            case "*":
                return "(" + left.toString() + "*" + right.toString() + ")";
        }
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}
