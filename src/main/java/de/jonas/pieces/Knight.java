package de.jonas.pieces;

import de.jonas.ChessBoard;
import de.jonas.util.ImageTheme;
import de.jonas.util.Location;
import de.jonas.util.TeamColor;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {
    private ImageTheme theme;

    public Knight(ImageTheme theme, TeamColor teamColor) {
        super(teamColor);
        this.theme = theme;

        this.loadImage(theme, teamColor);
    }

    @Override
    public List<Location> getMoveLocations(ChessBoard chessBoard, Location currentLocation) {
        List<Location> locationsList = new ArrayList<>();
        ChessPiece[][] board = chessBoard.getBoard();

        int row = currentLocation.getRow();
        int column = currentLocation.getColumn();

        int[][] directions = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int x = row + dx;
            int y = column + dy;

            Location location = new Location(x, y);

            if (!isLocationValid(chessBoard, location)) continue;
            if (board[x][y] != null) {
                if (board[x][y].getColor() == getColor()) {
                    continue;
                }
            }

            locationsList.add(location);
        }

        return locationsList.stream().filter(location -> isLocationValid(chessBoard, location)).toList();
    }

    @Override
    public String getPieceName() {
        return "knight";
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