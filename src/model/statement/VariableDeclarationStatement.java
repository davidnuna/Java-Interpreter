package model.statement;

import exceptions.VariableAlreadyDeclared;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.structures.ProgramState;
import model.types.IType;
import model.types.IntType;
import model.types.BoolType;
import model.types.StringType;
import model.values.IValue;

public class VariableDeclarationStatement implements IStatement{
    private final String identifier;
    private final IType type;

    public VariableDeclarationStatement(String identifier, IType type){
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        SymbolsTable symbolsTable = (SymbolsTable)state.getSymbolsTable();

        if (!symbolsTable.containsKey(identifier)){
            if (type.equals(new IntType())){
                symbolsTable.put(identifier, type.defaultValue());
            }
            else if (type.equals(new BoolType())){
                symbolsTable.put(identifier, type.defaultValue());
            }
            else if (type.equals(new StringType())){
                symbolsTable.put(identifier, type.defaultValue());
            }
            else{
                symbolsTable.put(identifier, type.defaultValue());
            }
        }
        else{
            throw new VariableAlreadyDeclared("Variable " + identifier + " has already been declared!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        typeEnv.put(identifier, type);
        return typeEnv;
    }

    public String toString(){ return type.toString() + " " + identifier; }
}
