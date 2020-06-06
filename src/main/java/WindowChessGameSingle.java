
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
//    사용되지 않은 변수는 지웁니다.
//    private final int startingX = 0;
//    private final int startingY = 0;
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
    private int KingRow, KingColumn;
    private int PlayerMousePosition = 1, PiecePosition = 1;
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
        //보드 초기 페인트를 설정하는 과정입니다. 이 부분에 메소드 추출을 수행합니다.
//        int NumOfPaintInstructions = vecPaintInstructions.size();
//        for (int i = 0; i < NumOfPaintInstructions; i++) {
//            System.err.println("vectorInstruction" + NumOfPaintInstructions);
//
//            currentInstruction =  vecPaintInstructions.elementAt(i);
//            int paintStartRow = currentInstruction.getStartRow();
//            int paintStartColumn = currentInstruction.getStartColumn();
//            int rowCells = currentInstruction.getRowCells();
//            int columnCells = currentInstruction.getColumnCells();
//            for (int row = 0; row < (paintStartRow + rowCells); row++) {
//
//                for (int column = 0; column < (paintStartColumn + columnCells); column++) {
//
//                    int playerCell = cellMatrix.getPlayerCell(row, column);
//                    int pieceCell = cellMatrix.getPieceCell(row, column);
//
//                    if (playerCell != 0) {
//
//                        try {
//                            g.drawImage((imgPlayer[playerCell - 1][pieceCell].getImage()), ((column + 1) * 50) - 45, ((row + 1) * 50) - 45, this);
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                        }
//
//                    }
//
//                }
//
//            }
//
//        }
        InitBoardPaint(g);

        if (isDragging) {
            g.drawImage((imgPlayer[currentPlayer - 1][pieceBeingDragged].getImage()), (currentX - 25), (currentY - 25), this);
        }
