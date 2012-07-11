package pl.delusion.chess.game;

import pl.delusion.chess.game.movement.MovementStrategy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.delusion.chess.game.movement.KnightMovementStrategy;
import pl.delusion.chess.game.movement.PawnMovementStrategy;

public class PieceLogicFactoryTest {
    
    private PieceLogicFactory pieceLogicFactory;
    
    @BeforeMethod
    public void beforeMethod() {
        pieceLogicFactory = new PieceLogicFactory();
    }
    
    @Test
    public void producesMovementStrategyForPawn() {
        // given
        
        // when
        MovementStrategy movementStrategy 
                = pieceLogicFactory.getMovementStrategy(PieceType.PAWN);
        
        // then
        assert movementStrategy instanceof PawnMovementStrategy;
    }
    
    @Test
    public void producesMovementStrategyForKnight() {
        // given
        
        // when
        MovementStrategy movementStrategy 
                = pieceLogicFactory.getMovementStrategy(PieceType.KNIGHT);
        
        // then
        assert movementStrategy instanceof KnightMovementStrategy;
    }
    
}
