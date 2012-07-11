package pl.delusion.chess.game;

import java.util.List;
import pl.delusion.chess.game.movement.Move;

public interface CollisionDetector {
    
    List<Move> upToCollisionWithAnybody(List<Move> moves, PieceLocator pieceLocator);
    List<Move> upToCollisionWith(List<Move> moves, PieceLocator pieceLocator, Direction direction);
    
}
