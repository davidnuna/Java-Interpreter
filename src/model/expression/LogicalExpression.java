package model.expression;

import exceptions.InvalidType;
import exceptions.UnrecognizedOperator;
import model.structures.HeapTable;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.types.IType;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

@SuppressWarnings("unused")
public class LogicalExpression implements Expression{
    private final Expression left;
    private final Expression right;
    private final String operator;

    public LogicalExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public BoolValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception {
        BoolValue firstValue = (BoolValue)left.evaluate(symbolsTable, heapTable);
        BoolValue secondValue = (BoolValue)right.evaluate(symbolsTable, heapTable);
        boolean firstBoolean = firstValue.getValue();
        boolean secondBoolean = secondValue.getValue();
        if (operator.equals("&&")){
            boolean result = firstBoolean && secondBoolean;
            return new BoolValue(result);
        }
        else if (operator.equals("||")){
            boolean result = firstBoolean || secondBoolean;
            return new BoolValue(result);
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
        if (type1.equals(new BoolType())){
            if (type2.equals(new BoolType())){
                return new BoolType();
            }
            else{
                throw new InvalidType("Second operand is not a boolean!");
            }
        }
        else{
            throw new InvalidType("First operand is not a boolean!");
        }
    }


    public String toString() {
        if (operator.equals("&&")){
            return "(" + left.toString() + "&&" + right.toString() + ")";
        }
        return "(" + left.toString() + "||" + right.toString() + ")";
    }
}
