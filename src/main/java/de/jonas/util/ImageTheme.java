package de.jonas.util;

import de.jonas.ChessGUI;
import de.jonas.pieces.ChessPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ImageTheme {
    WIKIPEDIA("Wikipedia"),
    CHESS_COM("Chess_com");

    private final String themeName;

    ImageTheme(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }

    public Image getImage(ChessPiece chessPiece, TeamColor color) {
        String filePath = "/" + this.getThemeName() + "/" + chessPiece.getPieceName() + "_" + color.getImageColorString() + ".png";
        Image image = null;
        try {
            image = ImageIO.read(ChessGUI.class.getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
