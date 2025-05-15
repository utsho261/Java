package games;

import javax.swing.*;
import java.awt.*;
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

        // Top: counters
        JPanel top = new JPanel(new GridLayout(1,2));
        lblErrors.setFont(new Font(null, Font.PLAIN, 16));
        lblMatches.setFont(new Font(null, Font.PLAIN, 16));
        top.add(lblErrors);
        top.add(lblMatches);
        add(top, BorderLayout.NORTH);

        // Center: grid
        JPanel grid = new JPanel(new GridLayout(ROWS, COLS, 5,5));
        grid.setPreferredSize(new Dimension(COLS*CW, ROWS*CH));

        setupDeck();
        matched = new boolean[deck.size()];
        buttons = new ArrayList<>();

        for (int i = 0; i < deck.size(); i++) {
            JButton b = new JButton();
            b.setIcon(deck.get(i).iconFace);
            b.setPreferredSize(new Dimension(CW,CH));
            b.setFocusable(false);
            final int idx = i;
            b.addActionListener(e -> onCardClick(b, idx));
            buttons.add(b);
            grid.add(b);
        }
        add(grid, BorderLayout.CENTER);

        // South: restart
        btnRestart.setEnabled(false);
        btnRestart.addActionListener(e -> resetGame());
        add(btnRestart, BorderLayout.SOUTH);

        // Timer: initial face-up for 10s, then hide all
        initialTimer = new Timer(10_000, e -> {
            initialTimer.stop();
            hideAll();
            ready = true;
            btnRestart.setEnabled(true);
        });
        initialTimer.setRepeats(false);

        // Timer: on mismatch, wait 3s then hide only those two
        mismatchTimer = new Timer(1_000, e -> {
            mismatchTimer.stop();
            hideMismatch();
        });
        mismatchTimer.setRepeats(false);

        // Start first deal
        resetGame();
    }

    private void setupDeck() {
        deck = new ArrayList<>();
        for (String nm: NAMES) {
            ImageIcon face = loadIcon("/pokemonimage/"+nm+".jpg");
            deck.add(new Card(nm, face));
        }
        deck.addAll(new ArrayList<>(deck)); // duplicate for pairs
        backIcon = loadIcon("/pokemonimage/back.jpg");
    }

    private void shuffleAndDeal() {
        Collections.shuffle(deck);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIcon(deck.get(i).iconFace);
            matched[i] = false;
        }
        errors = matches = 0;
        lblErrors.setText("Errors: 0");
        lblMatches.setText("Matches: 0");
        ready = false;
        first = second = null;
        btnRestart.setEnabled(false);
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

        if (first == null) {
            first = b;
        } else if (second == null && b != first) {
            second = b;
            ready = false;

            int i1 = buttons.indexOf(first), i2 = idx;
            if (deck.get(i1).name.equals(deck.get(i2).name)) {
                // match: keep face-up
                matched[i1] = matched[i2] = true;
                matches++;
                lblMatches.setText("Matches: "+matches);
                // reset and allow next picks immediately
                first = second = null;
                ready = true;
            } else {
                // mismatch: count error, then flip those two after 3s
                errors++;
                lblErrors.setText("Errors: "+errors);
                mismatchTimer.restart();
            }
        }
    }

    private void hideAll() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIcon(backIcon);
        }
    }

    private void hideMismatch() {
        if (first != null && second != null) {
            first.setIcon(backIcon);
            second.setIcon(backIcon);
        }
        first = second = null;
        ready = true;
    }

    private void resetGame() {
        shuffleAndDeal();
        initialTimer.restart();
    }
}