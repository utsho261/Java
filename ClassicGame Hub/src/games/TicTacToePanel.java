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
        setBackground(new Color(30, 30, 35));
        buildMenuCard();
        add(menuCard, "MENU");
        add(gameCard, "GAME");
        cards.show(this, "MENU");
    }

    private void buildMenuCard() {
        menuCard.setBackground(new Color(30, 30, 35));
        menuCard.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel title = new JLabel("Tic-Tac-Toe");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(255, 215, 0));
        menuCard.add(title, gbc);

        JButton pvp = createStyledButton("Player vs Player");
        pvp.addActionListener(e -> {
            gameCard.setMode(false);
            cards.show(this, "GAME");
        });
        gbc.gridy++;
        menuCard.add(pvp, gbc);

        JButton pvc = createStyledButton("Player vs Computer");
        pvc.addActionListener(e -> {
            gameCard.setMode(true);
            cards.show(this, "GAME");
        });
        gbc.gridy++;
        menuCard.add(pvc, gbc);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        return button;
    }

    private static class GameBoardPanel extends JPanel implements ActionListener {
        private JButton[] btn = new JButton[9];
        private boolean playerXTurn = true, vsComputer = false;
        private Random rand = new Random();

        public GameBoardPanel() {
            setLayout(new BorderLayout(5,5));
            setBackground(new Color(30, 30, 35));

            JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            top.setOpaque(false);
            JButton quit = createStyledButton("← Back");
            quit.addActionListener(e -> {
                Container parent = GameBoardPanel.this.getParent();
                if (parent instanceof JComponent) {
                    CardLayout cl = (CardLayout)((JComponent)parent).getLayout();
                    cl.show(parent, "MENU");
                }
            });
            top.add(quit);
            add(top, BorderLayout.NORTH);

            JPanel board = new JPanel(new GridLayout(3,3,5,5));
            board.setBackground(new Color(30, 30, 35));
            for (int i = 0; i < 9; i++) {
                btn[i] = createBoardButton();
                btn[i].addActionListener(this);
                board.add(btn[i]);
            }
            add(board, BorderLayout.CENTER);

            JButton reset = createStyledButton("Restart");
            reset.addActionListener(e -> resetBoard());
            JPanel bottom = new JPanel();
            bottom.setOpaque(false);
            bottom.add(reset);
            add(bottom, BorderLayout.SOUTH);
        }

        private JButton createBoardButton() {
            JButton button = new JButton("");
            button.setFont(new Font("Segoe UI", Font.BOLD, 60));
            button.setBackground(new Color(240, 240, 240));
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
            return button;
        }

        private JButton createStyledButton(String text) {
            JButton button = new JButton(text);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            return button;
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
                if (!playerXTurn) return;
                b.setText("X");
                b.setForeground(new Color(200, 50, 50));
                if (checkWin("X")) {
                    showWinMessage("Player wins!");
                    return;
                }
                if (isDraw()) {
                    showDrawMessage();
                    return;
                }
                playerXTurn = false;
                SwingUtilities.invokeLater(this::computerMove);
            } else {
                b.setText(playerXTurn ? "X" : "O");
                b.setForeground(playerXTurn ? new Color(200, 50, 50) : new Color(50, 50, 200));
                if (checkWin(playerXTurn ? "X" : "O")) {
                    showWinMessage((playerXTurn ? "Player X" : "Player O") + " wins!");
                    return;
                }
                if (isDraw()) {
                    showDrawMessage();
                    return;
                }
                playerXTurn = !playerXTurn;
            }
        }

        private void showWinMessage(String message) {
            JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        }

        private void showDrawMessage() {
            JOptionPane.showMessageDialog(this, "Draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();
        }

        private void computerMove() {
            if (tryWinOrBlock("O") || tryWinOrBlock("X")) {
                // Already moved in tryWinOrBlock
            } else {
                int i;
                do { i = rand.nextInt(9); }
                while (!btn[i].getText().isEmpty());
                btn[i].setText("O");
                btn[i].setForeground(new Color(50, 50, 200));
            }

            if (checkWin("O")) {
                showWinMessage("Computer wins!");
            } else if (isDraw()) {
                showDrawMessage();
            } else {
                playerXTurn = true;
            }
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
                    btn[L[2]].setText("O");
                    btn[L[2]].setForeground(new Color(50, 50, 200));
                    return true;
                }
                if (a.equals(m) && c.equals(m) && b.isEmpty()) {
                    btn[L[1]].setText("O");
                    btn[L[1]].setForeground(new Color(50, 50, 200));
                    return true;
                }
                if (b.equals(m) && c.equals(m) && a.isEmpty()) {
                    btn[L[0]].setText("O");
                    btn[L[0]].setForeground(new Color(50, 50, 200));
                    return true;
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
                b.setBackground(new Color(240, 240, 240));
            }
            playerXTurn = true;
        }
    }
}