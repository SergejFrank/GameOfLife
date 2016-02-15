package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private static final int Size = 15;
    private boolean isAlive = false;

    public Cell(int pos_x, int pos_y) {
        super(Size, Size);
        setStroke(Color.BLACK);
        setStrokeWidth(1.0);
        setFill(Color.RED);
        setPosition(pos_x, pos_y);
    }

    private void setPosition(int pos_x, int pos_y) {
        setTranslateX(pos_x * Size);
        setTranslateY(pos_y * Size);
    }
}
