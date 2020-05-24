
import chess.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowChessGameSingle extends ChessBoard implements MouseListener, MouseMotionListener {

    private final int refreshRate = 5; //Amount of pixels moved before screen is refreshed
    private final ImageIcon[][] imgPlayer = new ImageIcon[2][6];
    private final String[] strPlayerName = {"", ""};
    private String strStatusMsg = "";
    private final CellMatrix cellMatrix = new CellMatrix();
    private int currentPlayer = 1, startRow = 7, startColumn = 7, pieceBeingDragged = 1;
    private final int startingX = 0;
    private final int startingY = 0;
    private int currentX = 0;
    private int currentY = 0;
    private int refreshCounter = 0;
    private boolean firstTime = true, hasWon = false, isDragging = false, kingSafe, drag = false;
    private final Pawn pawnObject = new Pawn();
    private final Rock rockObject = new Rock();
    private final Knight knightObject = new Knight();
    private final Bishop bishopObject = new Bishop();
    private final Queen queenObject = new Queen();
    private final King kingObject = new King();
    private int kr, kc;
    private int pplayer = 1, ppiece = 1;
    private int moveCount = 0;
    private final String[] moveRecord = new String[500];

    public WindowChessGameSingle() {
        super();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    private String getPlayerMsg() {
        System.err.println("windowchessboard, getPlayerMSg() being running");
        if (hasWon) {
            return "Congrats " + strPlayerName[currentPlayer - 1] + ", you are the winner!";
        } else if (firstTime) {
            return "" + strPlayerName[0] + " you are white, " + strPlayerName[1] + " you are black. Press start to Play";
        } else {
            return "" + strPlayerName[currentPlayer - 1] + " move";
        }

    }

    //reset playermatrix and pieces matrix
    private void resetBoard() {
        System.err.println("windowchessboard, resetboard() being running");
        hasWon = false;
        currentPlayer = 1;
        strStatusMsg = getPlayerMsg();
        cellMatrix.resetMatrix();
        repaint();

    }

    public void setupImages(ImageIcon[] imgWhite, ImageIcon[] imgBlack) {

        imgPlayer[0] = imgWhite;
        imgPlayer[1] = imgBlack;
        resetBoard();

    }

    public void setNames(String strPlayer1Name, String strPlayer2Name) {

        strPlayerName[0] = strPlayer1Name;
        strPlayerName[1] = strPlayer2Name;
        strStatusMsg = getPlayerMsg();
        repaint();

    }

    public String[] getMovementRecord() {
        return moveRecord;
    }

    protected void drawExtra(Graphics g) {
        System.err.println("windowchessboard, drawextra() being running");
        for (int i = 0; i < vecPaintInstructions.size(); i++) {
            System.err.println("vectorInstruction" + vecPaintInstructions.size());

            currentInstruction =  vecPaintInstructions.elementAt(i);
            int paintStartRow = currentInstruction.getStartRow();
            int paintStartColumn = currentInstruction.getStartColumn();
            int rowCells = currentInstruction.getRowCells();
            int columnCells = currentInstruction.getColumnCells();
            for (int row = 0; row < (paintStartRow + rowCells); row++) {

                for (int column = 0; column < (paintStartColumn + columnCells); column++) {

                    int playerCell = cellMatrix.getPlayerCell(row, column);
                    int pieceCell = cellMatrix.getPieceCell(row, column);

                    if (playerCell != 0) {

                        try {
                            g.drawImage((imgPlayer[playerCell - 1][pieceCell].getImage()), ((column + 1) * 50) - 45, ((row + 1) * 50) - 45, this);
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                    }

                }

            }

        }

        if (isDragging) {
            g.drawImage((imgPlayer[currentPlayer - 1][pieceBeingDragged].getImage()), (currentX - 25), (currentY - 25), this);
        }
        g.setColor(new Color(51, 51, 51));
        g.fillRect(5, 405, 415, 25);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.PLAIN, 13));
        g.drawString(strStatusMsg, 5, 425);

        vecPaintInstructions.clear(); //clear all paint instructions
    }

    public void newGame() {
        System.err.println("windowchessboard, newGame() being running");
        firstTime = false;
        resetBoard();

    }

    private void checkMove(int desRow, int desColumn) {

        System.err.println("windowchessboard, Checkmove() being running");
        boolean legalMove = false;

//if moved onto own piece
        if (cellMatrix.getPlayerCell(desRow, desColumn) == currentPlayer) {
            strStatusMsg = "Can not move onto a piece that is yours";
        } else {
//find the move is valid or not
            switch (pieceBeingDragged) {

                case 0:
                    legalMove = pawnObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix(), currentPlayer);
                    break;
                case 1:
                    legalMove = rockObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 2:
                    legalMove = knightObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 3:
                    legalMove = bishopObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 4:
                    legalMove = queenObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;
                case 5:
                    legalMove = kingObject.legalMove(startRow, startColumn, desRow, desColumn, cellMatrix.getPlayerMatrix());
                    break;

            }

        }

        //if legal
        if (legalMove) {
            System.err.println("legalmove, inside check");
            int newDesRow = 7;
            int newDesColumn = 7;

            switch (pieceBeingDragged) {

                case 0:
                    newDesRow = pawnObject.getDesRow();
                    newDesColumn = pawnObject.getDesColumn();
                    break;
                case 1:
                    newDesRow = rockObject.getDesRow();
                    newDesColumn = rockObject.getDesColumn();
                    break;
                case 2:
                    newDesRow = knightObject.getDesRow();
                    newDesColumn = knightObject.getDesColumn();
                    break;
                case 3:
                    newDesRow = bishopObject.getDesRow();
                    newDesColumn = bishopObject.getDesColumn();
                    break;
                case 4:
                    newDesRow = queenObject.getDesRow();
                    newDesColumn = queenObject.getDesColumn();
                    break;
                case 5:
                    newDesRow = kingObject.getDesRow();
                    newDesColumn = kingObject.getDesColumn();
                    break;

            }


            pplayer = cellMatrix.getPlayerCell(newDesRow, newDesColumn);
            ppiece = cellMatrix.getPieceCell(newDesRow, newDesColumn);


            cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
            cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);

            kr = cellMatrix.getKingRow(currentPlayer);
            kc = cellMatrix.getKingCol(currentPlayer);

            System.err.println("KingRow= " + kr + " KingCol= " + kc);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, kr, kc);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                System.err.println("King is NOT Safe");
            }

            if (kingSafe) {
                cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);

                if (cellMatrix.checkWinner(currentPlayer)) {

                    hasWon = true;
                    strStatusMsg = getPlayerMsg();

                } else { //turn change

                    if (currentPlayer == 1) {
                        moveRecord[moveCount++] = strPlayerName[currentPlayer - 1] + ": " + startRow + "," + startColumn + "->" + desRow + "," + desColumn;
                        currentPlayer = 2;
                    } else {
                        moveRecord[moveCount++] = strPlayerName[currentPlayer - 1] + ": " + startRow + "," + startColumn + "->" + desRow + "," + desColumn;
                        currentPlayer = 1;
                    }


                    strStatusMsg = getPlayerMsg();

                }
            } else {
                unsucessfullDrag();

                cellMatrix.setPlayerCell(newDesRow, newDesColumn, pplayer);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, ppiece);

                strStatusMsg = "" + strPlayerName[currentPlayer - 1] + ", Your King is checked";
                repaint();
            }

        } else {

            switch (pieceBeingDragged) {

                case 0:
                    strStatusMsg = pawnObject.getErrorMsg();
                    break;
                case 1:
                    strStatusMsg = rockObject.getErrorMsg();
                    break;
                case 2:
                    strStatusMsg = knightObject.getErrorMsg();
                    break;
                case 3:
                    strStatusMsg = bishopObject.getErrorMsg();
                    break;
                case 4:
                    strStatusMsg = queenObject.getErrorMsg();
                    break;
                case 5:
                    strStatusMsg = kingObject.getErrorMsg();
                    break;

            }

            unsucessfullDrag();

        }

    }

    private boolean isCheckMate(int curPlayer) {

        kr = cellMatrix.getKingRow(curPlayer);
        kc = cellMatrix.getKingCol(curPlayer);

        boolean legalMove;
        boolean safe;
        boolean b1 = true, b2 = true, b3 = true, b4 = true, b5 = true, b6 = true, b7 = true, b8 = true;
        int newkr;
        int newkc;

        newkr = kr + 1;
        newkc = kc;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b1 = true;
            } else {
                b1 = false;
            }
        }

        newkr = kr + 1;
        newkc = kc - 1;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b2 = true;
            } else {
                b2 = false;
            }
        }

        newkr = kr;
        newkc = kc - 1;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b3 = true;
            } else {
                b3 = false;
            }
        }

        newkr = kr - 1;
        newkc = kc - 1;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b4 = true;
            } else {
                b4 = false;
            }
        }

        newkr = kr - 1;
        newkc = kc;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b5 = true;
            } else {
                b5 = false;
            }
        }

        newkr = kr - 1;
        newkc = kc + 1;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b6 = true;
            } else {
                b6 = false;
            }
        }

        newkr = kr;
        newkc = kc + 1;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b7 = true;
            } else {
                b7 = false;
            }
        }

        newkr = kr + 1;
        newkc = kc + 1;
        if (newkr >= 0 && newkc >= 0 && newkr <= 7 && newkc <= 8) {
            legalMove = kingObject.legalMove(kr, kc, newkr, newkc, cellMatrix.getPlayerMatrix());
            safe = cellMatrix.isKingSafe(curPlayer, newkr, newkc);
            if (!legalMove || !safe) {
                b8 = true;
            } else {
                b8 = false;
            }
        }


        if (b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8) {
            return true;
        }


        return false;
    }

    private void unsucessfullDrag() {
        System.err.println("unsuccessfulDrag");
        cellMatrix.setPieceCell(startRow, startColumn, pieceBeingDragged);
        cellMatrix.setPlayerCell(startRow, startColumn, currentPlayer);

    }

    private void updatePaintInstructions(int desRow, int desColumn) {
        System.err.println("updatePaintInstructions");
        currentInstruction = new PaintInstruction(startRow, startColumn, 1);
        vecPaintInstructions.addElement(currentInstruction);
        currentInstruction = new PaintInstruction(desRow, desColumn);
        vecPaintInstructions.addElement(currentInstruction);

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
        mouseReleased(e);
    }

    public void mousePressed(MouseEvent e) {
        System.err.println("windowchessboard, mousepressed() being running");
        if (!hasWon && !firstTime) {

            int x = e.getX();
            int y = e.getY();

            if ((x > 5 && x < 405) && (y > 5 && y < 405)) //in the correct bounds
            {
                //find startRow and StartColumn from where the player clicks on the board
                startRow = findWhichTileSelected(y);
                startColumn = findWhichTileSelected(x);
                System.err.println("START  " + startRow + " ," + startColumn);
                if (cellMatrix.getPlayerCell(startRow, startColumn) == currentPlayer) {

                    pieceBeingDragged = cellMatrix.getPieceCell(startRow, startColumn);
                    cellMatrix.setPieceCell(startRow, startColumn, 6);
                    cellMatrix.setPlayerCell(startRow, startColumn, 0);
                    isDragging = true;

                } else {
                    isDragging = false;
                }

            }

        }

    }

    public void mouseReleased(MouseEvent e) {
        System.err.println("mouseReleased");

        if (!drag) {
            cellMatrix.setPieceCell(startRow, startColumn, pieceBeingDragged);
            cellMatrix.setPlayerCell(startRow, startColumn, currentPlayer);
        }

        if (isDragging) {

            isDragging = false;

            int desRow = findWhichTileSelected(currentY);
            int desColumn = findWhichTileSelected(currentX);
            checkMove(desRow, desColumn);

            kr = cellMatrix.getKingRow(currentPlayer);
            kc = cellMatrix.getKingCol(currentPlayer);

            System.err.println("KingRow= " + kr + " KingCol= " + kc);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, kr, kc);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                strStatusMsg = "" + strPlayerName[currentPlayer - 1] + " move, Your King is checked";
                System.err.println("King is NOT Safe");
            }

            if (isCheckMate(currentPlayer)) {
                strStatusMsg = "CHECKMATE";
            }

            repaint();

        }

    }

    public void mouseDragged(MouseEvent e) {
        drag = true;
        if (isDragging) {

            int x = e.getX();
            int y = e.getY();

            if ((x > 5 && x < 405) && (y > 5 && y < 405)) //in the correct bounds
            {

                if (refreshCounter >= refreshRate) {

                    currentX = x;
                    currentY = y;
                    refreshCounter = 0;
                    int desRow = findWhichTileSelected(currentY);
                    int desColumn = findWhichTileSelected(currentX);

                    updatePaintInstructions(desRow, desColumn);

                    repaint();

                } else {
                    refreshCounter++;
                }

            } else {
                isDragging = false;
                unsucessfullDrag();
                repaint();
            }

        }

    }

    public void mouseMoved(MouseEvent e) {
    }

}
