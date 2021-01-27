package model.statement;

import exceptions.FileError;
import exceptions.NonStringType;
import exceptions.VariableAlreadyDeclared;
import model.expression.Expression;
import model.structures.*;
import model.values.*;
import model.types.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class OpenRFileStatement implements IStatement{
    private final Expression expression;

    public OpenRFileStatement(Expression expression){ this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        HeapTable heapTable = (HeapTable)state.getHeapTable();
        SymbolsTable symbolTable = (SymbolsTable)state.getSymbolsTable();
        FileTable fileTable = (FileTable)state.getFileTable();
        if ((expression.evaluate(symbolTable, heapTable)).getType().equals(new StringType())){
            StringValue stringValue = (StringValue)expression.evaluate(symbolTable, heapTable);
            if (!fileTable.containsKey(stringValue)){
                try{
                    Reader reader = new FileReader(stringValue.getValue());
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    fileTable.put(stringValue, bufferedReader);
                }
                catch (Exception e){
                    throw new FileError(e.getMessage());
                }
            }
            else{
                throw new VariableAlreadyDeclared("The file has already been opened!");
            }
        }
        else{
            throw new NonStringType("The result of the expression is not a string!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    public String toString() { return "open(" + expression.toString() + ")"; }
}
