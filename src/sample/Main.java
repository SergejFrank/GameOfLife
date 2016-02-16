package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game Of Life");
        Group root = new Group();

        final Board board = new Board(40);

        for (Cell c : board.getCells()) {
            root.getChildren().add(c);
        }
        Scene scene = new Scene(root, board.getBoardSize(), board.getBoardSize(), Color.WHITE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:  board.generateRandomBoard(); break;
                case SPACE: board.toggleGame(); break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
