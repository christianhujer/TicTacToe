package com.nelkinda.tictactoe;

public enum Player {
    PLAYER2('O'), PLAYER1('X'), NONE('.');
    private final char displayChar;
    Player(final char displayChar) {
        this.displayChar = displayChar;
    }
    @Override
    public String toString() {
        return Character.toString(displayChar);
    }
}
