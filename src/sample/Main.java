package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TEST");
        Group root = new Group();

        final Board board = new Board(40);

        for (Cell c : board.getCells()) {
            root.getChildren().add(c);
        }
        Scene scene = new Scene(root, board.getSize(), board.getSize(), Color.WHITE);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    board.startGame(); break;
                    case DOWN:  board.startRandomGame(); break;
                    case SPACE:  board.stopGame(); break;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
