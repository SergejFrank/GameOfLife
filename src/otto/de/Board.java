package otto.de;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.concurrent.Task;

public class Board {

    private Cell[][] cells;
    private final int boardSize;
    private GameStatus gameStatus = GameStatus.PAUSED;
    private static Random rnd = new Random();
    private int delay = 50;

    public Board(int boardSize) {
        this.boardSize = boardSize;

        cells = new Cell[boardSize][boardSize];

        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }

        for (Cell cell : getCells()) {
            cell.setNeighbours(getNeighbours(cell));
        }
    }

    public void toggleGame() {
        if (gameStatus.isStarted()) {
            gameStatus = GameStatus.PAUSED;
        } else {
            gameStatus = GameStatus.STARTED;
        }
        nextRound();
    }

    public void pauseGame() {
        gameStatus = GameStatus.PAUSED;
    }

    public void generateRandomBoard() {
        for (Cell cell : getCells()) {
            if (rnd.nextBoolean()) {
                cell.toggleAlive();
            }
        }
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
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

    private void calculateNextRound() {
        for (Cell cell : getCells()) {
            cell.calculateNextRound();
        }
    }

    private void nextRound() {
        if (gameStatus.isStarted()) {
            final Task task = new Task<Void>() {
                @Override
                public Void call() {

                    nextCycle();

                    try {
                        Thread.sleep(delay);
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

    public void nextCycle() {
        calculateNextRound();
        for (Cell cell : getCells()) {
            cell.nextRound();
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return this.delay;
    }

    private ArrayList<Cell> getNeighbours(Cell cell) {
        int[] cellPos = cell.getPos();
        int pos_x = cellPos[0];
        int pos_y = cellPos[1];

        int pos_x2 = Math.floorMod(pos_x - 1, boardSize);
        int pos_x3 = Math.floorMod(pos_x + 1, boardSize);
        int pos_y2 = Math.floorMod(pos_y - 1, boardSize);
        int pos_y3 = Math.floorMod(pos_y + 1, boardSize);

        ArrayList<Cell> neighbours = new ArrayList<>();

        neighbours.add(cells[pos_x2][pos_y2]);
        neighbours.add(cells[pos_x2][pos_y]);
        neighbours.add(cells[pos_x2][pos_y3]);
        neighbours.add(cells[pos_x][pos_y2]);
        neighbours.add(cells[pos_x][pos_y3]);
        neighbours.add(cells[pos_x3][pos_y2]);
        neighbours.add(cells[pos_x3][pos_y]);
        neighbours.add(cells[pos_x3][pos_y3]);

        return neighbours;
    }

    public void killAll() {
        for (Cell cell : getCells()) {
            cell.suicide();
        }
    }
}
