package de.jonas.util;

import de.jonas.ChessGUI;
import de.jonas.pieces.ChessPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ImageTheme {
    WIKIPEDIA("Wikipedia", "Wikipedia"),
    SMOOTH("Smooth", "Smooth"),
    THREE_D("3D", "3D"),
    CHESS_COM("Chess_com", "Chess.com");

    private final String path;
    private final String themeName;

    ImageTheme(String path, String themeName) {
        this.path = path;
        this.themeName = themeName;
    }

    public String getPath() {
        return path;
    }

    public String getThemeName() {
        return themeName;
    }

    public Image getImage(ChessPiece chessPiece, TeamColor color) {
        String filePath = "/" + this.getPath() + "/" + chessPiece.getPieceName() + "_" + color.getImageColorString() + ".png";
        Image image = null;
        try {
            image = ImageIO.read(ChessGUI.class.getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
