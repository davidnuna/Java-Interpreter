package model.structures;

import exceptions.ExecutionStackEmpty;
import model.statement.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private final IStack<IStatement> executionStack;
    private final IList<IValue> outputStream;
    private final IDictionary<String, IValue> symbolsTable;
    private final IDictionary<StringValue, BufferedReader> fileTable;
    private final IHeap<Integer, IValue> heapTable;
    private final int stateID;
    public static int ID = -1;

    public ProgramState(IStatement program){
        executionStack = new ExecutionStack(program);
        outputStream = new OutputStream();
        symbolsTable = new SymbolsTable();
        fileTable = new FileTable();
        heapTable = new HeapTable();
        stateID = getID();
    }

    public ProgramState(IStack<IStatement> executionStack, IList<IValue> outputStream, IDictionary<String, IValue> symbolsTable, IDictionary<StringValue, BufferedReader> fileTable, IHeap<Integer, IValue> heapTable){
        this.executionStack = executionStack;
        this.outputStream = outputStream;
        this.symbolsTable = symbolsTable;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        stateID = getID();
    }

    public Boolean isNotComplete(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws Exception{
        if (executionStack.isEmpty()){
            throw new ExecutionStackEmpty("The execution stack is empty!");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public static synchronized int getID(){
        ID++;
        return ID;
    }

    public int getStateID(){
        return stateID;
    }

    public IStack<IStatement> getExecutionStack(){
        return executionStack;
    }

    public IDictionary<String, IValue> getSymbolsTable(){
        return symbolsTable;
    }

    public IList<IValue> getOutputStream(){
        return outputStream;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }

    public IHeap<Integer, IValue> getHeapTable(){
        return heapTable;
    }
}