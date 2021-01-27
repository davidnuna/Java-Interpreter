package view;

import model.structures.ProgramState;

import java.util.List;

public abstract class Command {
    private final String key, description;
    public Command(String key, String description){
        this.key = key;
        this.description = description;
    }

    public abstract void execute();
    public abstract List<ProgramState> getProgramsList();
    public String getKey() { return key; }
    public String getDescription() { return description; }
    public String toString() { return description; }
}
