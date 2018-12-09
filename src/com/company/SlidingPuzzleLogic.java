package com.company;

public class SlidingPuzzleLogic {

    private static final int SIZE = 3;

    private Button[][] buttonContent;
    private Button emptyButton;


    public static boolean isSolvable(int[] array) {
        int inversions = 0;

        for(int i = 0; i < SIZE - 1; i++) {
            for(int j = i + 1; j < SIZE; j++) {
                if (array[i] > array[j]) inversions++;
            }
            if(array[i] == 0 && i % 2 == 1) inversions++;
        }

        return (inversions % 2 == 0);
    }

    public SlidingPuzzleLogic() {
        buttonContent = new Button[SIZE][SIZE];
        refresh();
    }

    public int getNumber(int rows, int columns) {
        return buttonContent[rows][columns].getNumber();
    }

    public void refresh() {
        int[] array = new int[SIZE*SIZE];
        int counter = 0;

        NumberGenerator.getGenerator().getNumber(buttonContent);

        emptyButton = buttonContent[SIZE - 1][SIZE - 1];
        emptyButton.setNumber(0);

        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                switchButtons(row, column, (int) (Math.random() * SIZE), (int) (Math.random() * SIZE));
                array[counter]+=getNumber(row, column);
                counter++;
            }
        }

        if(!isSolvable(array)) refresh();
    }

    public boolean moveButton(int row, int column) {
        return isEmpty(row, column, -1, 0) || isEmpty(row, column, 1, 0)
                || isEmpty(row, column, 0, -1) || isEmpty(row, column, 0, 1);
    }

    private boolean isEmpty(int row, int column, int row1, int column1) {
        int row2 = row + row1;
        int column2 = column + column1;
        if (checkRowAndColumn(row2, column2) && buttonContent[row2][column2] == emptyButton) {
            switchButtons(row, column, row2, column2);
            return true;
        }
        return false;
    }

    public boolean checkRowAndColumn(int row, int column) {
        return row >= 0 && row < SIZE && column >= 0 && column < SIZE;
    }

    private void switchButtons(int row1, int column1, int row2, int column2) {
        Button button = buttonContent[row1][column1];
        buttonContent[row1][column1] = buttonContent[row2][column2];
        buttonContent[row2][column2] = button;
    }

    public int isGameOver() {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Button button = buttonContent[row][column];
                if(button.finalPosition(row, column)) count++;
            }
        }
        return count;
    }

    public static int getSIZE() {
        return SIZE;
    }
}

class Button {
    private int row;
    private int column;
    private int number;

    public Button(int r, int c, int number) {
        row = r;
        column = c;
        this.number = number;
    }

    public void setNumber(int newNumber) {
        number = newNumber;
    }

    public int getNumber() {
        return number;
    }

    public boolean finalPosition(int r, int c) {
        return r == row && c == column;
    }

}