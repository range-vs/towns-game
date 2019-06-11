package pages;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;

public class TestController {

    @FXML
    private BorderPane rootPane;
    @FXML
    private Label questionLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private Label timerLabelText;
    @FXML
    private RadioButton answ1;
    @FXML
    private RadioButton answ2;
    @FXML
    private RadioButton answ3;
    @FXML
    private RadioButton answ4;
    @FXML
    private ToggleGroup _answers;
    @FXML
    private Label currentCountLabel;
    @FXML
    private Label correctCountLabel;
    @FXML
    private Label answerLabel;

    private Stage root;
    private HashMap<Integer, String> questions;
    private HashMap<Integer, HashMap<String, Boolean>> answers;
    private ArrayList<Integer> index;
    private Integer currentQuestion;
    private Integer correct;
    private Integer all;
    private RadioButton btns[];
    private ArrayList<Integer> timerIndex;
    private Timer timer;
    private TimerTask tick;
    private int time;


    @FXML
    public void initialize(){
        initStyles();
        initTests();
        updateQuestion();
        updateState();
    }

    private void initStyles(){
        String image = this.getClass().getResource("../res/img/bg.jpg").toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + image + "')");
    }

    private void initTests(){
        questions = new HashMap<Integer, String>(){{
            put(0, "В историческом романе «Князь Серебряный» А. Н. Толстой пишет, \nчто в городки с удовольствием играли русские бояре времен ... .\n" +
                    "Каких конкретно времён?"); //  Ивана Грозного.
            put(1, "Какое альтернативное название имеет игра \"Городки\"?"); //  Рюхи
            put(2, "Где распространилась игра по свидетельству Л. Н. Толстого и А. А. Игнатьев (1877–1954 гг.)?"); // армия, среди офицеров
            put(3, "Откуда произошло название \"Городки\"?"); // происходит от одного селения, называемого Городок.
            put(4, "Какой \"вождь мирового пролетариата\" был заядлым игроков в \"Городки\"?"); // В.И.Ленин
            put(5, "Какой день считается Днём Рождения городишного спорта?"); // 20 августа 1923 г. были утверждены «Единые правила игры в городки», составленные методистом физкультуры С. В. Сысоевым
            put(6, "Самое раннее упоминание игры \"Городки\"?"); // начало XIX века
            put(7, "Какой обычай игры исчез в конце XIX - начале XX веков?"); // катались победители игры на спинах побежденных
            put(8, "Каковы примерные размеры для бит?"); // длиной 80 см, диаметром 4-5 см
            put(9, "Что такое полукон?"); // нарисуйте городки. Отступив от городов на примерно 3 метра, нарисуйте черту, с которой игроки будут метать биту
            put(10, "Сколько команд участвует в игре?"); // две
            put(11, "Какая фигура сущестует в игре?"); // колодец
            put(12, "Что такое кон?"); // три метра от полукона
            put(13, "Какие последствия, если игрок во время броска заступил за линию?"); // бросок не засчитывается
            put(14, "Что будет, если бита застревает на поле?"); // её должен выбить другой игрок другой битой
            put(15, "Что такое пригород?"); // в усложненном варианте игры существует пригород, откуда также следует выбивать закатившиеся фигуры
        }};
        answers = new HashMap<Integer, HashMap<String, Boolean>>(){{
            put(0, new HashMap<String, Boolean>(){{
                put("Во времена князя Владимира", false);
                put("Во времена В. И. Ленина", false);
                put("Во времена Ивана Грозного", true);
                put("Во времена Петра Первого", false);
            }});
            put(1, new HashMap<String, Boolean>(){{
                put("Махи", false);
                put("Рюхи", true);
                put("Ряхи", false);
                put("Бахи", false);
            }});
            put(2, new HashMap<String, Boolean>(){{
                put("Армия", true);
                put("Россия", false);
                put("СССР", false);
                put("Госпитали", false);
            }});
            put(3, new HashMap<String, Boolean>(){{
                put("Было придумано", false);
                put("Указом правительства", false);
                put("История умалчивает", false);
                put("От селения с названием \"Городок\"", true);
            }});
            put(4, new HashMap<String, Boolean>(){{
                put("В.И.Ленин", true);
                put("И.В.Сталин", false);
                put("В.В.Путин", false);
                put("М.С.Горбачёв", false);
            }});
            put(5, new HashMap<String, Boolean>(){{
                put("20 апреля 1903 г.", false);
                put("13 июля 1823 г.", false);
                put("20 августа 1923 г.", true);
                put("21 августа 1943 г.", false);
            }});
            put(6, new HashMap<String, Boolean>(){{
                put("конец XX века", false);
                put("начало XIX века", true);
                put("начало XX века", false);
                put("конец XIX века", false);
            }});
            put(7, new HashMap<String, Boolean>(){{
                put("производился обмен душами населения", false);
                put("игра была на ставки, ставка проигравшего сгорала", false);
                put("проигравший публично хвалил победившего", false);
                put("победители игры катались на спинах побежденных", true);
            }});
            put(8, new HashMap<String, Boolean>(){{
                put("длина 100 см, диаметр 4-5 см", false);
                put("длина 80 см, диаметр 4-5 см", true);
                put("длина 80 см, диаметр 10 - 15 см", false);
                put("длина 60 см, диаметр 5 см", false);
            }});
            put(9, new HashMap<String, Boolean>(){{
                put("Название фигуры", false);
                put("Черта, примерно 3 метра от городов, с которой игроки будут метать биту", true);
                put("Половина игры", false);
                put("Усложнённые правила игры", false);
            }});
            put(10, new HashMap<String, Boolean>(){{
                put("Две", true);
                put("Без ограничений", false);
                put("Три", false);
                put("Одна", false);
            }});
            put(11, new HashMap<String, Boolean>(){{
                put("Краб", false);
                put("Сапог", false);
                put("Колодец", true);
                put("Короб", false);
            }});
            put(12, new HashMap<String, Boolean>(){{
                put("Черта, три метра от полукона", true);
                put("Полный цикл игры", false);
                put("Черта, шесть метров от полукона", false);
                put("Название фигуры", false);
            }});
            put(13, new HashMap<String, Boolean>(){{
                put("дисквалификация команды", false);
                put("бросок не засчитывается", true);
                put("вычитание очков у команды", false);
                put("без последствий", false);
            }});
            put(14, new HashMap<String, Boolean>(){{
                put("без последствий", false);
                put("просто убрать с поля", false);
                put("бита больше не может участвовать в игре", false);
                put("другой игрок должен её выбить другой битой", true);
            }});
            put(15, new HashMap<String, Boolean>(){{
                put("Название одной из фигур", false);
                put("В усложненном варианте игры эта часть территории тоже участвует в игре", true);
                put("Название дополнительной черты для бросков", false);
                put("В игре нет такого термина", false);
            }});
        }};
        ArrayList<Integer> indexsSort = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
        index = new ArrayList<>();
        while(!indexsSort.isEmpty()){
            int ind = (int) (Math.random() * indexsSort.size()) + 0;
            index.add(indexsSort.remove(ind));
        }
        currentQuestion = 0;
        correct = 0;
        all = questions.size();
        btns = new RadioButton[]{answ1, answ2, answ3, answ4};
        timerIndex = new ArrayList<>();
        while(timerIndex.size() < 4){
            int ind = (int) (Math.random() * index.size()) + 0;
            ind = index.get(ind);
            if(!timerIndex.contains(ind)){
                timerIndex.add(ind);
            }
        }
        System.out.println(timerIndex);
    }

    private void initTimer(){
        timer = new Timer();
        tick = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> timerLabel.setText("00:00:" +
                        (time == 10 ? time : "0" + time)));
                time--;
                if(time == 0){
                    timer.cancel();
                    timer = null;
                    Platform.runLater(() -> {
                        getAnswer();
                        if (inspEnd()) {
                            return;
                        }
                        updateQuestion();
                        updateState();
                    });
                }
            }
        };
        timer.schedule(tick, 0, 1000);
    }

    public void updateQuestion(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        answ1.fire();
        int ind = index.get(currentQuestion++);
        questionLabel.setText(questions.get(ind));
        int i = 0;
        for(Map.Entry<String, Boolean> e: answers.get(ind).entrySet()){
            btns[i++].setText(e.getKey());
        }
        if(timerIndex.contains(ind)){
            timerLabelText.setText("Время для ответа:");
            time = 10;
            initTimer();
        }else{
            timerLabelText.setText("");
            timerLabel.setText("");
        }
    }

    public void getAnswer(){
        RadioButton rbtn = (RadioButton)_answers.getSelectedToggle();
        int ind = index.get(currentQuestion-1);
        boolean flag = answers.get(ind).get(rbtn.getText());
        if(flag){
            answerLabel.setText("Ответ верный");
            answerLabel.setStyle("-fx-text-fill: green");
            correct++;
        } else {
            answerLabel.setText("Ответ не верный");
            answerLabel.setStyle("-fx-text-fill: red");
        }
    }

    public void updateState(){
        currentCountLabel.setText(currentQuestion + "/" + all);
        correctCountLabel.setText(correct + "/" + all);
    }

    public boolean inspEnd(){
        if(currentQuestion == all){
            root.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ваши результаты");
            alert.setHeaderText(null);
            alert.setContentText("Вы ответили на " + correct + "/" + all + " вопросов.");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public void writeAnswer(ActionEvent actionEvent) {
        getAnswer();
        if(inspEnd()){
            return;
        }
        updateQuestion();
        updateState();
    }

    public void setStage(Stage s){
        root = s;
        root.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if(timer != null){
                    timer.cancel();
                    timer = null;
                }
                if(currentQuestion != all){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Оповещение");
                    alert.setHeaderText(null);
                    alert.setContentText("Вы прервали тестирование.\nРезультаты обнулены.");
                    alert.showAndWait();
                }
            }
        });
    }

}
