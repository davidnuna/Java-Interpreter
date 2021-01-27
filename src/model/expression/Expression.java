package model.expression;

import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.values.IValue;
import model.types.IType;
import model.structures.IDictionary;

public interface Expression{
    IValue evaluate(SymbolsTable symbolsTable, HeapTable heapTable) throws Exception;
    IType typeCheck(IDictionary<String, IType> typeEnv) throws Exception;
}
