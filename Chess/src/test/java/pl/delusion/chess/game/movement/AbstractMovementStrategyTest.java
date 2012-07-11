package pl.delusion.chess.game.movement;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.delusion.chess.game.Piece;

import pl.delusion.chess.game.PieceLocator;

public class AbstractMovementStrategyTest {
    
    private static final List<Move> MOVES = new ArrayList<Move>();
    
    static {
        MOVES.add(new Move(4, 4));
    }
    
    private AbstractMovementStrategy abstractMovementStrategy;
    
    @BeforeMethod
    public void beforeMethod() {
        abstractMovementStrategy = new AbstractMovementStrategy() {

            public List<Move> getPossibleMoves(int startX, int startY, Piece piece, PieceLocator pieceLocator) {
                return MOVES;
            }
            
        };
    }
    
    @Test
    public void allowsCorrectMoves() {
        // given
        
        // when
        boolean result = abstractMovementStrategy.checkMove(3, 3, 4, 4, null, null);
        
        // then
        assert result;
    }
    
    @Test
    public void deniesIncorrectMoves() {
        // given
        
        // when
        boolean result = abstractMovementStrategy.checkMove(3, 3, 4, 5, null, null);
        
        // then
        assert !result;
    }
    
}
