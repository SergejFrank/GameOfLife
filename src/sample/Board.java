package sample;

import java.util.ArrayList;

public class Board {

    private Cell[][] cells;
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;

        cells = new Cell[boardSize][boardSize];

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public ArrayList<Cell> getCells() {
        ArrayList<Cell> flattendCells = new ArrayList<>();
        for (Cell[] cellsRow : cells) {
            for (Cell cell : cellsRow) {
                flattendCells.add(cell);
            }
        }
        return flattendCells;
    }

    public int getSize() {
        return boardSize * cells[0][0].getCellSize();
    }
}
