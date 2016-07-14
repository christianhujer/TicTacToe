package com.nelkinda.tictactoe;

import static com.nelkinda.tictactoe.Player.NONE;

public class TicTacToe {
    private final Player[][] fields = { { NONE, NONE, NONE }, { NONE, NONE, NONE }, { NONE, NONE, NONE } };
    private Player previousPlayer;

    public static TicTacToe parseInitialGame(final String s) {
        final TicTacToe parsedGame = new TicTacToe();
        int x = 0, y = 0;
        for (final char c : s.toCharArray()) {
            switch (c) {
            case 'X':
                parsedGame.fields[x][y] = Player.PLAYER1;
                break;
            case 'O':
                parsedGame.fields[x][y] = Player.PLAYER2;
                break;
            case '\n':
                x++;
                y = -1;
                break;
            }
            y++;
        }
        return parsedGame;
    }

    public void move(final Player player, final int x, final int y) {
        if (previousPlayer == player)
            throw new PlayerMovedTwiceException();
        this.previousPlayer = player;
        if (fields[x][y] != NONE)
            throw new FieldAlreadyOccupiedException();
        fields[x][y] = player;
        if (isGameWon())
            throw new GameWonException();
    }

    public boolean isGameWon() {
        return isGameWonByHorizontalLines() || isGameWonByVerticalLines() || isGameWonByDiagonals();
    }

    public Player get(final int x, final int y) {
        return fields[x][y];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++)
                sb.append(fields[x][y].toString());
            sb.append('\n');
        }
        return sb.toString().trim();
    }

    public boolean isGameWonByHorizontalLines() {
        for (int x = 0; x < 3; x++)
            if (fields[x][0] != NONE && fields[x][0] == fields[x][1] && fields[x][0] == fields[x][2])
                return true;
        return false;
    }

    public boolean isGameWonByVerticalLines() {
        for (int y = 0; y < 3; y++)
            if (fields[0][y] != NONE && fields[0][y] == fields[1][y] && fields[0][y] == fields[2][y])
                return true;
        return false;
    }

    public boolean isGameWonByDiagonals() {
        if (fields[0][0] != NONE && fields[0][0] == fields[1][1] && fields[0][0] == fields[2][2])
            return true;
        if (fields[2][0] != NONE && fields[2][0] == fields[1][1] && fields[2][0] == fields[0][2])
            return true;
        return false;
    }
}
