package de.jonas;

import de.jonas.pieces.ChessPiece;
import de.jonas.util.ImageTheme;

import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    private JFrame frame;
    private ImageTheme theme = ImageTheme.values()[0];
    private ChessBoard chessBoard;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ChessGUI()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize your chess board with pieces here
        ChessPiece[][] board = new ChessPiece[8][8];

        this.chessBoard = new ChessBoard(board, theme);
        frame.add(chessBoard, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

