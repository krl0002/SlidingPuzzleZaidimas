package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SlidingPuzzleInterface extends JPanel {
    private Interface Interface;
    private SlidingPuzzleLogic logic = new SlidingPuzzleLogic();
    private int mouseClicks = 0;

    public SlidingPuzzleInterface() {
        JButton startGame = new JButton("Pradėti naują žaidimą");
        startGame.addActionListener(new start());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(startGame);

        Interface = new Interface();


        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(Interface, BorderLayout.CENTER);


    }

    class Interface extends JPanel implements MouseListener {
        private static final int SIZE = 2;

        private static final int LANGELIO_DYDIS = 90;
        private Font fontStyle;

        public Interface() {
            fontStyle = new Font("SansSerif", Font.BOLD, 35);
            this.setPreferredSize(new Dimension(LANGELIO_DYDIS * SIZE, LANGELIO_DYDIS * SIZE));
            this.setBackground(Color.BLACK);
            this.addMouseListener(this);
        }

        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    int x = c * LANGELIO_DYDIS;
                    int y = r * LANGELIO_DYDIS;
                    int skaicius = logic.getNumber(r, c);
                    if (skaicius != 0) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x + 2, y + 2, LANGELIO_DYDIS - 3, LANGELIO_DYDIS - 3);
                        g.setColor(Color.black);
                        g.setFont(fontStyle);
                        g.drawString("" + skaicius, x + 30, y + (3 * LANGELIO_DYDIS) / 4);
                    }
                }
            }
        }
            public void mousePressed(MouseEvent e){
                int column = e.getX() / LANGELIO_DYDIS;
                int row = e.getY() / LANGELIO_DYDIS;
                logic.pajudintiLang(row, column);
                this.repaint();

                mouseClicks++;
                if(logic.gameOver() == SIZE*SIZE) {
                    JOptionPane.showMessageDialog(null, "Zaidimas baigtas, atlikote: "+mouseClicks+" ejimus",
                            "Zaidimas baigtas", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            public void mouseClicked (MouseEvent e) {};
            public void mouseReleased (MouseEvent e) {};
            public void mouseEntered (MouseEvent e) {};
            public void mouseExited (MouseEvent e) {};
        }

        public class start implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                mouseClicks = 0;
                logic.refresh();
                Interface.repaint();
            }
        }
    }
