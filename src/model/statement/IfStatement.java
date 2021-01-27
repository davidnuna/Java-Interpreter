package model.statement;

import exceptions.InvalidType;
import model.expression.Expression;
import model.structures.*;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;
import model.types.BoolType;

public class IfStatement implements IStatement{
    private final Expression expression;
    private final IStatement thenContent;
    private final IStatement elseContent;

    public IfStatement(Expression expression, IStatement thenWord, IStatement elseWord) {
        this.expression = expression;
        this.thenContent = thenWord;
        this.elseContent = elseWord;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        IStack<IStatement> stack = state.getExecutionStack();
        SymbolsTable symbolsTable = (SymbolsTable)state.getSymbolsTable();
        HeapTable heapTable = (HeapTable)state.getHeapTable();
        BoolValue valueBool = (BoolValue)expression.evaluate(symbolsTable, heapTable);
        if (valueBool.getValue()){
            stack.push(thenContent);
        }
        else{
            stack.push(elseContent);
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeExpression.equals(new BoolType())){
            thenContent.typeCheck(typeEnv.deepCopy());
            elseContent.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else{
            throw new InvalidType("The type of the condition is not boolean!");
        }
    }

    public String toString(){
        return "if (" + expression.toString() + ") then (" + thenContent.toString() + ") else (" + elseContent.toString() + ")";
    }
}
