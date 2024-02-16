package de.jonas.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUTIL {
    public static BufferedImage resize(Image image, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        newImage.createGraphics().drawImage(image, 0, 0, width, height, null);
        return newImage;
    }
}
