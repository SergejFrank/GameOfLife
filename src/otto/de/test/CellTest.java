package otto.de.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;

import org.junit.Test;

import otto.de.Cell;

public class CellTest {

    @Test
    public void testToggleAlive() throws Exception {
        Cell cell = new Cell(0, 0);

        assertThat(cell.isAlive(), is(false));

        cell.toggleAlive();
        assertThat(cell.isAlive(), is(true));

        cell.toggleAlive();
        assertThat(cell.isAlive(), is(false));
    }

    @Test
    public void testNextRound() throws Exception {
        Cell cell1 = new Cell(0, 0);
        Cell cell2 = new Cell(0, 0);
        Cell cell3 = new Cell(0, 0);
        Cell cell4 = new Cell(0, 0);
        Cell cell5 = new Cell(0, 0);
        Cell cell6 = new Cell(0, 0);
        Cell cell7 = new Cell(0, 0);
        Cell cell8 = new Cell(0, 0);
        Cell cell9 = new Cell(0, 0);

        ArrayList<Cell> neighbours = new ArrayList<>();
        neighbours.add(cell2);
        neighbours.add(cell3);
        neighbours.add(cell4);
        neighbours.add(cell5);
        neighbours.add(cell6);
        neighbours.add(cell7);
        neighbours.add(cell8);
        neighbours.add(cell9);

        cell1.setNeighbours(neighbours);

        cell2.toggleAlive();
        cell3.toggleAlive();
        cell4.toggleAlive();

        cell1.calculateNextRound();
        cell1.nextRound();

        assertThat(cell1.isAlive(), is(true));
    }

    @Test
    public void testSuicide() throws Exception {
        Cell cell = new Cell(0, 0);
        cell.toggleAlive();

        assertThat(cell.isAlive(), is(true));

        cell.suicide();
        assertThat(cell.isAlive(), is(false));
    }
}