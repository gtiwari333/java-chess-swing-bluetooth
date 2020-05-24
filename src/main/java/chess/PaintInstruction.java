package chess;

/******
 * class for finding minimum row and columns that needed to be paint currently to avoid flickering
 * instead of painting all the rows and columns 8*8 and their content piece with background of cell
 * it is better to draw minimum or adjacent cells only
 * it is used in DESKTOP version only,
 * first the current row,cols are stored in vector & then ...
 */

public class PaintInstruction {

    private int startRow = 0, startColumn = 0, rowCells = 0, columnCells = 0;

    public PaintInstruction(int firstRow, int firstColumn) {
        calculateRedrawCells(firstRow, firstColumn);
    }

    public PaintInstruction(int startRow, int startColumn, int numCells) {

        this.startRow = startRow;
        this.startColumn = startColumn;
        rowCells = numCells;
        columnCells = numCells;

    }

    private void calculateRedrawCells(int firstRow, int firstColumn) {
        //adjust or calculate the row and column of a cell that are needed to be drawn
        if (firstRow == 0) {
            this.startRow = firstRow;
        } else {
            this.startRow = firstRow - 1;
        }

        if (firstColumn == 0) {
            this.startColumn = firstColumn;
        } else {
            this.startColumn = firstColumn - 1;
        }

        if (firstRow <= 5) {
            rowCells = 3;
        } else {
            rowCells = 8 - startRow;
        }

        if (firstColumn <= 5) {
            columnCells = 3;
        } else {
            columnCells = 8 - startColumn;
        }

    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getRowCells() {
        return rowCells;
    }

    public int getColumnCells() {
        return columnCells;
    }
}
