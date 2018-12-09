package com.company;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sliding Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new SlidingPuzzleInterface());
        frame.pack();
        frame.show();
        frame.setResizable(false);
    }

}
