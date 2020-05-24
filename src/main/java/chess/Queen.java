package chess;

public class Queen extends BasePiece {

    public boolean legalMove(int startRow, int startColumn, int desRow, int desColumn, int[][] playerMatrix) {

        boolean axis;

        if (startRow == desRow ^ startColumn == desColumn) //XOR If ONE of these conditions match (if both true or false then false is returned)
        {
            axis = true; //Moving straight along axis
        } else if (startRow != desRow && startColumn != desColumn) {
            axis = false; //Moving diagonal
        } else {

            strErrorMsg = "Queen can move in a straight line in any direction";
            return false;

        }

        return checkMoveStraightDiagonal(startRow, startColumn, desRow, desColumn, playerMatrix, axis);

    }
}
