package view;

import model.structures.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class ExitCommand extends Command{
    public ExitCommand(String key, String description){ super(key, description); }

    @Override
    public void execute(){
        System.exit(0);
    }

    @Override
    public List<ProgramState> getProgramsList(){ return null; }
}
