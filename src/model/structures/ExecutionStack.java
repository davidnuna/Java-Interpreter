package model.structures;

import model.statement.IStatement;

import java.util.ArrayList;

public class ExecutionStack implements IStack<IStatement> {
    private final ArrayList<IStatement> elements;

    public ExecutionStack(IStatement program){
        elements = new ArrayList<>();
        this.push(program);
    }

    @Override
    public void push(IStatement element){ elements.add(element); }

    @Override
    public IStatement pop(){
        int index = elements.size() - 1;
        IStatement lastElement = elements.get(index);
        elements.remove(index);
        return lastElement;
    }

    @Override
    public boolean isEmpty() {
        return elements.size() == 0;
    }

    @Override
    public ArrayList<IStatement> asList(){
        return elements;
    }

    @Override
    public String toString(){
        StringBuilder stackString = new StringBuilder();
        for (IStatement element: elements){
            stackString.append(element.toString()).append("\n");
        }
        return stackString.toString();
    }
}
