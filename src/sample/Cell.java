package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private static final int SIZE = 15;
    private boolean isAlive = false;

    public Cell(int pos_x, int pos_y) {
        super(SIZE, SIZE);
        setStroke(Color.BLACK);
        setStrokeWidth(1.0);
        setFill(Color.RED);
        setPosition(pos_x, pos_y);
    }

    private void setPosition(int pos_x, int pos_y) {
        setTranslateX(pos_x * SIZE);
        setTranslateY(pos_y * SIZE);
    }

    public int getCellSize() {
        return SIZE;
    }
}
