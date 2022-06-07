package minesweeper;

import java.util.Random;

public class Field {
    Cell[][] field = new Cell[9][9];

    public Field(int mines){

        int remain = mines;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new Cell();
            }
        }
        Random random = new Random();
        while (remain > 0) {
            Cell cell = field[random.nextInt(9)][random.nextInt(9)];
            if (!cell.isMine()){
                cell.setMine(true);
                remain--;
            }
        }
    }

    public void countMine(Cell[][] cell) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!cell[i][j].isMine()) {
                    int count = 0;
                    if (i > 0 && cell[i - 1][j].isMine()) {
                        count++;
                    } // check up
                    if (i < 8 && cell[i + 1][j].isMine()) {
                        count++;
                    } // check down
                    if (j > 0 && cell[i][j - 1].isMine()) {
                        count++;
                    } // check left
                    if (j < 8 && cell[i][j + 1].isMine()) {
                        count++;
                    } // check right
                    if (i > 0 && j > 0 && cell[i - 1][j - 1].isMine()) {
                        count++;
                    } // check left-up corner
                    if (i > 0 && j < 8 && cell[i - 1][j + 1].isMine()) {
                        count++;
                    } // check right-up corner
                    if (i < 8 && j > 0 && cell[i + 1][j - 1].isMine()) {
                        count++;
                    } // check left-down corner
                    if (i < 8 && j < 8 && cell[i + 1][j + 1].isMine()) {
                        count++;
                    } // check right-down corner
                    if (count > 0) {
                        cell[i][j].symbol = (char)(count + '0');
                    }
                }
            }
        }
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(field[i][j].symbol);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
