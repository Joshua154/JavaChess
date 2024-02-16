package de.jonas.pieces;

import de.jonas.ChessBoard;
import de.jonas.util.ImageTheme;
import de.jonas.util.ImageUTIL;
import de.jonas.util.TeamColor;
import de.jonas.util.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ChessPiece {
    private Image image;
    private TeamColor teamColor;
    protected ChessBoard chessBoard;
    protected ImageTheme theme;

    public ChessPiece(TeamColor teamColor, ChessBoard chessBoard) {
        this.teamColor = teamColor;
        this.chessBoard = chessBoard;
    }

    protected void loadImage(ImageTheme theme, TeamColor color) {
        Image img = theme.getImage(this, color);
        img = ImageUTIL.resize(img, 55, 55);
        this.image = new ImageIcon(img).getImage();
    }

    public Image getImage() {
        return image;
    }

    public TeamColor getColor() {
        return teamColor;
    }

    protected boolean isLocationValid(ChessBoard chessBoard, Location location) {
        return location.getRow() >= 0 && location.getRow() < chessBoard.getBoard().length && location.getColumn() >= 0 && location.getColumn() < chessBoard.getBoard().length;
    }

    public abstract List<Location> getMoveLocations(ChessBoard chessBoard, Location currentLocation);
    public abstract String getPieceName();
    public abstract void movedTo(Location newLocation);
    public boolean isInTeam(TeamColor teamColor) {
        return this.teamColor == teamColor;
    }
    public abstract int getValue();
    public void reloadImage() {
        this.loadImage(this.theme, teamColor);
    }
    public void setTheme(ImageTheme theme) {
        this.theme = theme;
    }
}
