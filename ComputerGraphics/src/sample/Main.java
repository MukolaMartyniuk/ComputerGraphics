package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("S_Menu.fxml"));
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
