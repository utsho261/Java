package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BreakoutGame extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 850;  // Increased width
    private final int HEIGHT = 550; // Increased height
    private final int PADDLE_WIDTH = 120;
    private final int PADDLE_HEIGHT = 20;
    private final int BALL_SIZE = 20;
    private final int BRICK_WIDTH = 80;  // Wider bricks
    private final int BRICK_HEIGHT = 30; // Taller bricks
    private final int NUM_ROWS = 5;
    private final int NUM_COLS = 10;
    private final int DELAY = 10;

    private Timer timer;
    private boolean running = false;
    private int score = 0;
    private int highScore = 0;
    private int ballX, ballY, ballDX, ballDY;
    private int paddleX;
    private List<Brick> bricks;
    private List<Ball> balls;
    private JButton startButton;
    private JButton restartButton;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;

    public BreakoutGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 35));
        setFocusable(true);
        addKeyListener(this);

        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        resetGameState();

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(30, 30, 35));

        // Score display
        JPanel scorePanel = new JPanel(new GridLayout(2, 1));
        scorePanel.setOpaque(false);

        highScoreLabel = new JLabel("High Score: 0", SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        highScoreLabel.setForeground(new Color(255, 215, 0));
        scorePanel.add(highScoreLabel);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(100, 255, 100));
        scorePanel.add(scoreLabel);

        bottomPanel.add(scorePanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        startButton = createStyledButton("Start");
        startButton.addActionListener(e -> startGame());
        buttonPanel.add(startButton);

        restartButton = createStyledButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setEnabled(false);
        buttonPanel.add(restartButton);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        timer = new Timer(DELAY, this);
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

    private void resetGameState() {
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT - 50;
        ballDX = 2;
        ballDY = -2;
        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;

        bricks.clear();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                int hitPoints = new Random().nextInt(3) + 1;
                bricks.add(new Brick(
                        j * BRICK_WIDTH + 20,
                        i * BRICK_HEIGHT + 50,
                        BRICK_WIDTH,
                        BRICK_HEIGHT,
                        hitPoints
                ));
            }
        }

        balls = new ArrayList<>();
        balls.add(new Ball(ballX, ballY, ballDX, ballDY));
    }

    private void startGame() {
        running = true;
        score = 0;
        scoreLabel.setText("Score: " + score);
        resetGameState();
        timer.start();
        startButton.setEnabled(false);
        restartButton.setEnabled(true);
        requestFocusInWindow();
    }

    private void restartGame() {
        if (timer != null) timer.stop();
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) return;

        for (Ball ball : balls) {
            ball.x += ball.dx;
            ball.y += ball.dy;

            if (ball.x <= 0 || ball.x >= WIDTH - BALL_SIZE) ball.dx = -ball.dx;
            if (ball.y <= 0) ball.dy = -ball.dy;

            // Paddle collision
            if (ball.y + BALL_SIZE >= HEIGHT - PADDLE_HEIGHT - 10 &&
                    ball.y + BALL_SIZE <= HEIGHT - 10 &&
                    ball.x + BALL_SIZE >= paddleX &&
                    ball.x <= paddleX + PADDLE_WIDTH) {
                ball.dy = -ball.dy;
                score++;
                scoreLabel.setText("Score: " + score);

                if (score > highScore) {
                    highScore = score;
                    highScoreLabel.setText("High Score: " + highScore);
                }
            }

            if (ball.y >= HEIGHT) {
                running = false;
                timer.stop();
                scoreLabel.setText("Game Over! Score: " + score);
                repaint();
                return;
            }

            // Brick collision
            for (Brick brick : bricks) {
                if (brick.rect.intersects(new Rectangle(ball.x, ball.y, BALL_SIZE, BALL_SIZE))) {
                    ball.dy = -ball.dy;
                    brick.hitPoints--;
                    if (brick.hitPoints <= 0) {
                        bricks.remove(brick);
                        score += 10;
                        scoreLabel.setText("Score: " + score);

                        if (score > highScore) {
                            highScore = score;
                            highScoreLabel.setText("High Score: " + highScore);
                        }
                    }
                    break;
                }
            }
        }

        if (bricks.isEmpty()) {
            running = false;
            timer.stop();
            scoreLabel.setText("You Win! Score: " + score);
            repaint();
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw background grid
        g2d.setColor(new Color(40, 40, 45));
        for (int i = 0; i < WIDTH; i += 20) {
            g2d.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i < HEIGHT; i += 20) {
            g2d.drawLine(0, i, WIDTH, i);
        }

        // Draw paddle with shadow
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(paddleX + 3, HEIGHT - PADDLE_HEIGHT - 7, PADDLE_WIDTH, PADDLE_HEIGHT);

        GradientPaint paddleGradient = new GradientPaint(
                paddleX, HEIGHT - PADDLE_HEIGHT - 10, new Color(70, 130, 180),
                paddleX, HEIGHT - 10, new Color(50, 110, 160)
        );
        g2d.setPaint(paddleGradient);
        g2d.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT - 10, PADDLE_WIDTH, PADDLE_HEIGHT);

        // Draw bricks
        for (Brick brick : bricks) {
            // Shadow
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillRect(brick.rect.x + 2, brick.rect.y + 2, brick.rect.width, brick.rect.height);

            // Brick with gradient based on hit points
            Color topColor = switch (brick.hitPoints) {
                case 3 -> new Color(255, 100, 100); // Red
                case 2 -> new Color(255, 200, 100); // Orange
                default -> new Color(100, 255, 100); // Green
            };
            Color bottomColor = topColor.darker();
            GradientPaint gradient = new GradientPaint(
                    brick.rect.x, brick.rect.y, topColor,
                    brick.rect.x, brick.rect.y + brick.rect.height, bottomColor
            );
            g2d.setPaint(gradient);
            g2d.fillRect(brick.rect.x, brick.rect.y, brick.rect.width, brick.rect.height);

            // Border
            g2d.setColor(Color.BLACK);
            g2d.drawRect(brick.rect.x, brick.rect.y, brick.rect.width, brick.rect.height);

            // Hit points
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 14));
            String hpText = String.valueOf(brick.hitPoints);
            g2d.drawString(hpText,
                    brick.rect.x + brick.rect.width/2 - g2d.getFontMetrics().stringWidth(hpText)/2,
                    brick.rect.y + brick.rect.height/2 + 5
            );
        }

        // Draw balls with shine effect
        for (Ball ball : balls) {
            // Shadow
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillOval(ball.x + 3, ball.y + 3, BALL_SIZE, BALL_SIZE);

            // Ball with gradient
            GradientPaint gradient = new GradientPaint(
                    ball.x, ball.y, Color.RED,
                    ball.x + BALL_SIZE, ball.y + BALL_SIZE, Color.ORANGE
            );
            g2d.setPaint(gradient);
            g2d.fillOval(ball.x, ball.y, BALL_SIZE, BALL_SIZE);

            // Highlight
            g2d.setColor(new Color(255, 255, 255, 100));
            g2d.fillOval(ball.x + BALL_SIZE/4, ball.y + BALL_SIZE/4, BALL_SIZE/3, BALL_SIZE/3);
        }

        if (!running) {
            // Game over overlay
            g2d.setColor(new Color(0, 0, 0, 180));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 48));
            String msg = bricks.isEmpty() ? "You Win!" : "Game Over";
            g2d.drawString(msg, getWidth()/2 - g2d.getFontMetrics().stringWidth(msg)/2, getHeight()/2 - 50);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 36));
            msg = "Score: " + score;
            g2d.drawString(msg, getWidth()/2 - g2d.getFontMetrics().stringWidth(msg)/2, getHeight()/2 + 20);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddleX > 0) paddleX -= 20;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddleX < WIDTH - PADDLE_WIDTH) paddleX += 20;
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    class Ball {
        int x, y, dx, dy;
        Ball(int x, int y, int dx, int dy) {
            this.x = x; this.y = y; this.dx = dx; this.dy = dy;
        }
    }

    class Brick {
        Rectangle rect;
        int hitPoints;
        Brick(int x, int y, int width, int height, int hitPoints) {
            this.rect = new Rectangle(x, y, width, height);
            this.hitPoints = hitPoints;
        }
    }
}