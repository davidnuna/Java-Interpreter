package model.statement;

import exceptions.UndeclaredVariable;
import exceptions.InvalidType;
import model.expression.Expression;
import model.structures.HeapTable;
import model.structures.IDictionary;
import model.structures.ProgramState;
import model.structures.SymbolsTable;
import model.values.IValue;
import model.types.IType;

public class AssignmentStatement implements IStatement{
    private final String identifier;
    private final Expression expression;

    public AssignmentStatement(String identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        SymbolsTable symbolsTable = (SymbolsTable)state.getSymbolsTable();
        HeapTable heapTable = (HeapTable)state.getHeapTable();

        if (symbolsTable.containsKey(identifier)){
            IValue value = expression.evaluate(symbolsTable, heapTable);
            IType type = (symbolsTable.get(identifier)).getType();
            if (value.getType().equals(type)){
                symbolsTable.put(identifier, value);
            }
            else{
                throw new InvalidType("The declared type of the variable " + identifier + " and the type of the assigned expression do not match!");
            }
        }
        else{
            throw new UndeclaredVariable("The variable " + identifier + " was not declared!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType typeVariable = typeEnv.get(identifier);
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeVariable.equals(typeExpression)){
            return typeEnv;
        }
        else{
            throw new InvalidType("The variable and expression type do not match!");
        }
    }

    public String toString(){ return identifier + "=" + expression.toString(); }
}
