package games;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class PokemonMatchPanel extends JPanel {
    private class Card {
        String name;
        ImageIcon iconFace;
        Card(String name, ImageIcon iconFace) {
            this.name = name;
            this.iconFace = iconFace;
        }
    }

    private static final String[] NAMES = {
            "darkness","double","fairy","fighting","fire",
            "grass","lightning","metal","psychic","water"
    };
    private static final int ROWS = 4, COLS = 5, CW = 90, CH = 128;

    private ArrayList<Card> deck;
    private ArrayList<JButton> buttons;
    private boolean[] matched;
    private ImageIcon backIcon;
    private Timer initialTimer;
    private Timer mismatchTimer;
    private JButton first, second;
    private boolean ready = false;
    private int errors = 0, matches = 0;

    private JLabel lblErrors = new JLabel("Errors: 0", SwingConstants.CENTER);
    private JLabel lblMatches = new JLabel("Matches: 0", SwingConstants.CENTER);
    private JButton btnRestart = new JButton("Restart Game");

    public PokemonMatchPanel() {
        setLayout(new BorderLayout(5,5));
        setBackground(new Color(30, 30, 35));

        JPanel top = new JPanel(new GridLayout(1,2));
        top.setOpaque(false);
        lblErrors.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblErrors.setForeground(new Color(255, 100, 100));
        lblMatches.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMatches.setForeground(new Color(100, 255, 100));
        top.add(lblErrors);
        top.add(lblMatches);
        add(top, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(ROWS, COLS, 5,5));
        grid.setPreferredSize(new Dimension(COLS*CW, ROWS*CH));
        grid.setBackground(new Color(40, 40, 45));

        setupDeck();
        matched = new boolean[deck.size()];
        buttons = new ArrayList<>();

        for (int i = 0; i < deck.size(); i++) {
            JButton b = new JButton();
            b.setIcon(deck.get(i).iconFace);
            b.setPreferredSize(new Dimension(CW,CH));
            b.setFocusable(false);
            b.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
            final int idx = i;
            b.addActionListener(e -> onCardClick(b, idx));
            buttons.add(b);
            grid.add(b);
        }
        add(grid, BorderLayout.CENTER);

        btnRestart = createStyledButton("Restart Game");
        btnRestart.setEnabled(false);
        btnRestart.addActionListener(e -> resetGame());
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(btnRestart);
        add(bottom, BorderLayout.SOUTH);

        initialTimer = new Timer(5000, e -> {
            initialTimer.stop();
            hideAll();
            ready = true;
            btnRestart.setEnabled(true);
        });
        initialTimer.setRepeats(false);

        mismatchTimer = new Timer(1000, e -> {
            mismatchTimer.stop();
            hideMismatch();
        });
        mismatchTimer.setRepeats(false);

        resetGame();
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

    private void setupDeck() {
        deck = new ArrayList<>();
        for (String nm: NAMES) {
            ImageIcon face = loadIcon("/pokemonimage/"+nm+".jpg");
            deck.add(new Card(nm, face));
        }
        deck.addAll(new ArrayList<>(deck));
        backIcon = createCardBackImage();
    }

    private ImageIcon createCardBackImage() {
        BufferedImage img = new BufferedImage(CW, CH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 70, 120), CW, CH, new Color(30, 30, 60));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, CW, CH);

        g2d.setColor(new Color(255, 255, 255, 30));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, 0, CW, CH);
        g2d.drawLine(CW, 0, 0, CH);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(CW/2-15, CH/2-15, 30, 30);
        g2d.setColor(Color.RED);
        g2d.fillArc(CW/2-15, CH/2-15, 30, 30, 0, 180);

        g2d.dispose();
        return new ImageIcon(img);
    }

    private ImageIcon loadIcon(String path) {
        Image img = new ImageIcon(getClass().getResource(path))
                .getImage()
                .getScaledInstance(CW, CH, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private void onCardClick(JButton b, int idx) {
        if (!ready || matched[idx] || b.getIcon() != backIcon) return;

        b.setIcon(deck.get(idx).iconFace);
        b.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        if (first == null) {
            first = b;
        } else if (second == null && b != first) {
            second = b;
            ready = false;

            int i1 = buttons.indexOf(first), i2 = idx;
            if (deck.get(i1).name.equals(deck.get(i2).name)) {
                matched[i1] = matched[i2] = true;
                matches++;
                lblMatches.setText("Matches: "+matches);

                first.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                second.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

                first = second = null;
                ready = true;

                if (matches == NAMES.length) {
                    JOptionPane.showMessageDialog(this, "You won with " + errors + " errors!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                errors++;
                lblErrors.setText("Errors: "+errors);
                mismatchTimer.restart();
            }
        }
    }

    private void hideAll() {
        for (int i = 0; i < buttons.size(); i++) {
            if (!matched[i]) {
                buttons.get(i).setIcon(backIcon);
                buttons.get(i).setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
            }
        }
    }

    private void hideMismatch() {
        if (first != null && second != null) {
            first.setIcon(backIcon);
            second.setIcon(backIcon);
            first.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
            second.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
        }
        first = second = null;
        ready = true;
    }

    private void resetGame() {
        shuffleAndDeal();
        initialTimer.restart();
    }

    private void shuffleAndDeal() {
        Collections.shuffle(deck);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIcon(deck.get(i).iconFace);
            buttons.get(i).setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 2));
            matched[i] = false;
        }
        errors = matches = 0;
        lblErrors.setText("Errors: 0");
        lblMatches.setText("Matches: 0");
        ready = false;
        first = second = null;
        btnRestart.setEnabled(false);
    }
}