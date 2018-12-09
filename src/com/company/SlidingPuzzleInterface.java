package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SlidingPuzzleInterface extends JPanel {

    private Interface iface;
    private SlidingPuzzleLogic logic = new SlidingPuzzleLogic();
    private int mouseClicks = 0;

    public SlidingPuzzleInterface() {
        JButton startGame = new JButton("Pradėti naują žaidimą");
        startGame.addActionListener(new start());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(startGame);

        iface = new Interface();

        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(iface, BorderLayout.CENTER);
    }

    class Interface extends JPanel implements MouseListener {
        private static final int SIZE = 3;
        private static final int BUTTON_SIZE = 90;
        private Font fontStyle;

        public Interface() {
            fontStyle = new Font("SansSerif", Font.BOLD, 45);
            this.setPreferredSize(new Dimension(BUTTON_SIZE * SIZE, BUTTON_SIZE * SIZE));
            this.setBackground(Color.BLACK);
            this.addMouseListener(this);
        }

        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < SIZE; row++) {
                for (int column = 0; column < SIZE; column++) {
                    int x = column * BUTTON_SIZE;
                    int y = row * BUTTON_SIZE;
                    int number = logic.getNumber(row, column);
                    if (number != 0) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x + 2, y + 2, BUTTON_SIZE - 3, BUTTON_SIZE - 3);
                        g.setColor(Color.black);
                        g.setFont(fontStyle);
                        g.drawString("" + number, x + 30, y + (3 * BUTTON_SIZE) / 4);
                    }
                }
            }
        }

        public void mousePressed(MouseEvent e) {
            int column = e.getX() / BUTTON_SIZE;
            int row = e.getY() / BUTTON_SIZE;

            logic.moveButton(row, column);
            this.repaint();
            mouseClicks++;

            if (logic.isGameOver() == SIZE * SIZE) {
                JOptionPane.showMessageDialog(null, "Žaidimas baigtas, atlikote: " + mouseClicks + " ėjimus",
                        "Žaidimas baigtas!", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public class start implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mouseClicks = 0;
            logic.refresh();
            iface.repaint();
        }
    }

    public SlidingPuzzleInterface.Interface getIface() {
        return iface;
    }

    public int getMouseClicks() {
        return mouseClicks;
    }

}
