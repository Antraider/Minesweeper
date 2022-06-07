package minesweeper;

import java.util.Scanner;

public class Game {
    int mines;
    Field field;

    void start() {
        System.out.print("How many mines do you want on the field? ");
        Scanner scanner = new Scanner(System.in);
        mines = scanner.nextInt();
        scanner.close();
        field = new Field(mines);
        field.countMine(field.field);
        System.out.println(field.toString());
    }
}
