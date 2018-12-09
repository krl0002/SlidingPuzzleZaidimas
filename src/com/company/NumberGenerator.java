package com.company;

public class NumberGenerator {

    private static NumberGenerator generator;
    private static SlidingPuzzleLogic logic;
    private static int SIZE = logic.getSIZE();

    private NumberGenerator() {

    }

    public static synchronized NumberGenerator getGenerator() {
        if(generator == null) {
            generator = new NumberGenerator();
        }

        return generator;
    }

    public void getNumber(Button[][] number) {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                number[row][column] = new Button(row, column, row * SIZE + column + 1);
            }
        }
    }

}
