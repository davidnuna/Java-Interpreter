package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.expression.*;
import model.statement.*;
import model.structures.IDictionary;
import model.structures.ProgramState;
import model.structures.TypeEnvironment;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerProgramPicker implements Initializable {
    @FXML
    private ListView<Command> programsList;
    private ArrayList<IStatement> statementsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        programsList.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent){
                try {
                    runCommand(mouseEvent);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        IStatement statement1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))));
        ProgramState program1 = new ProgramState(statement1);
        IRepository repository1 = new Repository(program1, "log1.txt");
        Controller controller1 = new Controller(repository1);

        IStatement statement2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",
                                new ValueExpression(new IntValue(2)), new ArithmeticExpression("*", new ValueExpression(new IntValue(3)),
                                new ValueExpression(new IntValue(5))))), new CompoundStatement(new AssignmentStatement("b",
                                new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new IntValue(1)))),
                                new PrintStatement(new VariableExpression("b"))))));
        ProgramState program2 = new ProgramState(statement2);
        IRepository repository2 = new Repository(program2, "log2.txt");
        Controller controller2 = new Controller(repository2);

        IStatement statement3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v",
                                new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                new PrintStatement(new VariableExpression("v"))))));
        ProgramState program3 = new ProgramState(statement3);
        IRepository repository3 = new Repository(program3, "log3.txt");
        Controller controller3 = new Controller(repository3);

        IStatement statement4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                                new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                new CloseRFileStatement(new VariableExpression("varf"))))))))));
        ProgramState program4 = new ProgramState(statement4);
        IRepository repository4 = new Repository(program4, "log4.txt");
        Controller controller4 = new Controller(repository4);

        IStatement statement5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(new VariableDeclarationStatement("t", new BoolType()),
                                new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(7))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(11))),
                                new CompoundStatement(new AssignmentStatement("t", new RelationalExpression("<=", new VariableExpression("a"), new VariableExpression("b"))),
                                new PrintStatement(new VariableExpression("t"))))))));
        ProgramState program5 = new ProgramState(statement5);
        IRepository repository5 = new Repository(program5, "log5.txt");
        Controller controller5 = new Controller(repository5);

        IStatement statement6 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new PrintStatement(new VariableExpression("a")))))));
        ProgramState program6 = new ProgramState(statement6);
        IRepository repository6 = new Repository(program6, "log6.txt");
        Controller controller6 = new Controller(repository6);
        IStatement statement7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        ProgramState program7 = new ProgramState(statement7);
        IRepository repository7 = new Repository(program7, "log7.txt");
        Controller controller7 = new Controller(repository7);

        IStatement statement8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                new PrintStatement(new ArithmeticExpression("+", new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        IDictionary<String, IType> typeEnv8 = new TypeEnvironment();
        ProgramState program8 = new ProgramState(statement8);
        IRepository repository8 = new Repository(program8, "log8.txt");
        Controller controller8 = new Controller(repository8);

        IStatement statement9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        ProgramState program9 = new ProgramState(statement9);
        IRepository repository9 = new Repository(program9, "log9.txt");
        Controller controller9 = new Controller(repository9);

        IStatement statement10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                 new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                                 new CompoundStatement(new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntValue(0))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                 new PrintStatement(new VariableExpression("v")))));
        ProgramState program10 = new ProgramState(statement10);
        IRepository repository10 = new Repository(program10, "log10.txt");
        Controller controller10 = new Controller(repository10);
        IStatement statement11 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                 new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())),
                                 new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                 new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                 new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                 new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                 new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                 new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                 new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                 new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
        ProgramState program11 = new ProgramState(statement11);
        IRepository repository11 = new Repository(program11, "log11.txt");
        Controller controller11 = new Controller(repository11);

        TextMenu menu = new TextMenu();
        menu.addCommand(new RunExample("1", statement1.toString(), controller1));
        menu.addCommand(new RunExample("2", statement2.toString(), controller2));
        menu.addCommand(new RunExample("3", statement3.toString(), controller3));
        menu.addCommand(new RunExample("4", statement4.toString(), controller4));
        menu.addCommand(new RunExample("5", statement5.toString(), controller5));
        menu.addCommand(new RunExample("6", statement6.toString(), controller6));
        menu.addCommand(new RunExample("7", statement7.toString(), controller7));
        menu.addCommand(new RunExample("8", statement8.toString(), controller8));
        menu.addCommand(new RunExample("9", statement9.toString(), controller9));
        menu.addCommand(new RunExample("10", statement10.toString(), controller10));
        menu.addCommand(new RunExample("11", statement11.toString(), controller11));

        statementsList = new ArrayList<>();
        statementsList.add(statement1);
        statementsList.add(statement2);
        statementsList.add(statement3);
        statementsList.add(statement4);
        statementsList.add(statement5);
        statementsList.add(statement6);
        statementsList.add(statement7);
        statementsList.add(statement8);
        statementsList.add(statement9);
        statementsList.add(statement10);
        statementsList.add(statement11);
        ObservableList<Command> programs = FXCollections.observableArrayList(menu.getCommands());
        programsList.setItems(programs);
    }

    public void runCommand(MouseEvent mouseEvent) throws Exception{
        if(mouseEvent.getClickCount() == 2) {
            Command command = programsList.getSelectionModel().getSelectedItem();
            if (command == null){
                return;
            }
            Stage stage = (Stage) programsList.getScene().getWindow();
            FXMLLoader root = new FXMLLoader(getClass().getResource("ProgramExecution.fxml"));
            stage.setScene(new Scene(root.load(), 1310, 600));
            ControllerProgramExecution newController = root.getController();
            newController.setProgram(command);
        }
    }
    public void checkType() throws Exception{
        int index = programsList.getSelectionModel().getSelectedIndex();
        IStatement selectedProgram;
        for (IStatement st: statementsList) {
            if (st.toString().equals(programsList.getItems().get(index).getDescription())) {
                selectedProgram = st;
                try {
                    IDictionary<String, IType> typeEnv = new TypeEnvironment();
                    selectedProgram.typeCheck(typeEnv);
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getMessage());
                    alert.show();
                }
                return;
            }
        }
    }
}
