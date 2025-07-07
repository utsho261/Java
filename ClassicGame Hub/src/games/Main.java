package games;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ClassicGame Hub");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(850, 700);
            frame.setMinimumSize(new Dimension(850, 700));
            frame.setLocationRelativeTo(null);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);
            cardPanel.setBackground(new Color(30, 30, 35));

            GameSelectionPanel menuPanel = new GameSelectionPanel(cardPanel, cardLayout);

            // Game panels with consistent size
            JPanel ticTacToe = wrapWithBackButton(new TicTacToePanel(), cardPanel, cardLayout);
            JPanel rps = wrapWithBackButton(new RockPaperScissorsPanel(), cardPanel, cardLayout);
            JPanel pokemon = wrapWithBackButton(new PokemonMatchPanel(), cardPanel, cardLayout);
            JPanel flappyBird = wrapWithBackButton(new FlappyBirdPanel(), cardPanel, cardLayout);
            JPanel snakeGame = wrapWithBackButton(new SnakeGame(), cardPanel, cardLayout);
            JPanel breakoutGame = wrapWithBackButton(new BreakoutGame(), cardPanel, cardLayout);

            cardPanel.add(menuPanel, "Menu");
            cardPanel.add(ticTacToe, "Tic Tac Toe");
            cardPanel.add(rps, "Rock Paper Scissors");
            cardPanel.add(pokemon, "Pokemon Match");
            cardPanel.add(flappyBird, "Flappy Bird");
            cardPanel.add(snakeGame, "Snake");
            cardPanel.add(breakoutGame, "Breakout");

            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }

    private static JPanel wrapWithBackButton(JPanel gamePanel, JPanel cardPanel, CardLayout layout) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(30, 30, 35));
        wrapper.add(gamePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.setOpaque(false);

        JButton backButton = new JButton("← Back to Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        backButton.addActionListener(e -> layout.show(cardPanel, "Menu"));

        bottomPanel.add(backButton);
        wrapper.add(bottomPanel, BorderLayout.SOUTH);

        return wrapper;
    }
}