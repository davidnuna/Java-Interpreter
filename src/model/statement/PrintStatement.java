package model.statement;

import model.expression.Expression;
import model.structures.IDictionary;
import model.structures.ProgramState;
import model.structures.SymbolsTable;
import model.structures.HeapTable;
import model.types.IType;
import model.values.IValue;

public class PrintStatement implements IStatement{
    private final Expression expression;

    public PrintStatement(Expression expression) { this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        IValue value = expression.evaluate((SymbolsTable)state.getSymbolsTable(), (HeapTable)state.getHeapTable());
        state.getOutputStream().add(value);
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws Exception{
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
