
import bluetooth.BtCommunication;
import chess.*;

import javax.microedition.io.StreamConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WindowChessGameBT extends ChessBoard implements MouseListener, MouseMotionListener {

    private final int refreshRate = 5; //Amount of pixels moved before screen is refreshed
    private final ImageIcon[][] imgPlayer = new ImageIcon[2][6];
    private final String[] strPlayerName = {"", ""};
    private String strStatusMsg = "";
    private final CellMatrix cellMatrix = new CellMatrix();
    private int currentPlayer = 1, startRow = 7, startColumn = 7, pieceBeingDragged = 1;
    private int currentX = 0;
    private int currentY = 0;
    private int refreshCounter = 0;
    private boolean firstTime = true, hasWon = false, isDragging = false, kingSafe, drag = false;
    private final Pawn pawnObject;
    private final Rock rockObject;
    private final Knight knightObject;
    private final Bishop bishopObject;
    private final Queen queenObject;
    private final King kingObject;
    private boolean isServer, myturn;
    private int kr, kc;
    private int pplayer = 1, ppiece = 1;
    private final BtCommunication btcomm;
    StreamConnection conn;

    private final String[] strRedPieces = {"whitePawn.png", "whiteRock.png", "whiteKnight.png", "whiteBishop.png", "whiteQueen.png", "whiteKing.png"};
    private final String[] strBluePieces = {"blackPawn.png", "blackRock.png", "blackKnight.png", "blackBishop.png", "blackQueen.png", "blackKing.png"};
    private final ImageIcon[] imgBlack = new ImageIcon[6];
    private final ImageIcon[] imgWhite = new ImageIcon[6];

    public WindowChessGameBT(boolean type)   {
        isServer = type;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        pawnObject = new Pawn();
        rockObject = new Rock();
        knightObject = new Knight();
        bishopObject = new Bishop();
        queenObject = new Queen();
        kingObject = new King();
        btcomm = new BtCommunication();
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
        cellMatrix.printPieceCell();
        cellMatrix.printPlayerCell();

    }

    public void setupImages() {


        for (int i = 0; i < 6; i++) {
            imgWhite[i] = new ImageIcon(getClass().getResource("images/" + strRedPieces[i]));
            imgBlack[i] = new ImageIcon(getClass().getResource("images/" + strBluePieces[i]));
        }
        System.err.println(imgWhite[1]);
    }

    public void setNames(String strPlayer1Name, String strPlayer2Name) {

        strPlayerName[0] = strPlayer1Name;
        strPlayerName[1] = strPlayer2Name;
        strStatusMsg = getPlayerMsg();
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

    public void newGame(boolean isServer1, StreamConnection conn1) {
        isServer = isServer1;
        conn = conn1;
        System.err.println("*********Game Started **********");
        hasWon = false;
        if (isServer) {
            myturn = true;
            currentPlayer = 1;
            //strStatusGame = "Welcome, Your Turn First";
        } else {
            myturn = false;
            currentPlayer = 2;
            //strStatusGame = "Welcome, Server's Turn First";
        }

        cellMatrix.resetMatrix();
        //TODO:modify player name after reading info abt game
        setupImages();
        imgPlayer[1] = imgBlack;
        imgPlayer[0] = imgWhite;
        System.err.println("windowchessboard, newGame() being running");
        firstTime = false;
        resetBoard();

    }

    public void processCommands(String[] command) {

        for (String s : command) {
            System.err.println("Extracted" + s);
        }

        switch (command[0]) {
            case "makeEmpty":
                //make the source cell empty
                cellMatrix.setPieceCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), 6);
                cellMatrix.setPlayerCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), 0);
                break;
            case "playerCell":
                //update playerMatrix
                cellMatrix.setPlayerCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]));
                break;
            case "pieceCell":
                //update pieceMatrix
                changeTurn();
                cellMatrix.setPieceCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]));
                break;
        }
        repaint();
    }

    private void checkMove(int desRow, int desColumn) {
        cellMatrix.printPieceCell();
        cellMatrix.printPlayerCell();
        System.err.println("windowchessboard, Checkmove() being running");
        boolean legalMove = false;
        String msg;

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
            System.err.println("legal move");
            msg = "makeEmpty#" + startRow + "#" + startColumn;
            btcomm.writeMessage(msg);
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

            System.err.println("pplayer " + pplayer + " ppiece" + ppiece);
            System.err.println("newDesRow " + newDesRow + " newDesColumn" + newDesRow);

            cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
            cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
            //int tmp=(currentPlayer%2)+1;
            msg = "playerCell#" + newDesRow + "#" + newDesColumn + "#" + currentPlayer;
            btcomm.writeMessage(msg);
            System.err.println("Sent: " + msg);


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
                msg = "pieceCell#" + newDesRow + "#" + newDesColumn + "#" + pieceBeingDragged;
                btcomm.writeMessage(msg);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
                System.err.println("Sent: " + msg);
                if (cellMatrix.checkWinner(currentPlayer)) {
                    System.err.println("checking haswon??");
                    hasWon = true;
                    strStatusMsg = getPlayerMsg();

                } else {

                    changeTurn();

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
            System.err.println("ILLEGAL");

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
        if (myturn) {
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
    }

    public void mouseReleased(MouseEvent e) {
        if (myturn) {

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

                repaint();

            }
        }
        cellMatrix.printPieceCell();
        cellMatrix.printPlayerCell();
    }

    public void mouseDragged(MouseEvent e) {
        if (myturn) {

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
                        System.err.println("desRow: " + desRow + "desColumn" + desColumn);
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
    }

    public void changeTurn() {
        //change turn
        myturn = myturn == false ? true : false;
        if (isServer && myturn) {
            currentPlayer = 1;
        } else {
            currentPlayer = 2;
        }

    }

    public void mouseMoved(MouseEvent e) {
    }

}
