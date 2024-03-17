package de.jonas;

import de.jonas.pieces.*;
import de.jonas.util.ImageTheme;
import de.jonas.util.Location;
import de.jonas.util.TeamColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class ChessBoard extends JPanel {
    private ChessPiece[][] board;
    private Location selectedPieceLocation = null;
    private ChessPiece selectedPiece = null;
    private TeamColor bottomColor;
    private List<Location> possibleMoves;
    private JPanel boardGrid = new JPanel(new GridLayout(8, 8));
    private ChessBoard chessBoard = this;
    private TeamColor currentTurn = TeamColor.WHITE;
    private boolean isRunning = true;
    private int moveCount = 0;
    private ImageTheme theme;

    private int playerWhitePoints = 0;
    private int playerBlackPoints = 0;

    public ChessBoard(ChessPiece[][] board, ImageTheme theme) {
        this.theme = theme;
        this.board = board;
        setDefaultBoard(theme, TeamColor.WHITE);

        setLayout(new BorderLayout());
        addRanksAndFiles();
        this.setPreferredSize(new Dimension(400, 400));
        initializeBoard();
    }

    private void addRanksAndFiles() {
        JPanel topPanel = new JPanel(new GridLayout(1, 8));
        JPanel leftPanel = new JPanel(new GridLayout(8, 1));

        // Adding files (A-H) at the top
        for (char file = 'A'; file <= 'H'; file++) {
            topPanel.add(new JLabel(String.valueOf(file), SwingConstants.CENTER));
        }

        // Adding ranks (1-8) on the left
        for (int rank = 8; rank >= 1; rank--) {
            leftPanel.add(new JLabel(String.valueOf(rank), SwingConstants.CENTER));
        }

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(boardGrid, BorderLayout.CENTER); // Chessboard grid is added in the center
    }

    private void initializeBoard() {
        boardGrid.removeAll(); // Clear the board grid before initializing

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                final int row = i;
                final int col = j;
                JPanel square = new JPanel(new BorderLayout());
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                colorSquare(square, i, j); // Method to color the square

                JLabel pieceLabel = new JLabel();
                if (board[i][j] != null) {
                    pieceLabel.setIcon(new ImageIcon(board[i][j].getImage()));
                }
                square.add(pieceLabel);

                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(!isRunning) {
                            return;
                        }
                        if (selectedPieceLocation == null && board[row][col] != null) {
                            // Select piece
                            selectedPieceLocation = new Location(row, col);
                            selectedPiece = board[row][col];

                            if(selectedPiece.getColor() != currentTurn) {
                                selectedPieceLocation = null;
                                selectedPiece = null;
                                return;
                            }

                            possibleMoves = selectedPiece.getMoveLocations(chessBoard, selectedPieceLocation); // Get possible moves for the piece
                        } else if (selectedPieceLocation != null && (selectedPieceLocation.getRow() != row || selectedPieceLocation.getColumn() != col)) {
                            Location moveTo = new Location(row, col);
                            if (possibleMoves != null && possibleMoves.contains(moveTo)) {
                                if (board[row][col] != null) {
                                    ChessPiece piece = board[row][col];
                                    if(piece instanceof King) {
                                        isRunning = false;
                                    }
                                }

                                board[row][col] = board[selectedPieceLocation.getRow()][selectedPieceLocation.getColumn()];
                                board[selectedPieceLocation.getRow()][selectedPieceLocation.getColumn()] = null;
                                selectedPieceLocation = null;
                                possibleMoves = null;

                                currentTurn = currentTurn.getOpposite();
                                moveCount++;
                                selectedPiece.movedTo(moveTo);
                            }
                            else {
                                deselectPiece();
                            }
                        }
                        else {
                            deselectPiece();
                        }
                        updateBoard(); // Refresh the board
                    }
                });

                boardGrid.add(square);
            }
        }
    }

    private void deselectPiece() {
        // Deselect piece
        selectedPieceLocation = null;
        selectedPiece = null;
        possibleMoves = null;
    }

    private void colorSquare(JPanel square, int row, int col) {
        if(selectedPieceLocation != null && board[row][col] != null && board[row][col].getPieceName().equals("king")) {
            square.setBackground(new Color(120, 0, 0)); // Highlight King in check
        }
        if (selectedPieceLocation != null && row == selectedPieceLocation.getRow() && col == selectedPieceLocation.getColumn()) {
            square.setBackground(Color.YELLOW); // Highlight the selected piece
        } else if (possibleMoves != null && possibleMoves.contains(new Location(row, col))) {
            if(board[row][col] != null)
                square.setBackground(Color.RED); // Highlight possible captures
            else
                square.setBackground(Color.GREEN); // Highlight possible moves
        } else {
            if ((row + col) % 2 == 0) {
                square.setBackground(Color.WHITE);
            } else {
                square.setBackground(Color.GRAY);
            }
        }
    }


    private void setDefaultBoard(ImageTheme theme, TeamColor bottomColor) {
        this.bottomColor = bottomColor;

        board[0][0] = new Rook(theme, bottomColor.getOpposite(), this);
        board[0][1] = new Knight(theme, bottomColor.getOpposite(), this);
        board[0][2] = new Bishop(theme, bottomColor.getOpposite(), this);
        board[0][3] = new Queen(theme, bottomColor.getOpposite(), this);
        board[0][4] = new King(theme, bottomColor.getOpposite(), this);
        board[0][5] = new Bishop(theme, bottomColor.getOpposite(), this);
        board[0][6] = new Knight(theme, bottomColor.getOpposite(), this);
        board[0][7] = new Rook(theme, bottomColor.getOpposite(), this);

        board[1][0] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][1] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][2] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][3] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][4] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][5] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][6] = new Pawn(theme, bottomColor.getOpposite(), this);
        board[1][7] = new Pawn(theme, bottomColor.getOpposite(), this);


        board[7][0] = new Rook(theme, bottomColor, this);
        board[7][1] = new Knight(theme, bottomColor, this);
        board[7][2] = new Bishop(theme, bottomColor, this);
        board[7][3] = new Queen(theme, bottomColor, this);
        board[7][4] = new King(theme, bottomColor, this);
        board[7][5] = new Bishop(theme, bottomColor, this);
        board[7][6] = new Knight(theme, bottomColor, this);
        board[7][7] = new Rook(theme, bottomColor, this);

        board[6][0] = new Pawn(theme, bottomColor, this);
        board[6][1] = new Pawn(theme, bottomColor, this);
        board[6][2] = new Pawn(theme, bottomColor, this);
        board[6][3] = new Pawn(theme, bottomColor, this);
        board[6][4] = new Pawn(theme, bottomColor, this);
        board[6][5] = new Pawn(theme, bottomColor, this);
        board[6][6] = new Pawn(theme, bottomColor, this);
        board[6][7] = new Pawn(theme, bottomColor, this);
    }

    private void updateBoard() {
        initializeBoard();
        validate();
        repaint();
    }


    public ChessPiece[][] getBoard() {
        return board;
    }

    public TeamColor getBottomColor() {
        return bottomColor;
    }

    public void reloadAllPictures() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    ChessPiece piece = board[i][j];
                    piece.setTheme(theme);
                    piece.reloadImage();
                }
            }
        }
        updateBoard();
    }
}