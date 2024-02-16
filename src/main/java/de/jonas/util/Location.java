package de.jonas.util;

public class Location {
    private int row;
    private int column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setPos(int x, int y) {
        this.column = y;
        this.row = x;
    }

    public void setPos(Location loc) {
        this.column = loc.getColumn();
        this.row = loc.getRow();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Location)) return false;
        Location loc = (Location) o;
        return this.column == loc.getColumn() && this.row == loc.getRow();
    }

    public boolean equals(int x, int y) {
        return this.column == x && this.row == y;
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    public String toChessNotation() {
        return "" + (char)('a' + column) + (char)('8' - row);
    }

    public static Location fromChessNotation(String notation) {
        return new Location(notation.charAt(0) - 'a', '8' - notation.charAt(1));
    }
}
