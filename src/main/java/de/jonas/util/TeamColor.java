package de.jonas.util;

public enum TeamColor {
    BLACK,
    WHITE;

    public TeamColor getOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public String toString() {
        if (this == BLACK) {
            return "Black";
        }
        return "White";
    }

    public String getImageColorString() {
        return this.toString().toLowerCase();
    }
}
