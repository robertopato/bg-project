package pl.delusion.chess.game;

import pl.delusion.chess.game.movement.MovementStrategy;
import java.util.EnumMap;
import java.util.Map;
import pl.delusion.chess.game.movement.KnightMovementStrategy;
import pl.delusion.chess.game.movement.PawnMovementStrategy;

public class PieceLogicFactory {
    
    private Map<PieceType, MovementStrategy> movementStrategies 
            = new EnumMap<PieceType, MovementStrategy>(PieceType.class);
    
    {
        movementStrategies.put(PieceType.KNIGHT, new KnightMovementStrategy());
        movementStrategies.put(PieceType.PAWN, new PawnMovementStrategy());
    }
    
    public MovementStrategy getMovementStrategy(PieceType pieceType) {
        return movementStrategies.get(pieceType);
    }
    
}
