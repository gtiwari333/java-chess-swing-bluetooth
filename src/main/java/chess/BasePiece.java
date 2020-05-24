package chess;

/*********
 * class which contains a function checkMoveStraightDiagonal() which has shared (protected) variables
 * finalDesRow, finalDesColumn & strErrorMsg
 * this class is extended in all pieces classes-> [Piece].java
 */
abstract class BasePiece {

    protected int finalDesRow = 0;
    protected int finalDesColumn = 0;
    protected String strErrorMsg = "";

    //Method for checking the path to the destination and making sure nothing is in the way

    protected boolean checkMoveStraightDiagonal(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix, boolean straightAxis) {

        if (straightAxis) //Moving along a straight axis (rock, queen)
        {

            if ((startColumn == desColumn) && (startRow != desRow)) //Moving along the same column
            {

                if (desRow < startRow) //Moving N
                {
                    //Checks each cell between the start row - 1 (since don't need to check the cell it is in) to the destination cell
                    for (int newRow = (startRow - 1); newRow > desRow; newRow--) {
                        //Checks the cell is empty
                        if (!checkAxisMove(newRow, desColumn, playerMatrix)) {
                            return false;
                        }

                    }

                } else //Moving S
                {

                    for (int newRow = (startRow + 1); newRow < desRow; newRow++) {

                        if (!checkAxisMove(newRow, desColumn, playerMatrix)) {
                            return false;
                        }

                    }

                }

            } else if ((startRow == desRow) && (startColumn != desColumn)) //Moving along the same row
            {

                if (desColumn < startColumn) //Moving W
                {

                    for (int newColumn = (startColumn - 1); newColumn > desColumn; newColumn--) {

                        if (!checkAxisMove(desRow, newColumn, playerMatrix)) {
                            return false;
                        }

                    }

                } else //Moving E
                {

                    for (int newColumn = (startColumn + 1); newColumn < desColumn; newColumn++) {

                        if (!checkAxisMove(desRow, newColumn, playerMatrix)) {
                            return false;
                        }

                    }

                }

            } else //If moved diagonally
            {

                strErrorMsg = "";
                return false;

            }

        } else //Moving diagonal (bishop/queen)
        {

            strErrorMsg = "The destination is not on the same diagonal line"; //Default error message
            int newColumn;

            if (desRow < startRow && desColumn < startColumn) //If moved NW
            {
                //The number of cells moved horizontal should equal the number of cells moved vertical
                if ((desRow - startRow) == (desColumn - startColumn)) {

                    for (int newRow = (startRow - 1); newRow > desRow; newRow--) {

                        newColumn = startColumn - (startRow - newRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }

                    }

                } else {
                    return false;
                }

            } else if (desRow < startRow && desColumn > startColumn) //If moved NE
            {

                if ((desRow - startRow) == (startColumn - desColumn)) {

                    for (int newRow = (startRow - 1); newRow > desRow; newRow--) {

                        newColumn = startColumn + (startRow - newRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }

                    }

                } else {
                    return false;
                }

            } else if (desRow > startRow && desColumn < startColumn) //If moved SW
            {

                if ((startRow - desRow) == (desColumn - startColumn)) {

                    for (int newRow = (startRow + 1); newRow < desRow; newRow++) {

                        newColumn = startColumn - (newRow - startRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }

                    }

                } else {
                    return false;
                }

            } else if (desRow > startRow && desColumn > startColumn) //If moved SE
            {

                if ((startRow - desRow) == (startColumn - desColumn)) {

                    for (int newRow = (startRow + 1); newRow < desRow; newRow++) {

                        newColumn = startColumn + (newRow - startRow);

                        if (!checkAxisMove(newRow, newColumn, playerMatrix)) {
                            return false;
                        }

                    }

                } else {
                    return false;
                }

            } else //Not a diagonal move
            {

                strErrorMsg = "";
                return false;

            }

        }

        finalDesRow = desRow;
        finalDesColumn = desColumn;

        return true;

    }
    //Checks the cell to make sure it is empty

    private boolean checkAxisMove(int newRow, int newColumn, int[][] playerMatrix) {

        if (playerMatrix[newRow][newColumn] != 0) //If not empty
        {

            strErrorMsg = "A piece is blocking the route"; //Error message
            return false;

        }

        return true;

    }

    public int getDesRow() {
        return finalDesRow;
    }

    public int getDesColumn() {
        return finalDesColumn;
    }

    public String getErrorMsg() {
        return strErrorMsg;
    }
}
