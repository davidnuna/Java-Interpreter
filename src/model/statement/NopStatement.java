package model.statement;

import exceptions.InvalidType;
import model.structures.IDictionary;
import model.structures.ProgramState;
import model.types.IType;
import model.types.RefType;

@SuppressWarnings("unused")
public class NopStatement implements IStatement{
    public NopStatement(){}

    @Override
    public ProgramState execute(ProgramState state) { return null; }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        return typeEnv;
    }

    public String toString() { return "nop"; }
}
