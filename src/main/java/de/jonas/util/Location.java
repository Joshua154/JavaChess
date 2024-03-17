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

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Location)) return false;
        Location loc = (Location) o;
        return this.column == loc.getColumn() && this.row == loc.getRow();
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
