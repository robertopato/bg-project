package pl.delusion.chess.game.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.delusion.chess.game.Piece;

import static org.mockito.Mockito.*;
import pl.delusion.chess.game.Board;
import pl.delusion.chess.game.Direction;
import pl.delusion.chess.game.PieceLocator;

public class KnightMovementStrategyTest {
    
    private static final List<Move> KNIGHT_MOVES_FROM_FIVE_FIVE = new ArrayList<Move>();
    
    static {
        KNIGHT_MOVES_FROM_FIVE_FIVE.addAll(Arrays.asList(new Move[] {
            new Move(4, 3), new Move(3, 4), new Move(3, 6), new Move(4, 7),
            new Move(6, 3), new Move(7, 4), new Move(7, 6), new Move(6, 7)
        }));
    }
    
    private KnightMovementStrategy knightMovementStrategy;
    
    private PieceLocator pieceLocator;
    private Piece knight;
    private Piece otherPiece;
    
    @BeforeMethod
    public void beforeMethod() {
        knightMovementStrategy = new KnightMovementStrategy();
        
        pieceLocator = mock(Board.class);
        knight = mock(Piece.class);
        otherPiece = mock(Piece.class);
        
        when(knight.getDirection()).thenReturn(Direction.GOING_DOWN);
        when(otherPiece.getDirection()).thenReturn(Direction.GOING_DOWN);
    }
    
    @Test
    public void knightHasEightPossibleLShapedMoves() {
        assert knightMovementStrategy.getPossibleMoves(5, 5, knight, pieceLocator).equals(KNIGHT_MOVES_FROM_FIVE_FIVE);
    }
    
    @Test
    public void knightWillNotGoOffBoard() {
        assert knightMovementStrategy.getPossibleMoves(0, 0, knight, pieceLocator).equals(Arrays.asList(new Move[] {
            new Move(2, 1), new Move(1, 2)
        }));
        
        assert knightMovementStrategy.getPossibleMoves(7, 0, knight, pieceLocator).equals(Arrays.asList(new Move[] {
            new Move(5, 1), new Move(6, 2)
        }));
        
        assert knightMovementStrategy.getPossibleMoves(0, 7, knight, pieceLocator).equals(Arrays.asList(new Move[] {
            new Move(1, 5), new Move(2, 6)
        }));
        
        assert knightMovementStrategy.getPossibleMoves(7, 7, knight, pieceLocator).equals(Arrays.asList(new Move[] {
            new Move(6, 5), new Move(5, 6)
        }));
    }
    
    @Test
    public void knightWillNotMoveToASquareOccupiedByAnotherPieceOfItsOwner() {
        for(Move move : KNIGHT_MOVES_FROM_FIVE_FIVE) {
            when(pieceLocator.hasPiece(move.getX(), move.getY())).thenReturn(Direction.GOING_DOWN);
        }
        
        assert knightMovementStrategy.getPossibleMoves(5, 5, knight, pieceLocator).equals(Collections.emptyList());
    }
    
    @Test
    public void knightWillMoveToSquaresOccupiedByOpponentPieces() {
        for(Move move : KNIGHT_MOVES_FROM_FIVE_FIVE) {
            when(pieceLocator.hasPiece(move.getX(), move.getY())).thenReturn(Direction.GOING_UP);
        }
        
        assert knightMovementStrategy.getPossibleMoves(5, 5, knight, pieceLocator).equals(KNIGHT_MOVES_FROM_FIVE_FIVE);
    }
    
}
