package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TEST");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        Cell c = new Cell(0,0);
        Cell c2 = new Cell(0,1);

        root.getChildren().add(c);
        root.getChildren().add(c2);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
