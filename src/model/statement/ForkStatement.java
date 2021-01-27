package model.statement;

import model.structures.*;
import model.types.IType;
import model.values.*;

import java.util.HashMap;

public class ForkStatement implements IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement){ this.statement = statement; }

    @Override
    public ProgramState execute(ProgramState state) {
        SymbolsTable symbolTable = (SymbolsTable)state.getSymbolsTable();
        OutputStream outputStream = (OutputStream)state.getOutputStream();
        HeapTable heapTable = (HeapTable)state.getHeapTable();
        FileTable fileTable = (FileTable)state.getFileTable();

        ExecutionStack newStack = new ExecutionStack(statement);
        SymbolsTable newSymbolsTable = new SymbolsTable();
        for (HashMap.Entry<String, IValue> element: (symbolTable.getContent()).entrySet()) {
            newSymbolsTable.put((element.getKey()), element.getValue().deepCopy());
        }
        return new ProgramState(newStack, outputStream, newSymbolsTable, fileTable, heapTable);
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        return statement.typeCheck(typeEnv);
    }

    public String toString() { return "fork(" + statement.toString() + ")"; }
}
