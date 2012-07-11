package pl.delusion.chess.game.movement;

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

public class PawnMovementStrategyTest {
    
    private PawnMovementStrategy pawnMovementStrategy;
    
    private PieceLocator pieceLocator;
    private Piece goingUpPawn;
    private Piece goingDownPawn;
    
    @BeforeMethod
    public void beforeMethod() {
        pawnMovementStrategy = new PawnMovementStrategy();
        
        pieceLocator = mock(Board.class);
        
        goingUpPawn = mock(Piece.class);
        when(goingUpPawn.getDirection()).thenReturn(Direction.GOING_UP);
        when(goingUpPawn.getMoveCount()).thenReturn(1);
        
        goingDownPawn = mock(Piece.class);
        when(goingDownPawn.getDirection()).thenReturn(Direction.GOING_DOWN);
        when(goingDownPawn.getMoveCount()).thenReturn(1);
    }
    
    @Test
    public void pawnWithGoingUpDirectionMayMoveUpToTwoOnFirstMove() {
        // given
        when(goingUpPawn.getMoveCount()).thenReturn(0);
        
        // when
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingUpPawn, pieceLocator);
        
        // then
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(4, 5),
            new Move(3, 5)
        }));
    }
    
    @Test
    public void pawnWithGoingUpDirectionMovesOneUpOnSubsequentMoves() {
        // given
        
        // when
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingUpPawn, pieceLocator);
        
        // then
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(4, 5)
        }));
    }
    
    @Test
    public void pawnWithGoingUpDirectionWillNotMoveBeyondBoard() {
        // two moves
        when(goingUpPawn.getMoveCount()).thenReturn(0);
        
        // zero options
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(0, 0, goingUpPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
        
        // one option 
        moves = pawnMovementStrategy.getPossibleMoves(1, 0, goingUpPawn, pieceLocator);
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(0, 0)
        }));
        
        // one move
        when(goingUpPawn.getMoveCount()).thenReturn(1);
        
        // zero options
        moves = pawnMovementStrategy.getPossibleMoves(0, 0, goingUpPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
    }
    
    @Test
    public void pawnWithGoingUpDirectionWillNotMoveIntoAnotherPiece() {
        when(pieceLocator.hasPiece(4, 5)).thenReturn(Direction.GOING_DOWN);
        
        // one move
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingUpPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
        
        // two moves
        when(goingUpPawn.getMoveCount()).thenReturn(0);
        
        moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingUpPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
        
        when(pieceLocator.hasPiece(4, 5)).thenReturn(null);
        when(pieceLocator.hasPiece(3, 5)).thenReturn(Direction.GOING_DOWN);
        
        moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingUpPawn, pieceLocator);
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(4, 5)
        }));
    }
    
    @Test
    public void pawnWithGoingUpDirectionMayMoveToADiagonalFieldOccupiedByTheOtherPlayersPawn() {
        // given
        when(pieceLocator.hasPiece(4, 4)).thenReturn(Direction.GOING_DOWN);
        when(pieceLocator.hasPiece(4, 5)).thenReturn(Direction.GOING_UP);
        when(pieceLocator.hasPiece(4, 6)).thenReturn(Direction.GOING_UP);
        
        // when
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingUpPawn, pieceLocator);
        
        // then
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(4, 4)
        }));
    }
    
    @Test
    public void pawnWithGoingDownDirectionMayMoveUpToTwoOnFirstMove() {
        // given
        when(goingDownPawn.getMoveCount()).thenReturn(0);
        
        // when
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingDownPawn, pieceLocator);
        
        // then
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(6, 5),
            new Move(7, 5)
        }));
    }
    
    @Test
    public void pawnWithGoingDownDirectionMovesOneDownOnSubsequentMoves() {
        // given
        
        // when
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingDownPawn, pieceLocator);
        
        // then
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(6, 5)
        }));
    }
    
    @Test
    public void pawnWithGoingDownDirectionWillNotMoveBeyondBoard() {
        // two moves
        when(goingDownPawn.getMoveCount()).thenReturn(1);
        
        // zero options
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(7, 7, goingDownPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
        
        // one option 
        moves = pawnMovementStrategy.getPossibleMoves(6, 7, goingDownPawn, pieceLocator);
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(7, 7)
        }));
        
        // one move
        when(goingDownPawn.getMoveCount()).thenReturn(1);
        
        // zero options
        moves = pawnMovementStrategy.getPossibleMoves(7, 7, goingDownPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
    }
    
    @Test
    public void pawnWithGoingDownDirectionWillNotMoveIntoAnotherPiece() {
        when(pieceLocator.hasPiece(6, 5)).thenReturn(Direction.GOING_DOWN);
        
        // one move
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingDownPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
        
        // two moves
        when(goingDownPawn.getMoveCount()).thenReturn(0);
        
        moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingDownPawn, pieceLocator);
        assert moves.equals(Collections.emptyList());
        
        when(pieceLocator.hasPiece(6, 5)).thenReturn(null);
        when(pieceLocator.hasPiece(7, 5)).thenReturn(Direction.GOING_DOWN);
        
        moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingDownPawn, pieceLocator);
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(6, 5)
        }));
    }
    
    @Test
    public void pawnWithGoingDownDirectionMayMoveToADiagonalFieldOccupiedByTheOtherPlayersPawn() {
        // given
        when(pieceLocator.hasPiece(6, 4)).thenReturn(Direction.GOING_DOWN);
        when(pieceLocator.hasPiece(6, 5)).thenReturn(Direction.GOING_DOWN);
        when(pieceLocator.hasPiece(6, 6)).thenReturn(Direction.GOING_UP);
        
        // when
        List<Move> moves = pawnMovementStrategy.getPossibleMoves(5, 5, goingDownPawn, pieceLocator);
        
        // then
        assert moves.equals(Arrays.asList(new Move[] {
            new Move(6, 6)
        }));
    }
    
}
