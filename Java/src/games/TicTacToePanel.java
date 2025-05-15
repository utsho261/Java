package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToePanel extends JPanel {
    private CardLayout cards = new CardLayout();
    private JPanel menuCard = new JPanel();
    private GameBoardPanel gameCard = new GameBoardPanel();

    public TicTacToePanel() {
        setLayout(cards);
        buildMenuCard();
        add(menuCard, "MENU");
        add(gameCard, "GAME");

        // show menu first
        cards.show(this, "MENU");
    }

    private void buildMenuCard() {
        menuCard.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel title = new JLabel("Tic-Tac-Toe");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        menuCard.add(title, gbc);

        gbc.gridy++;
        JButton pvp = new JButton("Player vs Player");
        pvp.addActionListener(e -> {
            gameCard.setMode(false);
            cards.show(this, "GAME");
        });
        menuCard.add(pvp, gbc);

        gbc.gridy++;
        JButton pvc = new JButton("Player vs Computer");
        pvc.addActionListener(e -> {
            gameCard.setMode(true);
            cards.show(this, "GAME");
        });
        menuCard.add(pvc, gbc);
    }

    // Inner class for the actual board + quit button
    private static class GameBoardPanel extends JPanel implements ActionListener {
        private JButton[] btn = new JButton[9];
        private boolean playerXTurn = true, vsComputer = false;
        private Random rand = new Random();

        public GameBoardPanel() {
            setLayout(new BorderLayout(5,5));

            JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton quit = new JButton("← Back");
            quit.addActionListener(e -> {
                // return to menu
                Container parent = GameBoardPanel.this.getParent();
                if (parent instanceof JComponent) {
                    CardLayout cl = (CardLayout)((JComponent)parent).getLayout();
                    cl.show(parent, "MENU");
                }
            });
            top.add(quit);
            add(top, BorderLayout.NORTH);

            JPanel board = new JPanel(new GridLayout(3,3,5,5));
            for (int i = 0; i < 9; i++) {
                btn[i] = new JButton("");
                btn[i].setFont(new Font("Arial", Font.BOLD, 40));
                btn[i].addActionListener(this);
                board.add(btn[i]);
            }
            add(board, BorderLayout.CENTER);

            JButton reset = new JButton("Restart");
            reset.addActionListener(e -> resetBoard());
            add(reset, BorderLayout.SOUTH);
        }

        public void setMode(boolean vsComp) {
            this.vsComputer = vsComp;
            resetBoard();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if (!b.getText().isEmpty()) return;

            if (vsComputer) {
                // only allow player X to click
                if (!playerXTurn) return;
                b.setText("X");
                if (checkWin("X")) {
                    JOptionPane.showMessageDialog(this, "Player wins!");
                    resetBoard(); return;
                }
                if (isDraw()) {
                    JOptionPane.showMessageDialog(this, "Draw!");
                    resetBoard(); return;
                }
                playerXTurn = false;
                SwingUtilities.invokeLater(this::computerMove);
            } else {
                // PvP
                b.setText(playerXTurn ? "X" : "O");
                if (checkWin(playerXTurn ? "X" : "O")) {
                    JOptionPane.showMessageDialog(this,
                            (playerXTurn ? "Player X" : "Player O") + " wins!");
                    resetBoard(); return;
                }
                if (isDraw()) {
                    JOptionPane.showMessageDialog(this, "Draw!");
                    resetBoard(); return;
                }
                playerXTurn = !playerXTurn;
            }
        }

        private void computerMove() {
            // try win/block
            if (!tryWinOrBlock("O") && !tryWinOrBlock("X")) {
                // random
                int i;
                do { i = rand.nextInt(9); }
                while (!btn[i].getText().isEmpty());
                btn[i].setText("O");
            }
            if (checkWin("O")) {
                JOptionPane.showMessageDialog(this, "Computer wins!");
                resetBoard(); return;
            }
            if (isDraw()) {
                JOptionPane.showMessageDialog(this, "Draw!");
                resetBoard(); return;
            }
            playerXTurn = true;
        }

        private boolean tryWinOrBlock(String m) {
            int[][] lines = {
                    {0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}
            };
            for (int[] L : lines) {
                String a = btn[L[0]].getText(),
                        b = btn[L[1]].getText(),
                        c = btn[L[2]].getText();
                if (a.equals(m) && b.equals(m) && c.isEmpty()) {
                    btn[L[2]].setText("O"); return true;
                }
                if (a.equals(m) && c.equals(m) && b.isEmpty()) {
                    btn[L[1]].setText("O"); return true;
                }
                if (b.equals(m) && c.equals(m) && a.isEmpty()) {
                    btn[L[0]].setText("O"); return true;
                }
            }
            return false;
        }

        private boolean checkWin(String m) {
            int[][] lines = {
                    {0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}
            };
            for (int[] L : lines) {
                if (btn[L[0]].getText().equals(m) &&
                        btn[L[1]].getText().equals(m) &&
                        btn[L[2]].getText().equals(m)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isDraw() {
            for (JButton b : btn)
                if (b.getText().isEmpty()) return false;
            return true;
        }

        private void resetBoard() {
            for (JButton b : btn) {
                b.setText("");
                b.setBackground(null);
            }
            playerXTurn = true;
        }
    }
}

