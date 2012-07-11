package pl.delusion.chess.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PieceTest {
    
    private static final PieceType TYPE = PieceType.PAWN;
    private static final Direction DIRECTION = Direction.GOING_UP;
    
    private Piece piece;
    
    @BeforeMethod
    public void beforeMethod() {
        piece = new Piece(TYPE, DIRECTION);
    }
    
    @Test
    public void pieceHasAType() {
        // given
        
        // when
        PieceType type = piece.getType();
        
        // then
        assert type == TYPE;
    }
    
    @Test
    public void pieceHasADirection() {
        // given
        
        // when
        Direction direction = piece.getDirection();
        
        // then
        assert direction == DIRECTION;
    }
    
    @Test
    public void pieceHasACounterOfMoves() {
        // given
        
        // when
        int before = piece.getMoveCount();
        piece.move();
        int after = piece.getMoveCount();
        
        // then
        assert before == 0;
        assert after == 1;
    }
    
}
