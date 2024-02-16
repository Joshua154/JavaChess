package de.jonas.pieces;

import de.jonas.ChessBoard;
import de.jonas.util.ImageTheme;
import de.jonas.util.Location;
import de.jonas.util.TeamColor;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {
    private ImageTheme theme;

    public Queen(ImageTheme theme, TeamColor teamColor, ChessBoard chessBoard) {
        super(teamColor, chessBoard);
        this.theme = theme;

        this.loadImage(theme, teamColor);
    }

    @Override
    public List<Location> getMoveLocations(ChessBoard chessBoard, Location currentLocation) {
        List<Location> locationsList = new ArrayList<>();
        ChessPiece[][] board = chessBoard.getBoard();

        int row = currentLocation.getRow();
        int column = currentLocation.getColumn();

        int[][] directions = new int[][]{{-1, -1}, {-1,0}, {-1, 1}, {1, -1}, {1,0}, {1, 1}, {0, 1}, {0, -1}};

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int x = row + dx;
            int y = column + dy;

            while (x >= 0 && x < board.length && y >= 0 && y < board[x].length) {
                Location location = new Location(x, y);

                if (!isLocationValid(chessBoard, location)) break; // Exit if location is invalid
                if (board[x][y] != null) {
                    if (board[x][y].getColor() != getColor()) {
                        locationsList.add(location);
                    }
                    break;
                }

                locationsList.add(location);

                // Move to the next cell in the direction
                x += dx;
                y += dy;
            }
        }

        return locationsList.stream().filter(location -> isLocationValid(chessBoard, location)).toList();
    }

    @Override
    public String getPieceName() {
        return "queen";
    }

    @Override
    public void movedTo(Location newLocation) {
        return;
    }

    @Override
    public int getValue() {
        return 9;
    }
}
