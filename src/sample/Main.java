package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Game Of Life");


        Pane mainPane = new Pane();
        Group cellGroup = new Group();
        final Board board = new Board(40);
        cellGroup.getChildren().addAll(board.getCells());
        stage.setOnCloseRequest(e -> board.pauseGame());
        cellGroup.setTranslateX(100);

        Menu gameMenu = new Menu(board);

        mainPane.getChildren().add(cellGroup);
        mainPane.getChildren().add(gameMenu);

        Scene scene = new Scene(mainPane, board.getBoardSize()+100, board.getBoardSize(), Color.WHITE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:  board.generateRandomBoard(); break;
                case SPACE: board.toggleGame(); break;
                case C:
                    board.killAll();
                    break;
            }
            gameMenu.update();
        });



        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
