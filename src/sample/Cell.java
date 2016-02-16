package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private static final int SIZE = 10;
    private boolean isAlive = false;
    private boolean isAliveNextRound = false;
    private final int pos_x;
    private final int pos_y;

    public Cell(int pos_x, int pos_y) {
        super(SIZE, SIZE);
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        setFill(Color.GREY);
        setPosition(pos_x, pos_y);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                toggleAlive();
            }
        });
    }

    public int[] getPos() {
        return new int[]{pos_x, pos_y};
    }

    private void setPosition(int pos_x, int pos_y) {
        setTranslateX(pos_x * SIZE);
        setTranslateY(pos_y * SIZE);
    }

    public int getCellSize() {
        return SIZE;
    }

    public void toggleAlive() {
        this.isAlive = !this.isAlive;
        changeColor();
    }

    public void setAlive() {
        isAliveNextRound = true;
    }

    public void kill() {
        isAliveNextRound = false;
    }

    public boolean isAlive(){
        return isAlive;
    }

    private void changeColor() {
        if (isAlive) {
            this.setFill(Color.WHITE);
        } else {
            this.setFill(Color.GRAY);
        }
    }

    public void nextRound() {
        isAlive = isAliveNextRound;
        changeColor();
    }
}
