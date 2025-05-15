package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameSelectionPanel extends JPanel {

    public GameSelectionPanel(JPanel cardPanel, CardLayout cardLayout) {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JButton ticTacToeBtn = new JButton("Tic Tac Toe");
        JButton rpsBtn = new JButton("Rock Paper Scissors");
        JButton pokemonBtn = new JButton("Pokemon Match");
        JButton flappyBirdBtn = new JButton("Flappy Bird");
        JButton snakeGame = new JButton("Snake");
        JButton breakoutBtn = new JButton("Breakout");
      //  JButton chessBtn = new JButton("Chess");


        // Action Listeners to switch cards
        ticTacToeBtn.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Tic Tac Toe"));
        rpsBtn.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Rock Paper Scissors"));
        pokemonBtn.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Pokemon Match"));
        flappyBirdBtn.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Flappy Bird"));
        snakeGame.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Snake"));
        breakoutBtn.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Breakout"));
      //  chessBtn.addActionListener((ActionEvent e) -> cardLayout.show(cardPanel, "Chess"));

        add(ticTacToeBtn);
        add(rpsBtn);
        add(pokemonBtn);
        add(flappyBirdBtn);
        add(snakeGame);
        add(breakoutBtn);
       // add(chessBtn);
    }
}
