package view;

import controller.Controller;
import exceptions.ExecutionStackEmpty;
import model.structures.ProgramState;

import java.util.List;

public class RunExample extends Command{
    private final Controller controller;
    public RunExample(String key, String description, Controller controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute(){
        try{
           controller.allStep();
        }
        catch (ExecutionStackEmpty e){
            System.out.println("All the programs have finished their execution!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<ProgramState> getProgramsList(){
        return controller.getProgramsList();
    }
}
