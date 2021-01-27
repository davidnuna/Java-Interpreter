package model.statement;

import model.structures.IDictionary;
import model.structures.IStack;
import model.structures.ProgramState;
import model.types.IType;

public class CompoundStatement implements IStatement{
    private final IStatement first;
    private final IStatement second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    public String toString(){
        return "(" + first.toString() + ";" + second.toString() + ")";
    }
}
