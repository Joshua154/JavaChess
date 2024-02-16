package de.jonas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerInfoPanel extends JPanel {
    private JLabel pointsLabel;
    private JLabel turnLabel;
    private JLabel winLabel;
    private JPanel capturedPiecesPanel;
    private JPanel clockPanel;
    private JLabel chessClockLabel;
    private Timer chessClockTimer;
    private long timeLeftInMillis; // Time left in milliseconds

    private final long startTime = 5*60*1000; // 5 minutes
    private final long increment = 2*1000; // 2 seconds

    public PlayerInfoPanel(String playerName) {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder(playerName));
        setSize(200, 400);

        JPanel clockPanel = new JPanel();

        timeLeftInMillis = startTime;
        chessClockLabel = new JLabel(formatTime(timeLeftInMillis), SwingConstants.CENTER);

        clockPanel.add(chessClockLabel);

        JPanel labelsPanel = new JPanel(new GridLayout(3, 1));

        pointsLabel = new JLabel("Points: 0");
        pointsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        pointsLabel.setVerticalAlignment(SwingConstants.TOP);

        turnLabel = new JLabel("Turn: No");
        turnLabel.setHorizontalAlignment(SwingConstants.LEFT);
        turnLabel.setVerticalAlignment(SwingConstants.TOP);


        winLabel = new JLabel("Won: No");
        winLabel.setHorizontalAlignment(SwingConstants.LEFT);
        winLabel.setVerticalAlignment(SwingConstants.TOP);

        labelsPanel.add(pointsLabel);
        labelsPanel.add(turnLabel);
        labelsPanel.add(winLabel);


        capturedPiecesPanel = new JPanel();
        capturedPiecesPanel.setLayout(new FlowLayout());

        JScrollPane capturedPiecesScrollPane = new JScrollPane(capturedPiecesPanel);
        capturedPiecesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        capturedPiecesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        capturedPiecesScrollPane.setPreferredSize(new Dimension(200, 75));



        add(clockPanel);
        add(labelsPanel);
        add(capturedPiecesScrollPane);

        chessClockTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeftInMillis > 0) {
                    timeLeftInMillis -= 1000; // Decrease time by 1 second
                    updateClock();
                } else {
                    chessClockTimer.stop();
                }
            }
        });
    }

    public void updateClock() {
        chessClockLabel.setText(formatTime(timeLeftInMillis));
    }

    public void updatePoints(int points) {
        pointsLabel.setText("Points: " + points);
    }

    public void updateTurn(boolean isTurn) {
        turnLabel.setText("Turn: " + (isTurn ? "Yes" : "No"));
    }

    public void setWinner(boolean hasWon) {
        winLabel.setText("Won: " + (hasWon ? "Yes" : "No"));
    }

    public void addCapturedPiece(Icon pieceIcon) {
        JLabel pieceLabel = new JLabel(pieceIcon);
        capturedPiecesPanel.add(pieceLabel);
        capturedPiecesPanel.revalidate();
        capturedPiecesPanel.repaint();
    }

    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void startClock() {
        chessClockTimer.start();
    }

    public void pauseClock() {
        chessClockTimer.stop();
    }

    public void applyIncrement() {
        timeLeftInMillis += increment;
    }

    public void resetClock() {
        timeLeftInMillis = startTime;
        chessClockLabel.setText(formatTime(timeLeftInMillis));
    }

//    private void updateTimerLabel() {
//        int hours = elapsedTime / 3600;
//        int minutes = (elapsedTime % 3600) / 60;
//        int seconds = elapsedTime % 60;
//        timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
//    }
}
