package minesweeper;

import java.util.Scanner;

public class Game {
    int mines;
    Field field;
    int counter;

    void start() {
        System.out.print("How many mines do you want on the field? ");
        Scanner scanner = new Scanner(System.in);
        mines = scanner.nextInt();
        field = new Field(mines);
        System.out.println(field.toString());
        System.out.println("Set/unset mines marks or claim a cell as free: ");
        makeMove(scanner.nextInt() ,scanner.nextInt() ,scanner.next() ,true);
        System.out.println(field.toString());
        while (!field.isEnd) {
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String move = scanner.next();
            makeMove(x, y, move);
            System.out.println(field.toString());
            if (counter == mines) {
                field.isEnd = true;
                System.out.println("Congratulations! You found all the mines!");
            }

        }
    }

    private void makeMove(int x, int y, String move, boolean isFirstMove) {
        if (field.field[y - 1][x - 1].isMine()) {
            field.generateRandomMines(x - 1, y - 1);
        }
        makeMove(x, y, move);
    }

    private void makeMove(int x, int y, String move) {
        switch (move) {
            case "free" : {
                if (field.field[y - 1][x - 1].isMine()) {
                    System.out.println("You stepped on a mine and failed!");
                    field.isEnd = true;
                } else {
                    field.openField(y - 1, x - 1);
                }
                break;
            }
            case "mine" : {
                if (field.field[y - 1][x - 1].getMinesFound() != 0) {
                    System.out.println("There is a number here!");
                } else if (!field.field[y - 1][x - 1].isFlagged() && field.field[y - 1][x - 1].getMinesFound() == 0) {
                    if (field.field[y - 1][x - 1].isMine()) {
                        counter++;
                    }
                    field.field[y - 1][x - 1].setFlagged(true);
                } else if (field.field[y - 1][x - 1].isFlagged() && field.field[y - 1][x - 1].getMinesFound() == 0) {
                    if (field.field[y - 1][x - 1].isMine()) {
                        counter--;
                    }
                    field.field[y - 1][x - 1].setFlagged(false);
                }
                break;
            }
            default: {
                System.out.println("Error, choose correct move (free, mine)");
            }
        }
    }
}
