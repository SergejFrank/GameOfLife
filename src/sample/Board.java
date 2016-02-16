package sample;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board {

    private Cell[][] cells;
    private final int boardSize;
    private boolean gameStarted = false;
    private static Random rnd = new Random();

    public Board(int boardSize) {
        this.boardSize = boardSize;

        cells = new Cell[boardSize][boardSize];

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }

        for (Cell cell: getCells()) {
            cell.setNeighbours(getNeighbours(cell));
        }
    }

    public void toggleGame(){
            gameStarted = !gameStarted;
            nextRound();
    }

    public void generateRandomBoard(){
            for (Cell cell: getCells()) {
                if(rnd.nextBoolean()){
                    cell.toggleAlive();
                }
            }
    }

    public ArrayList<Cell> getCells() {
        ArrayList<Cell> flattendCells = new ArrayList<>();
        for (Cell[] cellsRow : cells) {
            Collections.addAll(flattendCells, cellsRow);
        }
        return flattendCells;
    }

    public int getBoardSize() {
        return boardSize * cells[0][0].getCellSize();
    }

    private Cell getCell(int pos_x, int pos_y) {
        return cells[pos_x][pos_y];
    }

    private void calculateNextRound(){
        for (Cell cell: getCells()) {
            cell.calculateNextRound();
        }
    }

    private void nextRound(){
        if(gameStarted) {
            final Task task = new Task<Void>() {
                @Override
                public Void call() {

                    calculateNextRound();
                    for (Cell cell : getCells()) {
                        cell.nextRound();
                    }

                    try {
                        Thread.sleep(55);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    nextRound();

                    return null;
                }
            };
            new Thread(task).start();
        }

    }

    private ArrayList<Cell> getNeighbours(Cell cell) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        int[] cellPos = cell.getPos();
        int pos_x = cellPos[0];
        int pos_y = cellPos[1];

        // Left cells
        if (pos_x > 0) {
            neighbours.add(getCell(pos_x - 1, pos_y));

            if (pos_y > 0) {
                neighbours.add(getCell(pos_x - 1, pos_y - 1));
            }else if(pos_y == 0){
                neighbours.add(getCell(pos_x - 1, boardSize - 1));
            }

            if (pos_y < boardSize - 1) {
                neighbours.add(getCell(pos_x - 1, pos_y + 1));
            }else if(pos_y == boardSize - 1){
                neighbours.add(getCell(pos_x - 1, 0));
            }
        }else if(pos_x == 0){
            neighbours.add(getCell(boardSize - 1, pos_y));

            if (pos_y > 0) {
                neighbours.add(getCell(boardSize - 1, pos_y - 1));
            }else if(pos_y == 0){
                neighbours.add(getCell(boardSize - 1, boardSize - 1));
            }

            if (pos_y < boardSize - 1) {
                neighbours.add(getCell(boardSize - 1, pos_y + 1));
            }else if(pos_y == boardSize - 1){
                neighbours.add(getCell(boardSize - 1, 0));
            }
        }

        // Right cells
        if (pos_x < boardSize - 1) {
            neighbours.add(getCell(pos_x + 1, pos_y));

            if (pos_y > 0) {
                neighbours.add(getCell(pos_x + 1, pos_y - 1));
            }else if(pos_y == 0){
                neighbours.add(getCell(pos_x + 1, boardSize - 1));
            }

            if (pos_y < boardSize - 1) {
                neighbours.add(getCell(pos_x + 1, pos_y + 1));
            }else if(pos_y == boardSize - 1){
                neighbours.add(getCell(pos_x + 1, 0));
            }
        }else if(pos_x == boardSize - 1){
            neighbours.add(getCell(0, pos_y));

            if (pos_y > 0) {
                neighbours.add(getCell(0, pos_y - 1));
            }else if(pos_y == 0){
                neighbours.add(getCell(0, boardSize - 1));
            }

            if (pos_y < boardSize - 1) {
                neighbours.add(getCell(0, pos_y + 1));
            }else if(pos_y == boardSize - 1){
                neighbours.add(getCell(0, 0));
            }
        }

        // Top cell
        if (pos_y > 0) {
            neighbours.add(getCell(pos_x, pos_y - 1));
        }else if(pos_y == 0){
            neighbours.add(getCell(pos_x, boardSize - 1));
        }

        // Bottom cell
        if (pos_y < boardSize - 1) {
            neighbours.add(getCell(pos_x, pos_y + 1));
        }else if(pos_y == boardSize - 1){
            neighbours.add(getCell(pos_x, 0));
        }

        return neighbours;
    }
}
