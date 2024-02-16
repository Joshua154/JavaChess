package de.jonas;

import de.jonas.pieces.ChessPiece;

public class MoveHistoryEntry {
    private String description;
    private ChessPiece piece;

    public MoveHistoryEntry(String description, ChessPiece piece) {
        this.description = description;
        this.piece = piece;
    }

    public String getDescription() {
        return description;
    }

    public ChessPiece getPiece() {
        return piece;
    }
}
