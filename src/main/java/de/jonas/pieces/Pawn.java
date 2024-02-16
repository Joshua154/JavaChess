package de.jonas.pieces;

import de.jonas.ChessBoard;
import de.jonas.util.ImageTheme;
import de.jonas.util.TeamColor;
import de.jonas.util.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends ChessPiece {
    private boolean hasMoved = false;
    private ImageTheme theme;

    public Pawn(ImageTheme theme, TeamColor teamColor, ChessBoard chessBoard) {
        super(teamColor, chessBoard);
        this.theme = theme;

        this.loadImage(theme, teamColor);
    }

    @Override
    public List<Location> getMoveLocations(ChessBoard chessBoard, Location currentLocation) {
        List<Location> locationsList = new ArrayList<>();
        ChessPiece[][] board = chessBoard.getBoard();

        int direction = (getColor() == chessBoard.getBottomColor()) ? -1 : 1;
        int nextRow = currentLocation.getRow() + direction;
        int column = currentLocation.getColumn(); // Assuming the correct method name is getColumn()

// move forward
        if (nextRow >= 0 && nextRow < board.length && board[nextRow][column] == null) {
            locationsList.add(new Location(nextRow, column));
            // Double move for pawn that hasn't moved
            int twoStepsForward = nextRow + direction;
            if (!hasMoved && twoStepsForward >= 0 && twoStepsForward < board.length && board[twoStepsForward][column] == null) {
                locationsList.add(new Location(twoStepsForward, column));
            }
        }

// capture moves to the right
        if (column + 1 < board[0].length && board[nextRow][column + 1] != null &&
                board[nextRow][column + 1].isInTeam(getColor().getOpposite())) {
            locationsList.add(new Location(nextRow, column + 1));
        }

// capture moves to the left
        if (column - 1 >= 0 && board[nextRow][column - 1] != null &&
                board[nextRow][column - 1].isInTeam(getColor().getOpposite())) {
            locationsList.add(new Location(nextRow, column - 1));
        }

// Filter out invalid locations
        return locationsList.stream().filter(location -> isLocationValid(chessBoard, location)).collect(Collectors.toList());
    }

    @Override
    public String getPieceName() {
        return "pawn";
    }

    @Override
    public void movedTo(Location newLocation) {
        hasMoved = true;
        if (newLocation.getRow() == 0 || newLocation.getRow() == 7) {
            this.chessBoard.getBoard()[newLocation.getColumn()][newLocation.getRow()] = new Queen(this.theme, this.getColor(), this.chessBoard);
        }
    }

    @Override
    public int getValue() {
        return 1;
    }
}
