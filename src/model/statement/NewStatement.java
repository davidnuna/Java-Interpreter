package model.statement;

import exceptions.InvalidType;
import exceptions.UndeclaredVariable;
import model.expression.Expression;
import model.structures.IDictionary;
import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.types.IType;
import model.types.RefType;
import model.values.RefValue;
import model.values.IValue;
import model.structures.ProgramState;

public class NewStatement implements IStatement{
    private final String identifier;
    private final Expression expression;

    public NewStatement(String variable, Expression expression) {
        this.identifier = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        SymbolsTable symbolsTable = (SymbolsTable)state.getSymbolsTable();
        HeapTable heapTable = (HeapTable)state.getHeapTable();

        if (symbolsTable.containsKey(identifier)){
            IType type = (symbolsTable.get(identifier)).getType();
            if (type instanceof RefType){
                IValue value = expression.evaluate(symbolsTable, heapTable);
                if (type.equals(new RefType(value.getType()))){
                    int address = heapTable.alloc(value);
                    symbolsTable.put(identifier, new RefValue(address, value.getType()));
                }
                else{
                    throw new InvalidType("The declared type of the variable " + identifier + " and the type of the assigned expression do not match!");
                }
            }
            else{
                throw new InvalidType("The declared type of the variable " + identifier + " is not refType!");
            }
        }
        else{
            throw new UndeclaredVariable("The variable " + identifier + " was not declared!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType typeIdentifier = typeEnv.get(identifier);
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeIdentifier.equals(new RefType(typeExpression))){
            return typeEnv;
        }
        else{
            throw new InvalidType("The variable and expression type do not match!");
        }
    }

    public String toString(){
        return "new (" + identifier + "," + expression.toString() + ")";
    }
}
