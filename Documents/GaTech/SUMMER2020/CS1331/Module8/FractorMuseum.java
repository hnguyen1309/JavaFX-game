import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FractorMuseum extends Application{
    private static int p1Score = 0;
    private static int p2Score = 0;
    private static TextField f1;
    private static TextField f2;
    private static int result;

    public static int getP1Score(){
        return p1Score;
    }
    public static int getP2Score(){
        return p2Score;
    }
    public static void setP1Score(int newScore){
        if (newScore > p1Score) {
            p1Score = newScore;
        }
    }
    public static void setP2Score(int newScore){
        if (newScore > p2Score) {
            p2Score = newScore;
        }
    }
    public static void main(String[] args){
        launch(args);
    }
    private static void incrementP1Score() {
        FractorMuseum.setP1Score((FractorMuseum.getP1Score() + 1));
    }
    private static void incrementP2Score() {
        FractorMuseum.setP2Score((FractorMuseum.getP2Score() + 1));
    }
    public void start(Stage mainStage){
        mainStage.setTitle("Fractal Museum");
        Button newGame = new Button("New Game");
        Button submit = new Button ("Submit");
        Pane header = new Pane();
        Pane center = new Pane();
        GridPane bottom = new GridPane();
        Pane left = new Pane();
        Pane right = new Pane();
        
        //create textfield to player 1
        TextField f1 = new TextField();
        f1.setPromptText("P1 Guess");
        TextField f2 = new TextField();
        f2.setPromptText("P2 Guess");
        Label p1 = new Label("Player 1 Score:" + getP1Score());
        Label p2 = new Label("Player 2 Score:" + getP2Score());
    
        submit.setOnAction((ActionEvent event) -> {
            if (!(f1.getText().isEmpty() || f2.getText().isEmpty())) {
                submit.setDisable(false);
            }
            String inputOne = f1.getCharacters().toString();
            String inputTwo = f2.getCharacters().toString();
                try {
                    int p1Guess = Integer.parseInt(inputOne);
                    int p2Guess = Integer.parseInt(inputTwo);
                    if(p1Guess <= 0 || p2Guess <= 0){
                        throw new Exception("Both guesses should be positive intergers");
                    }
                    if (Math.abs(result - p1Guess) < Math.abs(result - p2Guess)) {
                        incrementP1Score();
                    } else {
                        incrementP2Score();
                    }
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Input");
                    alert.setHeaderText("Input Mismatch Exception");
                    alert.setContentText("Both guesses should be positive intergers");
                    alert.showAndWait();
                    submit.setDisable(false);
                }
            p1.setText("Player 1 Score:" + getP1Score());
            p2.setText("Player 2 Score:" + getP2Score());
            submit.setDisable(true);

        });

        newGame.setOnAction((ActionEvent event) -> {
            center.getChildren().clear();
            result = FractalFactory.drawFractal(center);
            //center.getChildren().clear();
            submit.setDisable(false);
        });

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        center.setPrefSize(500, 500);
        Scene scene = new Scene(pane, 750, 750);
        mainStage.setScene(scene);

        pane.setTop(header);
        pane.setLeft(left);
        pane.setRight(right);
        pane.setCenter(center);
        BorderPane.setAlignment(center, Pos.CENTER);
        pane.setBottom(bottom);
        header.getChildren().add(newGame);
        bottom.setHgap(5);
        bottom.setVgap(5);
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.add(f1, 1, 0);
        bottom.add(f2, 2, 0);
        bottom.add(submit, 3, 0);
        bottom.add(p1,2,1);
        bottom.add(p2,3,1);
        mainStage.show();
    }
        
}