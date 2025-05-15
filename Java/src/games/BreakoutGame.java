package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BreakoutGame extends JPanel implements ActionListener, KeyListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 20;
    private final int BALL_SIZE = 15;
    private final int BRICK_WIDTH = 50;
    private final int BRICK_HEIGHT = 20;
    private final int NUM_ROWS = 5;
    private final int NUM_COLS = 10;
    private final int DELAY = 10;

    private Timer timer;
    private boolean running = false;
    private int score = 0;
    private int ballX, ballY, ballDX, ballDY;
    private int paddleX;
    private List<Brick> bricks;
    private List<Ball> balls;
    private JButton startButton;
    private JButton restartButton;
    private JLabel scoreLabel;

    public BreakoutGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(this);

        balls = new ArrayList<>();

        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT - 50;
        ballDX = 2;
        ballDY = -2;
        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;

        bricks = new ArrayList<>();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                int hitPoints = new Random().nextInt(3) + 1;
                bricks.add(new Brick(j * BRICK_WIDTH + 20, i * BRICK_HEIGHT + 50, BRICK_WIDTH, BRICK_HEIGHT, hitPoints));
            }
        }

        JPanel bottomPanel = new JPanel(new BorderLayout());
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(Color.BLUE);
        bottomPanel.add(scoreLabel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());
        bottomPanel.add(startButton, BorderLayout.WEST);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setEnabled(false);
        bottomPanel.add(restartButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        timer = new Timer(DELAY, this);
    }

    private void startGame() {
        running = true;
        score = 0;
        scoreLabel.setText("Score: " + score);
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT - 50;
        ballDX = 2;
        ballDY = -2;
        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;

        bricks.clear();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                int hitPoints = new Random().nextInt(3) + 1;
                bricks.add(new Brick(j * BRICK_WIDTH + 20, i * BRICK_HEIGHT + 50, BRICK_WIDTH, BRICK_HEIGHT, hitPoints));
            }
        }

        balls = new ArrayList<>();
        balls.add(new Ball(ballX, ballY, ballDX, ballDY));

        timer.start();
        startButton.setEnabled(false);
        restartButton.setEnabled(true);
        requestFocusInWindow();
    }

    private void restartGame() {
        startGame();
        scoreLabel.setText("Score: " + score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) return;

        for (Ball ball : balls) {
            ball.x += ball.dx;
            ball.y += ball.dy;

            if (ball.x <= 0 || ball.x >= WIDTH - BALL_SIZE) ball.dx = -ball.dx;
            if (ball.y <= 0) ball.dy = -ball.dy;

            if (ball.y + BALL_SIZE >= HEIGHT - PADDLE_HEIGHT - 10 &&
                    ball.y + BALL_SIZE <= HEIGHT - 10 &&
                    ball.x + BALL_SIZE >= paddleX &&
                    ball.x <= paddleX + PADDLE_WIDTH) {
                ball.dy = -ball.dy;
                score++;
                scoreLabel.setText("Score: " + score);
            }

            if (ball.y >= HEIGHT) {
                running = false;
                timer.stop();
                scoreLabel.setText("Game Over! Score: " + score);
                repaint();
            }

            for (Brick brick : bricks) {
                if (brick.rect.intersects(new Rectangle(ball.x, ball.y, BALL_SIZE, BALL_SIZE))) {
                    ball.dy = -ball.dy;
                    brick.hitPoints--;
                    if (brick.hitPoints <= 0) {
                        bricks.remove(brick);
                        score += 10;
                        scoreLabel.setText("Score: " + score);
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

        g.setColor(Color.RED);
        for (Ball ball : balls) {
            g.fillOval(ball.x, ball.y, BALL_SIZE, BALL_SIZE);
        }

        g.setColor(Color.BLUE);
        g.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT - 10, PADDLE_WIDTH, PADDLE_HEIGHT);

        for (Brick brick : bricks) {
            g.setColor(new Color(255 - (brick.hitPoints * 50), 100, 100));
            g.fillRect(brick.rect.x, brick.rect.y, brick.rect.width, brick.rect.height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(brick.hitPoints), brick.rect.x + brick.rect.width / 4, brick.rect.y + brick.rect.height / 2);
        }

        if (!running) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

    @Override public void keyPressed(KeyEvent e) {
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

    // ✅ Main method to create the frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Breakout Game");
            BreakoutGame gamePanel = new BreakoutGame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(gamePanel);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
