package model.expression;

import exceptions.InvalidType;
import exceptions.UnrecognizedOperator;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.values.*;

public class RelationalExpression implements Expression{
    private final Expression left;
    private final Expression right;
    private final String operator;


    public RelationalExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public BoolValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception {
        IntValue firstValue = (IntValue)left.evaluate(symbolsTable, heapTable);
        IntValue secondValue = (IntValue)right.evaluate(symbolsTable, heapTable);
        int firstInt = firstValue.getValue();
        int secondInt = secondValue.getValue();
        switch (operator){
            case "<":
                return new BoolValue(firstInt < secondInt);
            case "<=":
                return new BoolValue(firstInt <= secondInt);
            case "==":
                return new BoolValue(firstInt == secondInt);
            case "!=":
                return new BoolValue(firstInt != secondInt);
            case ">":
                return new BoolValue(firstInt > secondInt);
            case ">=":
                return new BoolValue(firstInt >= secondInt);
            default:
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
                return new BoolType();
            }
            else{
                throw new InvalidType("Second operand is not an integer!");
            }
        }
        else{
            throw new InvalidType("First operand is not an integer!");
        }
    }


    public String toString(){
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }
}
