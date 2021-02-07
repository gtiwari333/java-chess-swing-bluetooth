import chess.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static org.junit.jupiter.api.Assertions.*;

class WindowChessGameSingleTest  {

    @Test
    public  void PawnSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(0);
        WCGS.SetStartRowCol(6,7);
        WCGS.checkMove(4,7);
        assertTrue(WCGS.GetResult());
    }

    @Test
    public  void PawnFailureTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(0);
        WCGS.SetStartRowCol(6,7);
        WCGS.checkMove(0,0);
        assertFalse(WCGS.GetResult());
    }

    @Test
    public  void RockSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(1);
        WCGS.SetStartRowCol(7,7);
        WCGS.checkMove(4,7);
        assertTrue(WCGS.GetResult());
    }

    @Test
    public  void RockFailureTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(1);
        WCGS.SetStartRowCol(7,7);
        WCGS.checkMove(0,0);
        assertFalse(WCGS.GetResult());
    }

    @Test
    public  void KnightSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(2);
        WCGS.SetStartRowCol(7,6);
        WCGS.checkMove(5,5);
        assertTrue(WCGS.GetResult());
    }

    @Test
    public  void KnightFailureTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(2);
        WCGS.SetStartRowCol(7,6);
        WCGS.checkMove(0,0);
        assertFalse(WCGS.GetResult());
    }

    @Test
    public  void BishopSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(3);
        WCGS.SetStartRowCol(7,5);
        WCGS.checkMove(6,6);
        assertTrue(WCGS.GetResult());
    }

    @Test
    public  void BishopFailureTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(3);
        WCGS.SetStartRowCol(7,5);
        WCGS.checkMove(0,0);
        assertFalse(WCGS.GetResult());
    }

    @Test
    public  void QueenSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(4);
        WCGS.SetStartRowCol(7,3);
        WCGS.checkMove(6,4);
        assertTrue(WCGS.GetResult());
    }

    @Test
    public  void QueenFailureTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(4);
        WCGS.SetStartRowCol(7,3);
        WCGS.checkMove(0,0);
        assertFalse(WCGS.GetResult());
    }

    @Test
    public  void KingSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(5);
        WCGS.SetStartRowCol(7,4);
        WCGS.checkMove(6,4);
        assertTrue(WCGS.GetResult());
    }

    @Test
    public  void KingFailureTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(5);
        WCGS.SetStartRowCol(7,4);
        WCGS.checkMove(0,0);
        assertFalse(WCGS.GetResult());
    }
    //Update Matrix test
    @Test
    public  void PawnUpdateMatrixSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(0);
        WCGS.SetStartRowCol(6,7);
        WCGS.checkMove(4,7);
        assertEquals(4,WCGS.getNewdesrow());
        assertEquals(7,WCGS.getNewdescol());
    }

    @Test
    public  void RockUpdateMatrixSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(1);
        WCGS.SetStartRowCol(7,7);
        WCGS.checkMove(4,7);
        assertEquals(4,WCGS.getNewdesrow());
        assertEquals(7,WCGS.getNewdescol());
    }

    @Test
    public  void KnightUpdateMatrixSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(2);
        WCGS.SetStartRowCol(7,6);
        WCGS.checkMove(5,5);
        assertEquals(5,WCGS.getNewdesrow());
        assertEquals(5,WCGS.getNewdescol());
    }

    @Test
    public  void BishopUpdateMatrixSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(3);
        WCGS.SetStartRowCol(7,5);
        WCGS.checkMove(6,6);
        assertEquals(6,WCGS.getNewdesrow());
        assertEquals(6,WCGS.getNewdescol());
    }

    @Test
    public  void QueenUpdateMatrixSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(4);
        WCGS.SetStartRowCol(7,3);
        WCGS.checkMove(6,4);
        assertEquals(6,WCGS.getNewdesrow());
        assertEquals(4,WCGS.getNewdescol());
    }

    @Test
    public  void KingUpdateMatrixSuccessTest(){
        WindowChessGameSingle WCGS = new WindowChessGameSingle();
        WCGS.SetPieceBeingDragged(5);
        WCGS.SetStartRowCol(7,4);
        WCGS.checkMove(6,4);
        assertEquals(6,WCGS.getNewdesrow());
        assertEquals(4,WCGS.getNewdescol());
    }
}