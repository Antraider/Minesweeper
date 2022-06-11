package minesweeper;

public class Cell {
    private boolean isMine = false;
    private boolean isFlagged = false;
    private int minesFound = 0;


    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flag) {
        isFlagged = flag;
    }

    public int getMinesFound() {
        return minesFound;
    }

    public void setMinesFound(int minesFound) {
        this.minesFound = minesFound;
    }
}

