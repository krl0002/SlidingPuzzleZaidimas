package com.company;

public class SlidingPuzzleLogic {

    private static final int SIZE = 2;

    private Button[][] content;
    private Button emptyButton;

    public static boolean isSolvable(int[] p) {
        int inversions = 0;

        for(int i = 0; i < SIZE - 1; i++) {
            for(int j = i + 1; j < SIZE; j++)
                if(p[i] > p[j]) inversions++;

            if(p[i] == 0 && i % 2 == 1) inversions++;
        }

        return (inversions % 2 == 0);
    }

    public SlidingPuzzleLogic() {
        content = new Button[SIZE][SIZE];
        refresh();
    }

    public int getNumber(int rows, int columns) {
        return content[rows][columns].getNumber();
    }

    public void refresh() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                content[r][c] = new Button(r, c, (r * SIZE + c + 1));
            }
        }

        emptyButton = content[SIZE - 1][SIZE - 1];
        emptyButton.setNumber(0);

        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                sukeistiLangelius(row, column, (int) (Math.random() * SIZE), (int) (Math.random() * SIZE));
            }
        }

        int[] array = new int[SIZE*SIZE];
        int x = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Button lang = content[row][column];
                array[x]+=lang.getNumber();
                x++;
            }
        }

        if(!isSolvable(array)) {
            refresh();
        }
    }

    public boolean pajudintiLang(int row, int column) {
        return isEmpty(row, column, -1, 0) || isEmpty(row, column, 1, 0)
                || isEmpty(row, column, 0, -1) || isEmpty(row, column, 0, 1);
    }

    private boolean isEmpty(int row, int column, int row1, int column1) {
        int row2 = row + row1;
        int column2 = column + column1;
        if (tinkamiEiluteStulpelis(row2, column2) && content[row2][column2] == emptyButton) {
            sukeistiLangelius(row, column, row2, column2);
            return true;
        }
        return false;
    }

    public boolean tinkamiEiluteStulpelis(int r, int c) {
        return r >= 0 && r < SIZE && c >= 0 && c < SIZE;
    }

    private void sukeistiLangelius(int row1, int column1, int row2, int column2) {
        Button temp = content[row1][column1];
        content[row1][column1] = content[row2][column2];
        content[row2][column2] = temp;

    }

    public int gameOver() {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                Button button = content[row][column];
                if(button.finalPosition(row, column)) count++;
            }
        }
        return count;
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