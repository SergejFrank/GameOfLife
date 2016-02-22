package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Game Of Life");

        Group cellGroup = new Group();
        final Board board = new Board(40);
        cellGroup.getChildren().addAll(board.getCells());

        stage.setOnCloseRequest(e -> board.pauseGame());

        Scene scene = new Scene(cellGroup, board.getBoardSize(), board.getBoardSize(), Color.WHITE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:  board.generateRandomBoard(); break;
                case SPACE: board.toggleGame(); break;
                case C:
                    board.killAll();
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
