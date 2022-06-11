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
        field.countMine(field.field);
        System.out.println(field.toString());
        while (!field.isEnd) {
            System.out.println("Set/delete mines marks (x and y coordinates):");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (field.field[y - 1][x - 1].getMinesFound() != 0) {
                System.out.println("There is a number here!");
                continue;
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
            System.out.println(field.toString());
            if (counter == mines) {
                field.isEnd = true;
                System.out.println("Congratulations! You found all the mines!");
            }

        }
    }
}
