import chess.PaintInstruction;

import java.awt.*;
import java.util.Vector;

public class ChessBoard extends Canvas {

    protected PaintInstruction currentInstruction = null;
    protected final Vector<PaintInstruction> vecPaintInstructions = new Vector<>();

    public void update(Graphics g) {
        System.err.println("chessboard, update() being running");
        paint(g);
    }

    public void paint(Graphics g) {
        System.err.println("chessboard, paint() being running");
        boolean NoInstructions= vecPaintInstructions.size() == 0; //refect
        if (NoInstructions) {
            currentInstruction = new PaintInstruction(0, 0, 8);
            vecPaintInstructions.addElement(currentInstruction);

        }

        g.setColor(new Color(51, 51, 51));
        g.fillRect(50, 450, 450, 50); //Paint over the current status text

        int num_of_PaintInstructions=vecPaintInstructions.size();//refect
        for (int i = 0; i <num_of_PaintInstructions; i++) {
            currentInstruction = vecPaintInstructions.elementAt(i);
            int startRow = currentInstruction.getStartRow();
            int startColumn = currentInstruction.getStartColumn();
            int rowCells = currentInstruction.getRowCells();
            int columnCells = currentInstruction.getColumnCells();
            //blank bgs
            for (int row = startRow; row < (startRow + rowCells); row++) {
                for (int column = startColumn; column < (startColumn + columnCells); column++) {
                    drawTile(row, column, g);
                }

            }

        }

        drawExtra(g);

    }

    private void drawTile(int row, int column, Graphics g) {
        //todo: add the cell labels A-H:1-8
        boolean RowAndColEven=((row + 1) % 2 == 0)&&((column + 1) % 2 == 0);
        boolean RowIsEvenColIsOdd=((row + 1) % 2 == 0)&&((column + 1) % 2 != 0);
        boolean RowIsOddColIsEven=((row + 1) % 2 != 0)&&((column + 1) % 2 == 0);
        boolean RowAndColOdd=((row + 1) % 2 != 0)&&((column + 1) % 2 != 0);
        if(RowAndColEven)
            g.setColor(new Color(255, 255, 255));
        if(RowIsEvenColIsOdd)
            g.setColor(new Color(165, 130, 95));
        if(RowIsOddColIsEven)
            g.setColor(new Color(165, 130, 95));
        if(RowAndColOdd)
            g.setColor(new Color(255, 255, 255));
        g.fillRect((5 + (column * 50)), (5 + (row * 50)), 50, 50);

    }

    protected void drawExtra(Graphics g) //Any class extending the chess board can use this method to add extra things like player pieces
    {
    }

    protected int findWhichTileSelected(int coor) //Finds which tile the mouse is over
    {
        System.err.println("chessboard, findwhichtileselected() being running");
        for (int i = 0; i < 8; i++) {

            if ((coor >= (5 + (i * 50))) && (coor <= (55 + (i * 50)))) {
                return i;
            }

        }

        return -1;

    }
}