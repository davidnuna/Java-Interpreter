package model.statement;

import exceptions.InvalidType;
import model.expression.Expression;
import model.structures.*;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class WhileStatement implements IStatement{
    private final Expression expression;
    private final IStatement whileBlock;

    public WhileStatement(Expression expression, IStatement whileBlock) {
        this.expression = expression;
        this.whileBlock = whileBlock;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        IStack<IStatement> stack = state.getExecutionStack();
        SymbolsTable symbolsTable = (SymbolsTable)state.getSymbolsTable();
        HeapTable heapTable = (HeapTable)state.getHeapTable();
        if (expression.evaluate(symbolsTable, heapTable) instanceof BoolValue){
            BoolValue valueBool = (BoolValue)expression.evaluate(symbolsTable, heapTable);
            if (valueBool.getValue()){
                stack.push(this);
                stack.push(whileBlock);
            }
        }
        else{
            throw new InvalidType("The expression does not evaluate to a BoolValue!");
        }
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        IType typeExpression = expression.typeCheck(typeEnv);
        if (typeExpression.equals(new BoolType())){
            whileBlock.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else{
            throw new InvalidType("The type of the condition is not boolean!");
        }
    }

    public String toString(){
        return "(while (" + expression + ") " + whileBlock + ")";
    }
}
