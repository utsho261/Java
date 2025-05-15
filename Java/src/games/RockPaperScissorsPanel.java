package games;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RockPaperScissorsPanel extends JPanel {
    private JButton rock = new JButton("🪨 Rock");
    private JButton paper = new JButton("📃 Paper");
    private JButton scissors = new JButton("✂️ Scissors");
    private JLabel result  = new JLabel(" ", SwingConstants.CENTER);
    private JLabel score   = new JLabel("Wins: 0  Losses: 0  Draws: 0", SwingConstants.CENTER);

    private int wins = 0, losses = 0, draws = 0;
    private Random rand = new Random();

    public RockPaperScissorsPanel() {
        setLayout(new BorderLayout(5,5));
        JLabel title = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel choices = new JPanel();
        choices.add(rock); choices.add(paper); choices.add(scissors);
        add(choices, BorderLayout.CENTER);

        rock.addActionListener(e -> play(1));
        paper.addActionListener(e -> play(2));
        scissors.addActionListener(e -> play(3));

        JPanel south = new JPanel(new GridLayout(2,1,5,5));
        south.add(result);
        south.add(score);
        add(south, BorderLayout.SOUTH);
    }

    private void play(int yourChoice) {
        int comp = rand.nextInt(3) + 1;
        String[] name = { "", "Rock", "Paper", "Scissors" };
        String out;
        if (yourChoice == comp) {
            draws++; out = "Draw!";
        } else if ((yourChoice==1&&comp==3)||
                (yourChoice==2&&comp==1)||
                (yourChoice==3&&comp==2)) {
            wins++; out = "You Win!";
        } else {
            losses++; out = "Computer Wins!";
        }
        result.setText(
                String.format("You: %s  |  Computer: %s  →  %s",
                        name[yourChoice], name[comp], out));
        score.setText(
                String.format("Wins: %d  Losses: %d  Draws: %d", wins, losses, draws));
    }
}
