package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class MainController {

    @FXML
    private WebView webView;
    @FXML
    private Button btnRedo;

    private WebEngine webEngine;
    private String nameFolder;
    private int start;
    private int finish;
    private int current;
    private Stage curr;
    private boolean isFinish;

    public void init(){
        start = 1;
        current = 1;
        webEngine = webView.getEngine();
        URL url = getClass().getResource("../res/pages/" + nameFolder + "/" + current + ".html");
        webEngine.load(url.toExternalForm());
    }

    public void setStartProperties(String nameFolder, int finish, boolean isFinish){
        this.nameFolder = nameFolder;
        this.finish = finish;
        this.isFinish = isFinish;
    }

    public void back(ActionEvent actionEvent) {
        if(current == start){
            curr.close();
            return;
        } else{
            current--;
            URL url = getClass().getResource("../res/pages/" + nameFolder + "/" + current + ".html");
            webEngine.load(url.toExternalForm());
        }
        btnRedo.setDisable(false);
        btnRedo.setText("Далее");

    }

    public void redo(ActionEvent actionEvent) {
        if(btnRedo.getText().equals("Завершить")){
            curr.close();
            return;
        }
        else if(!isFinish && current == finish-1) {
            btnRedo.setDisable(true);
        } else if(isFinish && current == finish-1){
            btnRedo.setText("Завершить");
        }
        current++;
        URL url = getClass().getResource("../res/pages/" + nameFolder + "/" + current + ".html");
        webEngine.load(url.toExternalForm());

    }

    public void setStage(Stage st){
        curr = st;
    }

}
