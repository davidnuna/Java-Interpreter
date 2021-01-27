package controller;

import exceptions.ExecutionStackEmpty;
import model.structures.HeapTable;
import model.structures.SymbolsTable;
import model.values.IValue;
import model.values.RefValue;
import repository.IRepository;
import model.structures.ProgramState;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;
    ExecutorService executor;

    public Controller(IRepository repository){ this.repository = repository; executor = Executors.newFixedThreadPool(2); }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programsList){
        return programsList.stream()
                .filter(ProgramState::isNotComplete)
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgramState>programsList) throws Exception{
        List<Callable<ProgramState>> callList = programsList.stream()
                .map((ProgramState program)->(Callable<ProgramState>)(program::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future->{try { return future.get(); }
                              catch(Exception e){ System.out.println(e.getMessage()); return null; }} )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        programsList.addAll(newProgramsList);
        programsList.forEach(program->{try { repository.logProgramStateExecution(program); } catch(Exception e){ System.out.println(e.getMessage()); }});
        repository.setProgramsList(programsList);
    }

    public void allStep() throws Exception{
        List<ProgramState> programsList = removeCompletedPrograms(repository.getProgramsList());
        if(programsList.size() > 0){
            HeapTable heapTable = (HeapTable)programsList.get(0).getHeapTable();
            Set<Integer> allAddressesFromSymbolTables = new HashSet<>();
            for (ProgramState program: programsList){
                allAddressesFromSymbolTables.addAll(getAddressesFromSymbolsTable(program));
            }
            heapTable.setContent(safeGarbageCollector(allAddressesFromSymbolTables, heapTable.getContent()));
            oneStepForAllPrg(programsList);
        }
        else{
            executor.shutdown();
        }
        repository.setProgramsList(programsList);
    }

    Map<Integer, IValue> safeGarbageCollector(Set<Integer> symbolTableAddresses, Map<Integer, IValue> heap){
        return heap.entrySet().stream()
                .filter(e->symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Set<Integer> getAddressesFromSymbolsTable(ProgramState programState){
        SymbolsTable symbolsTable = (SymbolsTable) programState.getSymbolsTable();
        Collection<IValue> symbolTableValues = symbolsTable.getContent().values();
        return symbolTableValues.stream()
                .filter(v->v instanceof RefValue)
                .flatMap(v->{
                    ArrayList<Integer> addresses = new ArrayList<>();
                    RefValue v1 = (RefValue)v;
                    while (true){
                        addresses.add(v1.getAddress());
                        HeapTable heapTable = (HeapTable)programState.getHeapTable();
                        IValue iValue;
                        try {
                            iValue = heapTable.get(v1.getAddress());
                            if (iValue instanceof RefValue){
                                v1 = (RefValue) iValue;
                            }
                            else{
                                break;
                            }
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    return addresses.stream(); })
                .collect(Collectors.toSet());
    }

    public List<ProgramState> getProgramsList(){
        return repository.getProgramsList();
    }
}