//        보드의 디자인을 생성하는 함수들입니다. 이 부분에 메소드 추출을 수행합니다.
//        g.setColor(new Color(51, 51, 51));
//        g.fillRect(5, 405, 415, 25);
//        g.setColor(new Color(255, 255, 255));
//        g.setFont(new Font("Arial", Font.PLAIN, 13));
//        g.drawString(strStatusMsg, 5, 425);

        BoardDesign(g);

        vecPaintInstructions.clear(); //clear all paint instructions
    }

    private void BoardDesign(Graphics g) {
        g.setColor(new Color(51, 51, 51));
        g.fillRect(5, 405, 415, 25);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Arial", Font.PLAIN, 13));
        g.drawString(strStatusMsg, 5, 425);
    }

    private void InitBoardPaint(Graphics g) {
        int NumOfPaintInstructions = vecPaintInstructions.size();
        for (int i = 0; i < NumOfPaintInstructions; i++) {
            System.err.println("vectorInstruction" + NumOfPaintInstructions);

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

//            현재 위치를 저장하는 변수를 따로 메소드 추출 해줍니다.
//            PlayerMousePosition = cellMatrix.getPlayerCell(newDesRow, newDesColumn);
//            PiecePosition = cellMatrix.getPieceCell(newDesRow, newDesColumn);

            SaveCurrentPosition(newDesRow, newDesColumn);

//            움직인 위치를 저장하는 변수를 따로 메소드 추출 해줍니다.
//            cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
//            cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);

            SetCurrentPosition(newDesRow, newDesColumn);

//            현재 왕의 위치를 저장하는 변수를 따로 메소드 추출 해줍니다.
//            KingRow = cellMatrix.getKingRow(currentPlayer);
//            KingColumn = cellMatrix.getKingCol(currentPlayer);

            GetKingPosition();

            System.err.println("KingRow= " + KingRow + " KingCol= " + KingColumn);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, KingRow, KingColumn);

            if (kingSafe) {
                System.err.println("King is Safe");
            } else {
                System.err.println("King is NOT Safe");
            }

            if (kingSafe) {
                SetCurrentPosition(newDesRow, newDesColumn);

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

                cellMatrix.setPlayerCell(newDesRow, newDesColumn, PlayerMousePosition);
                cellMatrix.setPieceCell(newDesRow, newDesColumn, PiecePosition);

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

    private void GetKingPosition() {
        KingRow = cellMatrix.getKingRow(currentPlayer);
        KingColumn = cellMatrix.getKingCol(currentPlayer);
    }

    private void SetCurrentPosition(int newDesRow, int newDesColumn) {
        cellMatrix.setPlayerCell(newDesRow, newDesColumn, currentPlayer);
        cellMatrix.setPieceCell(newDesRow, newDesColumn, pieceBeingDragged);
    }

    private void SaveCurrentPosition(int newDesRow, int newDesColumn) {
        PlayerMousePosition = cellMatrix.getPlayerCell(newDesRow, newDesColumn);
        PiecePosition = cellMatrix.getPieceCell(newDesRow, newDesColumn);
    }

    private boolean isCheckMate(int curPlayer) {

        KingRow = cellMatrix.getKingRow(curPlayer);
        KingColumn = cellMatrix.getKingCol(curPlayer);


        boolean South = true, WestSouth = true, West = true, NorthWest = true, North = true, NorthEast = true, East = true, SouthEast = true;

        South = TestSouth(curPlayer);

        WestSouth = TestWestSouth(curPlayer);

        West = TestWest(curPlayer);

        NorthWest = TestNorthWest(curPlayer);

        North = TestNorth(curPlayer);

        NorthEast = TestNorthEast(curPlayer);

        East = TestEast(curPlayer);

        SouthEast = TestSouthEast(curPlayer);


        if (South && WestSouth && West && NorthWest && North && NorthEast && East && SouthEast) {
            return true;
        }


        return false;
    }

    private boolean TestSouthEast(int curPlayer) {
        int NewKingRow = KingRow + 1;
        int NewKingColumn = KingColumn + 1;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {
            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean TestEast(int curPlayer) {
        int NewKingRow = KingRow;
        int NewKingColumn = KingColumn + 1;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());;
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);;
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {
            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean TestNorthEast(int curPlayer) {
        int NewKingRow = KingRow - 1;
        int NewKingColumn = KingColumn + 1;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {

            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean TestNorth(int curPlayer) {
        int NewKingRow = KingRow - 1;
        int NewKingColumn = KingColumn;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {

            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean TestNorthWest(int curPlayer) {
        int NewKingRow = KingRow - 1;
        int NewKingColumn = KingColumn - 1;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {

            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean TestWest(int curPlayer) {
        int NewKingRow = KingRow;
        int NewKingColumn = KingColumn - 1;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {

            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean TestWestSouth(int curPlayer) {
        int NewKingRow = KingRow + 1;
        int NewKingColumn = KingColumn - 1;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {

            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private Boolean TestSouth(int curPlayer) {
        int NewKingRow = KingRow + 1;
        int NewKingColumn = KingColumn;
        boolean CanLegalMove = kingObject.legalMove(KingRow, KingColumn, NewKingRow, NewKingColumn, cellMatrix.getPlayerMatrix());
        boolean isSafe = cellMatrix.isKingSafe(curPlayer, NewKingRow, NewKingColumn);
        NewKingRow = KingRow + 1;
        NewKingColumn = KingColumn;
        if (NewKingRow >= 0 && NewKingColumn >= 0 && NewKingRow <= 7 && NewKingColumn <= 8) {

            if (!CanLegalMove || !isSafe) {
                return true;
            } else {
               return false;
            }
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

            boolean isCorrectBounds = (x > 5 && x < 405) && (y > 5 && y < 405); // 정해진 범위 안에 있는지 확인하는 조건을 boolean 변수로 선언해준다.

            if (isCorrectBounds) //in the correct bounds
            {
                //find startRow and StartColumn from where the player clicks on the board
                startRow = findWhichTileSelected(y);
                startColumn = findWhichTileSelected(x);
                System.err.println("START  " + startRow + " ," + startColumn);

                boolean isCorrectCurrentPlayerTurn = cellMatrix.getPlayerCell(startRow, startColumn) == currentPlayer;
                //현재 플레이어의 턴이 맞는지 확인해주는 조건을 boolean 변수로 선언해준다.
                if (isCorrectCurrentPlayerTurn) {

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

            GetKingPosition();

            System.err.println("KingRow= " + KingRow + " KingCol= " + KingColumn);
            kingSafe = cellMatrix.isKingSafe(currentPlayer, KingRow, KingColumn);

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
            boolean isCorrectBound = (x > 5 && x < 405) && (y > 5 && y < 405);
            //정해진 범위 안에 있는지 확인하는 조건을 boolean 변수로 선언해준다.
            if (isCorrectBound) //in the correct bounds
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