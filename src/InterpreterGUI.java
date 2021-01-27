import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import java.util.stream.Collectors;

public class InterpreterGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/ProgramPicker.fxml"));
        primaryStage.setTitle("Interpreter");
        primaryStage.setScene(new Scene(root, 1050, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
