package de.jonas.pieces;

import de.jonas.ChessBoard;
import de.jonas.util.ImageTheme;
import de.jonas.util.Location;
import de.jonas.util.TeamColor;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    private ImageTheme theme;
    private boolean hasMoved = false;

    public King(ImageTheme theme, TeamColor teamColor) {
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

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!isLocationValid(chessBoard, new Location(row + x, column + y))) continue;
                if(board[row + x][column + y] == null){
                    locationsList.add(new Location(row + x, column + y));
                }
                else if(board[row + x][column + y].getColor() != getColor()){
                    locationsList.add(new Location(row + x, column + y));
                }
            }
        }

        return locationsList.stream().filter(location -> isLocationValid(chessBoard, location)).toList();
    }

    @Override
    public String getPieceName() {
        return "king";
    }

    @Override
    public void movedTo(Location newLocation) {
        hasMoved = true;
    }

    @Override
    public int getValue() {
        return 0;
    }
}