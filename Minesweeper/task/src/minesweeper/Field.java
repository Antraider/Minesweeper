package minesweeper;

import java.util.Random;

public class Field {
    Cell[][] field = new Cell[9][9];
    boolean isEnd = false;

    public Field(int mines){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new Cell();
            }
        }
        generateRandomMines(mines);
    }

    void generateRandomMines(int remain) {
        Random random = new Random();
        while (remain > 0) {
            Cell cell = field[random.nextInt(9)][random.nextInt(9)];
            if (!cell.isMine() && !cell.isOpened()){
                cell.setMine(true);
                remain--;
            }
        }
    }

    void generateRandomMines(int x, int y) {
        generateRandomMines(1);
        if (field[y][x].isMine()) {
            field[y][x].setMine(false);
            generateRandomMines(x, y);
        }
    }

    public void openField(int i, int j) {

        if (!field[i][j].isMine() && countMine(i, j) > 0) {
            field[i][j].setOpened(true);
        } else if (!field[i][j].isMine() && countMine(i, j) == 0 && !field[i][j].isOpened()) {
            field[i][j].setOpened(true);
            if (i < 8) {
                if (field[i + 1][j].isFlagged()) {
                    field[i + 1][j].setFlagged(false);
                }
                openField(i + 1, j);
            } //down

            if (i > 0) {
                if (field[i - 1][j].isFlagged()) {
                    field[i - 1][j].setFlagged(false);
                }
                openField(i - 1, j);
            } //up
            if (j < 8) {
                if (field[i][j + 1].isFlagged()) {
                    field[i][j + 1].setFlagged(false);
                }
                openField(i,j + 1);
            } //right
            if (j > 0) {
                if (field[i][j - 1].isFlagged()) {
                    field[i][j - 1].setFlagged(false);
                }
                openField(i,j - 1);
            } //left

            if (i < 8 && j < 8) {
                if (field[i + 1][j + 1].isFlagged()) {
                    field[i + 1][j + 1].setFlagged(false);
                }
                openField(i + 1, j + 1);
            }

            if (i > 0 && j > 0) {
                if (field[i - 1][j - 1].isFlagged()) {
                    field[i - 1][j - 1].setFlagged(false);
                }
                openField(i - 1, j - 1);
            }

            if (i < 8 && j > 0) {
                if (field[i + 1][j - 1].isFlagged()) {
                    field[i + 1][j - 1].setFlagged(false);
                }
                openField(i + 1, j - 1);
            }

            if (i > 0 && j < 8) {
                if (field[i - 1][j + 1].isFlagged()) {
                    field[i - 1][j + 1].setFlagged(false);
                }
                openField(i - 1, j + 1);
            }
        }
    }

    public int countMine(int i, int j) {

        int count = 0;
        if (!field[i][j].isMine()) {
            if (i > 0 && field[i - 1][j].isMine()) {
                count++;
            } // check up
            if (i < 8 && field[i + 1][j].isMine()) {
                count++;
            } // check down
            if (j > 0 && field[i][j - 1].isMine()) {
                count++;
            } // check left
            if (j < 8 && field[i][j + 1].isMine()) {
                count++;
            } // check right
            if (i > 0 && j > 0 && field[i - 1][j - 1].isMine()) {
                count++;
            } // check left-up corner
            if (i > 0 && j < 8 && field[i - 1][j + 1].isMine()) {
                count++;
            } // check right-up corner
            if (i < 8 && j > 0 && field[i + 1][j - 1].isMine()) {
                count++;
            } // check left-down corner
            if (i < 8 && j < 8 && field[i + 1][j + 1].isMine()) {
                count++;
            } // check right-down corner
            if (count > 0) {
                field[i][j].setMinesFound(count);
            }
        }
        return count;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" |123456789|\n" +
                "-|---------|\n");
        for (int i = 0; i < 9; i++) {
            builder.append(i+1).append("|");
            for (int j = 0; j < 9; j++) {
                if (field[i][j].isFlagged()) {
                    builder.append('*');
                } else if (!field[i][j].isOpened()) {
                    builder.append('.');
                } else if (field[i][j].getMinesFound() > 0) {
                    builder.append(field[i][j].getMinesFound());
                } else {
                    builder.append('/');
                }
            }
            builder.append("|\n");
        }
        builder.append("-|---------|");
        return builder.toString();
    }
}
