package repository;

import model.structures.ProgramState;

import java.util.List;

public interface IRepository {
    void logProgramStateExecution(ProgramState programState) throws Exception;
    List<ProgramState> getProgramsList();
    void setProgramsList(List<ProgramState>newProgramStates);
}
