package games;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RockPaperScissorsPanel extends JPanel {
    private JButton rock, paper, scissors;
    private JLabel result = new JLabel(" ", SwingConstants.CENTER);
    private JLabel score = new JLabel("Wins: 0  Losses: 0  Draws: 0", SwingConstants.CENTER);
    private int wins = 0, losses = 0, draws = 0;
    private Random rand = new Random();

    public RockPaperScissorsPanel() {
        setLayout(new BorderLayout(5,5));
        setBackground(new Color(30, 30, 35));
        setPreferredSize(new Dimension(800, 600)); // Fixed size

        JLabel title = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(255, 215, 0));
        add(title, BorderLayout.NORTH);

        createButtons(); // Initialize buttons with proper emojis

        JPanel choices = new JPanel();
        choices.setOpaque(false);
        choices.add(rock);
        choices.add(paper);
        choices.add(scissors);
        add(choices, BorderLayout.CENTER);

        JPanel south = new JPanel(new GridLayout(2,1,5,5));
        south.setOpaque(false);
        result.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        result.setForeground(Color.WHITE);
        score.setFont(new Font("Segoe UI", Font.BOLD, 18));
        score.setForeground(new Color(200, 200, 255));
        south.add(result);
        south.add(score);
        add(south, BorderLayout.SOUTH);
    }

    private void createButtons() {
        // Use proper Unicode emoji characters
        rock = createStyledButton("\uD83E\uDEA8 Rock", 1);  // Rock emoji
        paper = createStyledButton("\uD83D\uDCC3 Paper", 2); // Scroll/Paper emoji
        scissors = createStyledButton("\u2702\uFE0F Scissors", 3); // Scissors emoji

        // Set emoji font
        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 18);
        rock.setFont(emojiFont);
        paper.setFont(emojiFont);
        scissors.setFont(emojiFont);
    }

    private JButton createStyledButton(String text, int choice) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.addActionListener(e -> play(choice));
        return button;
    }

    private void play(int yourChoice) {
        int comp = rand.nextInt(3) + 1;
        String[] name = { "", "Rock", "Paper", "Scissors" };
        String out;

        if (yourChoice == comp) {
            draws++;
            out = "Draw!";
        } else if ((yourChoice==1&&comp==3) || (yourChoice==2&&comp==1) || (yourChoice==3&&comp==2)) {
            wins++;
            out = "You Win!";
        } else {
            losses++;
            out = "Computer Wins!";
        }

        result.setText(String.format("You: %s  |  Computer: %s  →  %s",
                name[yourChoice], name[comp], out));
        score.setText(String.format("Wins: %d  Losses: %d  Draws: %d", wins, losses, draws));

        // Add visual feedback
        Timer timer = new Timer(300, e -> {
            result.setForeground(yourChoice == comp ? Color.YELLOW :
                    out.equals("You Win!") ? Color.GREEN : Color.RED);
        });
        timer.setRepeats(false);
        timer.start();
    }
}