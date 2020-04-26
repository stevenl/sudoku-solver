package io.stevenl.sudoku.core.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Cell {
    public static final Set<Integer> POSSIBLE_VALUES = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))
    );

    private int index;
    private int rowIndex;
    private int columnIndex;
    private int regionIndex;
    private int indexInRegion;
    private int value;

    public Cell(int index) {
        if (index < 0 || index >= Board.NR_CELLS) {
            throw new IllegalArgumentException("Invalid cell index: " + index);
        }

        this.index = index;
        this.rowIndex = index / Board.SIZE;
        this.columnIndex = index % Board.SIZE;

        int regionRow = rowIndex / Board.REGION_SIZE;
        int regionCol = columnIndex / Board.REGION_SIZE;
        this.regionIndex = regionRow * Board.REGION_SIZE + regionCol;

        int rowInRegion = rowIndex - regionRow * Board.REGION_SIZE;
        int columnInSquare = columnIndex - regionCol * Board.REGION_SIZE;
        this.indexInRegion = rowInRegion * Board.REGION_SIZE + columnInSquare;
    }

    public Cell(int index, int value) {
        this(index);
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRegionIndex() {
        return regionIndex;
    }

    public int getIndexInRegion() {
        return indexInRegion;
    }

    public void setValue(int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("Invalid cell value: " + value);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
