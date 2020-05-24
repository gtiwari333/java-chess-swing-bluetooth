package chess;

public class King extends BasePiece {

    public boolean legalMove(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix) {

        finalDesRow = desRow;
        finalDesColumn = desColumn;

        if (desRow == (startRow + 1) && desColumn == startColumn) //S
        {
            return true;
        } else if (desRow == (startRow + 1) && desColumn == (startColumn - 1)) //SW
        {
            return true;
        } else if (desRow == startRow && desColumn == startColumn - 1) //W
        {
            return true;
        } else if (desRow == (startRow - 1) && desColumn == (startColumn - 1)) //NW
        {
            return true;
        } else if (desRow == (startRow - 1) && desColumn == startColumn) //N
        {
            return true;
        } else if (desRow == (startRow - 1) && desColumn == (startColumn + 1)) //NE
        {
            return true;
        } else if (desRow == startRow && desColumn == (startColumn + 1)) //E
        {
            return true;
        } else if (desRow == (startRow + 1) && desColumn == (startColumn + 1)) //SE
        {
            return true;
        } else {

            strErrorMsg = "King can only move one space in any direction";
            return false;

        }

    }

}