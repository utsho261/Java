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
    private int timeLeft = 60; // Default 60 seconds

    private JButton restartButton;
    private JButton startButton;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    private JButton increaseTime;
    private JButton decreaseTime;

    public SnakeGame() {
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(this);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setEnabled(false); // Initially disabled
        bottomPanel.add(restartButton, BorderLayout.WEST);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(Color.BLUE);
        bottomPanel.add(scoreLabel, BorderLayout.EAST);

        // Top Timer Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timerLabel = new JLabel(formatTime(timeLeft));
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);
        topPanel.add(timerLabel);

        // Time adjust buttons
        increaseTime = new JButton("+ Time");
        increaseTime.addActionListener(e -> {
            if (timeLeft < 300) {  // Max time 5 minutes
                timeLeft += 10;
                timerLabel.setText(formatTime(timeLeft));
            }
        });

        decreaseTime = new JButton("- Time");
        decreaseTime.addActionListener(e -> {
            if (timeLeft > 10) {  // Min time 10 seconds
                timeLeft -= 10;
                timerLabel.setText(formatTime(timeLeft));
            }
        });

        topPanel.add(decreaseTime);
        topPanel.add(increaseTime);

        // Start Button to initiate game
        startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());
        topPanel.add(startButton);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Score live update
        Timer scoreUpdater = new Timer(100, e -> scoreLabel.setText("Score: " + score));
        scoreUpdater.start();
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
        // Disable time change and start button once the game starts
        increaseTime.setEnabled(false);
        decreaseTime.setEnabled(false);
        startButton.setEnabled(false);
        restartButton.setEnabled(true);

        initGame();
    }

    private void restartGame() {
        if (gameTimer != null) gameTimer.stop();
        if (countdownTimer != null) countdownTimer.stop();
        timeLeft = 60;  // Reset to default time (can be changed later)
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
            running = false;
            gameTimer.stop();
            countdownTimer.stop();
            repaint();
            return;
        }

        snake.addFirst(head);

        boolean ate = false;
        for (int i = 0; i < foods.size(); i++) {
            if (head.equals(foods.get(i))) {
                foods.remove(i);
                score++;
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

    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw snake
            for (int i = 0; i < snake.size(); i++) {
                Point p = snake.get(i);
                g.setColor(i == 0 ? Color.CYAN : Color.GREEN); // Head is Cyan
                g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            // Draw foods
            g.setColor(Color.RED);
            for (Point f : foods) {
                g.fillOval(f.x * TILE_SIZE, f.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

            if (!running) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                String msg = (timeLeft <= 0) ? "Time's Up!" : "Game Over";
                g.drawString(msg, getWidth() / 2 - 120, getHeight() / 2);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char newDir = switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> 'U';
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> 'D';
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> 'L';
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> 'R';
            default -> direction;
        };

        if ((direction == 'U' && newDir != 'D') ||
                (direction == 'D' && newDir != 'U') ||
                (direction == 'L' && newDir != 'R') ||
                (direction == 'R' && newDir != 'L')) {
            direction = newDir;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
