package model.statement;

import model.structures.IDictionary;
import model.structures.ProgramState;
import model.types.IType;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Exception;
    IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception;
}
