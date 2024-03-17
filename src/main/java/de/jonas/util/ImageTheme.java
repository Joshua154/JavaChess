package de.jonas.util;

import de.jonas.ChessGUI;
import de.jonas.pieces.ChessPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ImageTheme {
    WIKIPEDIA("Wikipedia");

    private final String path;

    ImageTheme(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
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
