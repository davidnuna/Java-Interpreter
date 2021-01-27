package model.statement;

import exceptions.*;
import model.expression.Expression;
import model.structures.*;
import model.values.*;
import model.types.*;

import java.io.BufferedReader;

public class CloseRFileStatement implements IStatement{
    private final Expression expression;

    public CloseRFileStatement(Expression expression){ this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        SymbolsTable symbolTable = (SymbolsTable)state.getSymbolsTable();
        HeapTable heapTable = (HeapTable)state.getHeapTable();
        FileTable fileTable = (FileTable)state.getFileTable();
        IValue value = expression.evaluate(symbolTable, heapTable);
        if (value.getType().equals(new StringType())){
            StringValue stringValue = (StringValue)value;
            if (fileTable.containsKey(stringValue)){
                BufferedReader bufferedReader = fileTable.get(stringValue);
                try{
                    bufferedReader.close();
                }
                catch (Exception e){
                    throw new FileError(e.getMessage());
                }
                fileTable.remove(stringValue);
            }
            else{
                throw new UndeclaredVariable("The file has not been opened yet!");
            }
        }
        else{
            throw new NonStringType("The expression does not evaluate to a string value!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    public String toString() { return "close(" + expression.toString() + ")"; }
}
