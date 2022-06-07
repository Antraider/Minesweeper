package minesweeper;

public class Cell {
    private boolean isMine = false;
    public char symbol = '.';


    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
        symbol = 'X';
    }
}

