package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.statement.*;
import model.structures.ProgramState;
import model.structures.TableRow;
import model.values.IValue;

import javafx.scene.input.MouseEvent;
import model.values.StringValue;

import java.io.BufferedReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerProgramExecution implements Initializable {
    private Command command;
    private int programIndex;
    @FXML private Label nrProgramStates;

    @FXML private ListView<IValue> outList;
    @FXML private ListView<Integer> programsIDSList;
    @FXML private ListView<IStatement> executionStackList;

    @FXML private TableView<TableRow> symbolsTable;
    @FXML private TableColumn<TableRow, String> symbolsTableVariable;
    @FXML private TableColumn<TableRow, String> symbolsTableValue;

    @FXML private TableView<TableRow> heapTable;
    @FXML private TableColumn<TableRow, String> heapTableAddress;
    @FXML private TableColumn<TableRow, String> heapTableValue;

    @FXML private TableView<TableRow> fileTable;
    @FXML private TableColumn<TableRow, String> fileTableId;
    @FXML private TableColumn<TableRow, String> fileTableName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        symbolsTableVariable.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFirst().toString()));
        symbolsTableValue.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSecond().toString()));

        heapTableAddress.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFirst().toString()));
        heapTableValue.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSecond().toString()));

        fileTableName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFirst().toString()));
        fileTableId.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSecond().toString()));
    }

    public void populate(){
        List<ProgramState> programStates = command.getProgramsList();
        if (programStates.size() <= programIndex){
            programIndex = 0;
            programsIDSList.getSelectionModel().select(programIndex);
        }
        nrProgramStates.setText("Number of programs: " + programStates.size());
        if (programStates.size() > 0){
            ObservableList<IValue> output = FXCollections.observableArrayList(programStates.get(0).getOutputStream().asList());
            outList.setItems(output);
            ObservableList<Integer> ids = FXCollections.observableArrayList(programStates.stream().map(ProgramState::getStateID).collect(Collectors.toList()));
            programsIDSList.setItems(ids);
            ObservableList<TableRow<StringValue, BufferedReader>> fileTbl = FXCollections.observableArrayList(programStates.get(0).getFileTable().keySet().stream()
                    .map(iValue -> new TableRow<>(iValue, programStates.get(0).getFileTable().get(iValue))).collect(Collectors.toList()));
            fileTable.getItems().setAll(fileTbl);
            ObservableList<TableRow<Integer, IValue>> heapTbl = FXCollections.observableArrayList(programStates.get(0).getHeapTable().keySet().stream()
                    .map(address -> new TableRow<>(address, programStates.get(0).getHeapTable().get(address))).collect(Collectors.toList()));
            heapTable.getItems().setAll(heapTbl);
            ObservableList<IStatement> executionStack = FXCollections.observableArrayList(programStates.get(programIndex).getExecutionStack().asList());
            executionStackList.setItems(executionStack);
            ObservableList<TableRow<String, IValue>> symbolsTbl = FXCollections.observableArrayList(programStates.get(programIndex).getSymbolsTable().keySet().stream()
                    .map(variable -> new TableRow<>(variable, programStates.get(programIndex).getSymbolsTable().get(variable))).collect(Collectors.toList()));
            symbolsTable.getItems().setAll(symbolsTbl);
        }
        else{
            programsIDSList.getItems().clear();
            executionStackList.getItems().clear();
        }
    }

    public void oneStep(){
        try{
            command.execute();
            populate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setProgram(Command command){
        this.command = command;
        programIndex = 0;
        populate();
        programsIDSList.getSelectionModel().select(programIndex);
    }

    public void selectIndex(MouseEvent mouseEvent){
        if(mouseEvent.getClickCount() == 1 && programsIDSList.getSelectionModel().getSelectedItem() != null){
            programIndex = programsIDSList.getSelectionModel().getSelectedIndex();
            populate();
        }
    }

}
