package games;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Game Hub");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);


            // Main menu panel
            GameSelectionPanel menuPanel = new GameSelectionPanel(cardPanel, cardLayout);

            // Game panels wrapped with back button
            JPanel ticTacToe = wrapWithBackButton(new TicTacToePanel(), cardPanel, cardLayout);
            JPanel rps = wrapWithBackButton(new RockPaperScissorsPanel(), cardPanel, cardLayout);
            JPanel pokemon = wrapWithBackButton(new PokemonMatchPanel(), cardPanel, cardLayout);
            JPanel flappyBird = wrapWithBackButton(new FlappyBirdPanel(), cardPanel, cardLayout);
            JPanel snakeGame = wrapWithBackButton(new SnakeGame(), cardPanel, cardLayout);
            JPanel breakoutGame = wrapWithBackButton(new BreakoutGame(), cardPanel, cardLayout);
           // JPanel chessPanel = wrapWithBackButton(new ChessPanel(), cardPanel, cardLayout);

            cardPanel.add(menuPanel, "Menu");
            cardPanel.add(ticTacToe, "Tic Tac Toe");
            cardPanel.add(rps, "Rock Paper Scissors");
            cardPanel.add(pokemon, "Pokemon Match");
            cardPanel.add(flappyBird, "Flappy Bird");
            cardPanel.add(snakeGame, "Snake");
            cardPanel.add(breakoutGame, "Breakout");
            //cardPanel.add(chessPanel, "Chess");

            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }

    private static JPanel wrapWithBackButton(JPanel gamePanel, JPanel cardPanel, CardLayout layout) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(gamePanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> layout.show(cardPanel, "Menu"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);

        wrapper.add(bottomPanel, BorderLayout.SOUTH);
        return wrapper;
    }
}
