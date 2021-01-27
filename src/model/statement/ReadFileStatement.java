package model.statement;

import exceptions.*;
import model.expression.Expression;
import model.structures.*;
import model.values.*;
import model.types.*;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement{
    private final Expression expression;
    private final String variableName;

    public ReadFileStatement(Expression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        HeapTable heapTable = (HeapTable)state.getHeapTable();
        FileTable fileTable = (FileTable)state.getFileTable();
        SymbolsTable symbolTable = (SymbolsTable)state.getSymbolsTable();
        if (symbolTable.containsKey(variableName)){
            if ((symbolTable.get(variableName)).getType().equals(new IntType())){
                IValue value = expression.evaluate(symbolTable, heapTable);
                if (value.getType().equals(new StringType())){
                    StringValue stringValue = (StringValue)value;
                    if (fileTable.containsKey(stringValue)){
                        BufferedReader bufferedReader = fileTable.get(stringValue);
                        try{
                            IValue iValue;
                            String line = bufferedReader.readLine();
                            if (line == null){
                                iValue = (new IntType()).defaultValue();
                            }
                            else{
                                iValue = new IntValue(Integer.parseInt(line));
                            }
                            symbolTable.put(variableName, iValue);
                        }
                        catch (Exception e){
                            throw new FileError(e.getMessage());
                        }
                    }
                    else{
                        throw new UndeclaredVariable("The file has not been opened yet!");
                    }
                }
                else{
                    throw new NonStringType("The expression does not evaluate to a string value!");
                }
            }
            else{
                throw new NonIntegerType("The variable " + variableName + " is not an integer!");
            }
        }
        else{
            throw new UndeclaredVariable("The variable " + variableName + " has not been declared!");
        }

        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType typeIdentifier = typeEnv.get(variableName);
        if (typeIdentifier.equals(new IntType())){
            expression.typeCheck(typeEnv);
            return typeEnv;
        }
        else{
            throw new InvalidType("The type of the variable is not int!");
        }
    }

    public String toString() { return "read file " + expression.toString() + " into " + variableName; }
}
