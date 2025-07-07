package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private final int TILE_SIZE = 25;
    private final int WIDTH = 30;
    private final int HEIGHT = 20;
    private final int DELAY = 150;

    private final LinkedList<Point> snake = new LinkedList<>();
    private final List<Point> foods = new LinkedList<>();
    private final int FOOD_COUNT = 3;

    private char direction = 'R';
    private boolean running = false;
    private Timer gameTimer;
    private Timer countdownTimer;

    private int score = 0;
    private int timeLeft = 60;
    private int highScore = 0;

    private JButton restartButton;
    private JButton startButton;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    private JLabel highScoreLabel;
    private JButton increaseTime;
    private JButton decreaseTime;

    public SnakeGame() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 35));
        setFocusable(true);
        addKeyListener(this);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(30, 30, 35));

        // Timer Panel
        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        timerPanel.setOpaque(false);

        timerLabel = new JLabel(formatTime(timeLeft));
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        timerLabel.setForeground(new Color(255, 100, 100));
        timerPanel.add(timerLabel);

        decreaseTime = createStyledButton("- Time");
        decreaseTime.addActionListener(e -> {
            if (timeLeft > 10) {
                timeLeft -= 10;
                timerLabel.setText(formatTime(timeLeft));
            }
        });
        timerPanel.add(decreaseTime);

        increaseTime = createStyledButton("+ Time");
        increaseTime.addActionListener(e -> {
            if (timeLeft < 300) {
                timeLeft += 10;
                timerLabel.setText(formatTime(timeLeft));
            }
        });
        timerPanel.add(increaseTime);

        startButton = createStyledButton("Start Game");
        startButton.addActionListener(e -> startGame());
        timerPanel.add(startButton);

        topPanel.add(timerPanel, BorderLayout.CENTER);

        // High Score
        highScoreLabel = new JLabel("High Score: 0", SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        highScoreLabel.setForeground(new Color(255, 215, 0));
        topPanel.add(highScoreLabel, BorderLayout.NORTH);

        add(topPanel, BorderLayout.NORTH);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(30, 30, 35));

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(100, 255, 100));
        bottomPanel.add(scoreLabel, BorderLayout.CENTER);

        restartButton = createStyledButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setEnabled(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(restartButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
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

    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void initGame() {
        snake.clear();
        foods.clear();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));
        spawnFoods();
        direction = 'R';
        running = true;
        score = 0;
        scoreLabel.setText("Score: 0");

        gameTimer = new Timer(DELAY, this);
        gameTimer.start();

        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText(formatTime(timeLeft));
            if (timeLeft <= 0) {
                running = false;
                countdownTimer.stop();
                gameTimer.stop();
                repaint();
            }
        });
        countdownTimer.start();

        requestFocusInWindow();
    }

    private void startGame() {
        increaseTime.setEnabled(false);
        decreaseTime.setEnabled(false);
        startButton.setEnabled(false);
        restartButton.setEnabled(true);
        initGame();
    }

    private void restartGame() {
        if (gameTimer != null) gameTimer.stop();
        if (countdownTimer != null) countdownTimer.stop();
        timeLeft = 60;
        timerLabel.setText(formatTime(timeLeft));
        startButton.setEnabled(true);
        increaseTime.setEnabled(true);
        decreaseTime.setEnabled(true);
        restartButton.setEnabled(false);
    }

    private void spawnFoods() {
        Random rand = new Random();
        while (foods.size() < FOOD_COUNT) {
            Point p = new Point(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
            if (!snake.contains(p) && !foods.contains(p)) {
                foods.add(p);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) return;

        Point head = new Point(snake.getFirst());

        switch (direction) {
            case 'U' -> head.y--;
            case 'D' -> head.y++;
            case 'L' -> head.x--;
            case 'R' -> head.x++;
        }

        // Wrap around
        if (head.x < 0) head.x = WIDTH - 1;
        if (head.x >= WIDTH) head.x = 0;
        if (head.y < 0) head.y = HEIGHT - 1;
        if (head.y >= HEIGHT) head.y = 0;

        if (snake.contains(head)) {
            gameOver();
            return;
        }

        snake.addFirst(head);

        boolean ate = false;
        for (int i = 0; i < foods.size(); i++) {
            if (head.equals(foods.get(i))) {
                foods.remove(i);
                score++;
                scoreLabel.setText("Score: " + score);

                if (score > highScore) {
                    highScore = score;
                    highScoreLabel.setText("High Score: " + highScore);
                }

                ate = true;
                break;
            }
        }

        if (!ate) {
            snake.removeLast();
        }

        spawnFoods();
        repaint();
    }

    private void gameOver() {
        running = false;
        gameTimer.stop();
        countdownTimer.stop();
        repaint();
    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
            setBackground(new Color(20, 20, 25));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw grid
            g2d.setColor(new Color(40, 40, 45));
            for (int i = 0; i < WIDTH; i++) {
                g2d.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT * TILE_SIZE);
            }
            for (int i = 0; i < HEIGHT; i++) {
                g2d.drawLine(0, i * TILE_SIZE, WIDTH * TILE_SIZE, i * TILE_SIZE);
            }

            // Draw snake with gradient
            for (int i = 0; i < snake.size(); i++) {
                Point p = snake.get(i);
                Color color = i == 0 ? new Color(0, 200, 200) :
                        new Color(0, 180 - (i * 2), 0);

                if (i == 0) {
                    // Head with eyes
                    g2d.setColor(color);
                    g2d.fillRoundRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, 10, 10);

                    // Eyes
                    g2d.setColor(Color.WHITE);
                    int eyeSize = TILE_SIZE / 4;
                    int eyeOffset = direction == 'L' || direction == 'R' ? TILE_SIZE/4 : TILE_SIZE/6;

                    if (direction == 'R') {
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/2, p.y * TILE_SIZE + eyeOffset, eyeSize, eyeSize);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/2, p.y * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize, eyeSize, eyeSize);
                    } else if (direction == 'L') {
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/4, p.y * TILE_SIZE + eyeOffset, eyeSize, eyeSize);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/4, p.y * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize, eyeSize, eyeSize);
                    } else if (direction == 'U') {
                        g2d.fillOval(p.x * TILE_SIZE + eyeOffset, p.y * TILE_SIZE + TILE_SIZE/4, eyeSize, eyeSize);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize, p.y * TILE_SIZE + TILE_SIZE/4, eyeSize, eyeSize);
                    } else { // DOWN
                        g2d.fillOval(p.x * TILE_SIZE + eyeOffset, p.y * TILE_SIZE + TILE_SIZE/2, eyeSize, eyeSize);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize, p.y * TILE_SIZE + TILE_SIZE/2, eyeSize, eyeSize);
                    }

                    // Pupils
                    g2d.setColor(Color.BLACK);
                    if (direction == 'R') {
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/2 + 2, p.y * TILE_SIZE + eyeOffset + 2, eyeSize/2, eyeSize/2);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/2 + 2, p.y * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize + 2, eyeSize/2, eyeSize/2);
                    } else if (direction == 'L') {
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/4 + 2, p.y * TILE_SIZE + eyeOffset + 2, eyeSize/2, eyeSize/2);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE/4 + 2, p.y * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize + 2, eyeSize/2, eyeSize/2);
                    } else if (direction == 'U') {
                        g2d.fillOval(p.x * TILE_SIZE + eyeOffset + 2, p.y * TILE_SIZE + TILE_SIZE/4 + 2, eyeSize/2, eyeSize/2);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize + 2, p.y * TILE_SIZE + TILE_SIZE/4 + 2, eyeSize/2, eyeSize/2);
                    } else { // DOWN
                        g2d.fillOval(p.x * TILE_SIZE + eyeOffset + 2, p.y * TILE_SIZE + TILE_SIZE/2 + 2, eyeSize/2, eyeSize/2);
                        g2d.fillOval(p.x * TILE_SIZE + TILE_SIZE - eyeOffset - eyeSize + 2, p.y * TILE_SIZE + TILE_SIZE/2 + 2, eyeSize/2, eyeSize/2);
                    }
                } else {
                    // Body segments
                    g2d.setColor(color);
                    g2d.fillRoundRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, 5, 5);
                }
            }

            // Draw foods
            g2d.setColor(Color.RED);
            for (Point f : foods) {
                g2d.fillOval(f.x * TILE_SIZE + 2, f.y * TILE_SIZE + 2, TILE_SIZE - 4, TILE_SIZE - 4);
                g2d.setColor(new Color(255, 100, 100));
                g2d.fillOval(f.x * TILE_SIZE + 4, f.y * TILE_SIZE + 4, TILE_SIZE - 8, TILE_SIZE - 8);
                g2d.setColor(Color.RED);
            }

            if (!running) {
                g2d.setColor(new Color(0, 0, 0, 180));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 48));

                String msg = (timeLeft <= 0) ? "Time's Up!" : "Game Over";
                g2d.drawString(msg, getWidth()/2 - g2d.getFontMetrics().stringWidth(msg)/2, getHeight()/2 - 50);

                g2d.setFont(new Font("Segoe UI", Font.BOLD, 36));
                msg = "Score: " + score;
                g2d.drawString(msg, getWidth()/2 - g2d.getFontMetrics().stringWidth(msg)/2, getHeight()/2 + 20);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        char newDir = direction;

        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != 'D') newDir = 'U';
        else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != 'U') newDir = 'D';
        else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != 'R') newDir = 'L';
        else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != 'L') newDir = 'R';

        direction = newDir;
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}