package de.jonas.pieces;

import de.jonas.ChessBoard;
import de.jonas.util.ImageTheme;
import de.jonas.util.Location;
import de.jonas.util.TeamColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends ChessPiece {
    public Bishop(ImageTheme theme, TeamColor teamColor, ChessBoard chessBoard) {
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

        int[][] directions = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int x = row + dx;
            int y = column + dy;

            while (x >= 0 && x < board.length && y >= 0 && y < board[x].length) {
                Location location = new Location(x, y);

                if (!isLocationValid(chessBoard, location)) break;
                if (board[x][y] != null) {
                    if (board[x][y].getColor() != getColor()) {
                        locationsList.add(location);
                    }
                    break;
                }

                locationsList.add(location);

                x += dx;
                y += dy;
            }
        }

        return locationsList.stream().filter(location -> isLocationValid(chessBoard, location)).collect(Collectors.toList());
    }

    @Override
    public String getPieceName() {
        return "bishop";
    }

    @Override
    public void movedTo(Location newLocation) {
        return;
    }

    @Override
    public int getValue() {
        return 3;
    }
}
