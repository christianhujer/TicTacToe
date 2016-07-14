package com.nelkinda.tictactoe;

import org.junit.Test;

import static com.nelkinda.tictactoe.Player.NONE;
import static com.nelkinda.tictactoe.Player.PLAYER1;
import static com.nelkinda.tictactoe.Player.PLAYER2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TicTacToeTest {
    private TicTacToe ticTacToe = new TicTacToe();

    @Test(expected = PlayerMovedTwiceException.class)
    public void playersMustTakeTurns() {
        ticTacToe.move(PLAYER1, 1, 1);
        ticTacToe.move(PLAYER1, 1, 2);
    }

    @Test(expected = FieldAlreadyOccupiedException.class)
    public void playersMustMoveOnAnEmptyField() {
        ticTacToe.move(PLAYER1, 1, 1);
        ticTacToe.move(PLAYER2, 1, 1);
    }

    @Test
    public void initialFieldIsEmpty() {
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                assertEquals(NONE, ticTacToe.get(x, y));
    }

    @Test
    public void movesAreRecorded() {
        ticTacToe.move(PLAYER1, 1, 1);
        assertEquals(PLAYER1, ticTacToe.get(1, 1));
        ticTacToe.move(PLAYER2, 1, 2);
        assertEquals(PLAYER1, ticTacToe.get(1, 1));
        assertEquals(PLAYER2, ticTacToe.get(1, 2));
    }

    @Test(expected = GameWonException.class)
    public void gameIsWon() {
        ticTacToe.move(PLAYER1, 1, 1);
        ticTacToe.move(PLAYER2, 1, 2);
        ticTacToe.move(PLAYER1, 0, 1);
        ticTacToe.move(PLAYER2, 1, 0);
        ticTacToe.move(PLAYER1, 2, 1);
    }

    @Test
    public void firstHorizontalForPlayer1() {
        ticTacToe = TicTacToe.parseInitialGame("XXX\n...\n...");
        assertTrue(ticTacToe.isGameWonByHorizontalLines());
    }

    @Test
    public void firstVerticalForPlayer1() {
        ticTacToe = TicTacToe.parseInitialGame(".X.\n.X.\n.X.");
        assertTrue(ticTacToe.isGameWonByVerticalLines());
    }

    @Test
    public void diagonalWon() {
        ticTacToe = TicTacToe.parseInitialGame("X..\n.X.\n..X");
        assertTrue(ticTacToe.isGameWonByDiagonals());
    }
}
