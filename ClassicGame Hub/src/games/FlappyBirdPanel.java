package games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdPanel extends JPanel implements ActionListener, KeyListener {
    private final int panelWidth = 850;
    private final int panelHeight = 600;
    private final int pipeWidth = 80;
    private final int pipeHeight = 400;
    private final int birdX = panelWidth / 8;
    private final int birdY = panelHeight / 2;
    private final int birdWidth = 50;
    private final int birdHeight = 35;

    private Image backgroundImg, birdImg, topPipeImg, bottomPipeImg;

    private class Bird {
        int x = birdX, y = birdY;
        int width = birdWidth, height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
    }

    private class Pipe {
        int x = panelWidth;
        int y;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img, int y, boolean isTop) {
            this.y = y;
            this.img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
    }

    private Bird bird;
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private final Random random = new Random();

    private Timer gameLoop, placePipeTimer;
    private boolean gameOver = false;
    private double score = 0;
    private int velocityX = -5;
    private int velocityY = 0;
    private final int gravity = 1;

    private JPanel gamePanel;
    private JButton restartButton;

    public FlappyBirdPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        gamePanel = new GameCanvas();
        gamePanel.setPreferredSize(new Dimension(panelWidth, panelHeight - 50));
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);

        restartButton = new JButton("Restart Game");
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> restartGame());

        add(gamePanel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        loadImages();
        initGame();
    }

    private void loadImages() {
        backgroundImg = new ImageIcon(getClass().getResource("/flappybirdimage/flappybirdbg.png")).getImage()
                .getScaledInstance(panelWidth, panelHeight - 50, Image.SCALE_SMOOTH);
        birdImg = new ImageIcon(getClass().getResource("/flappybirdimage/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/flappybirdimage/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/flappybirdimage/bottompipe.png")).getImage();
    }

    private void initGame() {
        bird = new Bird(birdImg);
        pipes.clear();
        score = 0;
        velocityY = 0;
        gameOver = false;

        gameLoop = new Timer(1000 / 60, this);
        placePipeTimer = new Timer(1500, e -> placePipes());

        gameLoop.start();
        placePipeTimer.start();

        gamePanel.requestFocusInWindow();
    }

    private void restartGame() {
        gameLoop.stop();
        placePipeTimer.stop();
        initGame();
    }

    private void placePipes() {
        int randomY = -pipeHeight / 2 - random.nextInt(pipeHeight / 2);
        int opening = panelHeight / 3;

        pipes.add(new Pipe(topPipeImg, randomY, true));
        pipes.add(new Pipe(bottomPipeImg, randomY + pipeHeight + opening, false));
    }

    private void move() {
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > panelHeight - 50) {
            gameOver = true;
        }

        if (gameOver) {
            gameLoop.stop();
            placePipeTimer.stop();
        }
    }

    private boolean collision(Bird b, Pipe p) {
        return b.x < p.x + p.width &&
                b.x + b.width > p.x &&
                b.y < p.y + p.height &&
                b.y + b.height > p.y;
    }

    private class GameCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImg, 0, 0, null);
            g.drawImage(bird.img, bird.x, bird.y, null);
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.img, pipe.x, pipe.y, null);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            if (gameOver) {
                g.drawString("Game Over: " + (int) score, 300, 100);
            } else {
                g.drawString("Score: " + (int) score, 10, 40);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        gamePanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                restartGame();
            } else {
                velocityY = -10;
            }
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
