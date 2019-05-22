package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../pages/start.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1350, 800);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(650);
        scene.getStylesheets().add("res/style/main.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
