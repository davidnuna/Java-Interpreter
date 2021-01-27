package model.expression;

import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.types.IType;
import model.values.IValue;

public class ValueExpression implements Expression{
    private final IValue value;

    public ValueExpression(IValue value){ this.value = value; }

    @Override
    public IValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception { return value; }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws Exception{ return value.getType(); }

    public String toString(){ return value.toString(); }
}
