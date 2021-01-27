package model.expression;

import exceptions.InvalidType;
import exceptions.UndeclaredVariable;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.types.IType;
import model.values.RefValue;
import model.values.IValue;
import model.types.RefType;

public class HeapReadingExpression implements Expression {
    private final Expression expression;

    public HeapReadingExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception {
        if (expression.evaluate(symbolsTable, heapTable) instanceof RefValue){
            RefValue valueRef = (RefValue) expression.evaluate(symbolsTable, heapTable);
            int address = valueRef.getAddress();

            if (heapTable.containsKey(address)){
                return heapTable.get(address);
            }
            else {
                throw new UndeclaredVariable("The address of the refValue does not exist in the heap table!");
            }
        }
        else{
            throw new InvalidType("The expression does not evaluate to a RefType!");
        }
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType type = expression.typeCheck(typeEnv);
        if (type instanceof RefType){
            RefType refType = (RefType)type;
            return refType.getInner();
        }
        else{
            throw new InvalidType("The argument is not a RefType!");
        }
    }


    public String toString(){
        return "rH(" + expression.toString() + ")";
    }
}
