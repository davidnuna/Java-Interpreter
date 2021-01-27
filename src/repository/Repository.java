package repository;

import model.statement.IStatement;
import model.structures.*;
import exceptions.FileError;
import model.values.IValue;
import model.values.StringValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private final String logFile;
    public Repository(ProgramState programState, String logFile) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        this.logFile = logFile;
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws Exception{
        IStack<IStatement> executionStack = programState.getExecutionStack();
        IDictionary<String, IValue> symbolsTable = programState.getSymbolsTable();
        IList<IValue> outputStream = programState.getOutputStream();
        IDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();
        IHeap<Integer, IValue> heapTable = programState.getHeapTable();
        int ID = programState.getStateID();
        PrintWriter printer;

        try{
            printer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
        }
        catch (IOException e){
            throw new FileError(e.getMessage());
        }

        printer.println("ID:\n" + ID + "\nExecution stack:\n" + executionStack.toString() + "\nSymbols table:\n" + symbolsTable.toString() + "\nOutput stream:\n" + outputStream.toString() + "\nFile table:\n" + fileTable.toString() + "\nHeap table:\n" + heapTable.toString() + "\n");

        printer.close();
    }

    @Override
    public List<ProgramState> getProgramsList(){
        return programStates;
    }

    @Override
    public void setProgramsList(List<ProgramState>newProgramStates){
        programStates = newProgramStates;
    }
}
