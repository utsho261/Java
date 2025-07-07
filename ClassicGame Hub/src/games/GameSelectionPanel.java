package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameSelectionPanel extends JPanel {

    public GameSelectionPanel(JPanel cardPanel, CardLayout cardLayout) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 35));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Reduced padding

        // Title
        JLabel title = new JLabel("Game Selection Panel");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(new Color(255, 215, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Bottom margin increased for title
        add(title, gbc);

        // Panel for game buttons
        JPanel gamesPanel = new JPanel(new GridLayout(0, 2, 20, 20)); // 2 columns, 20px gaps
        gamesPanel.setOpaque(false);

        // Add game buttons
        addGameButton("❌⭕ Tic Tac Toe", cardPanel, cardLayout, "Tic Tac Toe", gamesPanel);
        addGameButton("🪨📃✂️ Rock Paper Scissors", cardPanel, cardLayout, "Rock Paper Scissors", gamesPanel);
        addGameButton("⚡🔥🌊 Pokemon Match", cardPanel, cardLayout, "Pokemon Match", gamesPanel);
        addGameButton("🐦 Flappy Bird", cardPanel, cardLayout, "Flappy Bird", gamesPanel);
        addGameButton("🐍 Snake Game", cardPanel, cardLayout, "Snake", gamesPanel);
        addGameButton("🟥🟧🟨 Breakout", cardPanel, cardLayout, "Breakout", gamesPanel);

        gbc.insets = new Insets(0, 0, 0, 0);
        add(gamesPanel, gbc);
    }

    private void addGameButton(String text, JPanel cardPanel, CardLayout layout, String cardName, JPanel parent) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setPreferredSize(new Dimension(250, 60)); // Wider but shorter buttons
        button.addActionListener(e -> layout.show(cardPanel, cardName));

        parent.add(button);
    }
}