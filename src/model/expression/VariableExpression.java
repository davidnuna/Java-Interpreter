package model.expression;

import exceptions.InvalidType;
import exceptions.UndeclaredVariable;
import model.structures.HeapTable;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.types.IType;
import model.values.IValue;

public class VariableExpression implements Expression{
    private final String identifier;

    public VariableExpression(String identifier) { this.identifier = identifier; }

    @Override
    public IValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception {
        if (symbolsTable.containsKey(identifier)){
            return symbolsTable.get(identifier);
        }
        throw new UndeclaredVariable("The variable " + identifier + " has not been declared!");
    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType type = typeEnv.get(identifier);
        if (type != null){
            return type;
        }
        throw new UndeclaredVariable("The variable " + identifier + " has not been declared!");
    }


    public String toString() { return identifier; }
}
