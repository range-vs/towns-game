package pages;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StartController {

    @FXML
    private WebView webView;

    public void initialize(){
        WebEngine webEngine = webView.getEngine();
        URL url = getClass().getResource("../res/pages/start.html");
        webEngine.load(url.toExternalForm());
    }

    private void initMainPage(String title, String nameFolder, int finish, boolean isFinish) throws IOException {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../pages/main.fxml"));
        root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        Scene scene = new Scene(root, 1350, 800);
        stage.setScene(scene);
        scene.getStylesheets().add("res/style/main.css");
        MainController controller = fxmlLoader.getController();
        controller.setStartProperties(nameFolder, finish, isFinish);
        controller.setStage(stage);
        controller.init();
        stage.show();
    }

    public void startHistory(ActionEvent actionEvent) {
        try {
            initMainPage("История", "history", 3, false);
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    public void startEducation(ActionEvent actionEvent) {
        try {
            initMainPage("Обучение", "education", 5, true);
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
