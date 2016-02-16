package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Cell extends Rectangle {

    private static final int SIZE = 10;
    private boolean isAlive = false;
    private boolean isAliveNextRound = false;
    private final int pos_x;
    private final int pos_y;
    private ArrayList<Cell> neighbours;

    public Cell(int pos_x, int pos_y) {
        super(SIZE, SIZE);
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        setFill(Color.GREY);
        setPosition(pos_x, pos_y);

        this.setOnMouseClicked(t -> toggleAlive());
    }

    public int[] getPos() {
        return new int[]{pos_x, pos_y};
    }

    public int getCellSize() {
        return SIZE;
    }

    public void toggleAlive() {
        this.isAlive = !this.isAlive;
        changeColor();
    }

    public void nextRound() {
        isAlive = isAliveNextRound;
        changeColor();
    }

    public void calculateNextRound(){
        int neighboursSize = getCountAliveNeighbours();

        if (isAlive() && neighboursSize >= 2 && neighboursSize <= 3){
            revive();
        }else if((isAlive() && neighboursSize < 2) || neighboursSize > 3){
            kill();
        }else if(!isAlive() && neighboursSize == 3){
            revive();
        }
    }

    public void setNeighbours(ArrayList<Cell> neighbours){
        this.neighbours = neighbours;
    }

    private void setPosition(int pos_x, int pos_y) {
        setTranslateX(pos_x * SIZE);
        setTranslateY(pos_y * SIZE);
    }

    private void revive() {
        isAliveNextRound = true;
    }

    private void kill() {
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

    private int getCountAliveNeighbours(){
        int aliveNeighbours = 0;
        for (Cell neighbourCell:neighbours) {
            if(neighbourCell.isAlive()){
                aliveNeighbours++;
            }
        }
        return aliveNeighbours;
    }
}
