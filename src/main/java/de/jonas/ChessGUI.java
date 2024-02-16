package de.jonas;

import de.jonas.pieces.ChessPiece;
import de.jonas.util.ImageTheme;

import javax.swing.*;
import java.awt.*;

public class ChessGUI {
    private DefaultListModel<MoveHistoryEntry> moveHistoryModel;
    private JList<MoveHistoryEntry> moveHistoryList;
    private JFrame frame;
    private PlayerInfoPanel player1InfoPanel;
    private PlayerInfoPanel player2InfoPanel;
    private ImageTheme theme = ImageTheme.values()[0];
    private ChessBoard chessBoard;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ChessGUI()::createAndShowGUI);
    }

    private void createAndShowGUI() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel themePanel = new JPanel();
        themePanel.setLayout(new BoxLayout(themePanel, BoxLayout.Y_AXIS));
        themePanel.add(new JLabel("Select a theme:"));

        JScrollPane themeScrollPane = new JScrollPane();
        themeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        themeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        for (ImageTheme theme : ImageTheme.values()) {
            JButton themeButton = new JButton(theme.getThemeName());
            themeButton.addActionListener(e -> {
                this.theme = theme;
                chessBoard.setTheme(theme);
            });
            themePanel.add(themeButton);
        }


        // Player info panels
        player1InfoPanel = new PlayerInfoPanel("White");
        player1InfoPanel.updateTurn(true);
        player2InfoPanel = new PlayerInfoPanel("Black");

        leftPanel.add(themePanel);
        leftPanel.add(player1InfoPanel);
        leftPanel.add(player2InfoPanel);

        // Add left panel to the frame
        frame.add(leftPanel, BorderLayout.WEST);

        // Initialize your chess board with pieces here
        ChessPiece[][] board = new ChessPiece[8][8];

        this.chessBoard = new ChessBoard(board, player1InfoPanel, player2InfoPanel, theme);
        frame.add(chessBoard, BorderLayout.CENTER);

        // Create the move history list
        moveHistoryModel = new DefaultListModel<>();
        moveHistoryList = new JList<>(moveHistoryModel);
        moveHistoryList.setCellRenderer(new MoveHistoryCellRenderer());
        JScrollPane scrollPane = new JScrollPane(moveHistoryList);
        scrollPane.setPreferredSize(new Dimension(200, chessBoard.getPreferredSize().height));
        frame.add(scrollPane, BorderLayout.EAST);

        chessBoard.setMoveHistoryModel(moveHistoryModel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Custom cell renderer
    private static class MoveHistoryCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof MoveHistoryEntry) {
                MoveHistoryEntry entry = (MoveHistoryEntry) value;
                setText(entry.getDescription());
                setIcon(new ImageIcon(entry.getPiece().getImage()));
            }
            return this;
        }
    }
}

