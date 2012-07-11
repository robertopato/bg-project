package pl.delusion.chess.game;

import java.util.List;
import pl.delusion.chess.game.movement.Move;

public class CollisionDetectorImpl implements CollisionDetector {

    public List<Move> upToCollisionWithAnybody(List<Move> moves, PieceLocator pieceLocator) {
        return upToCollisionWith(moves, pieceLocator, null);
    }
    
    public List<Move> upToCollisionWith(List<Move> moves, PieceLocator pieceLocator, Direction player) {
        for(int i = 0; i < moves.size(); i++) {
            Direction direction
                    = pieceLocator.hasPiece(moves.get(i).getX(), moves.get(i).getY());
            
            if(direction != null && (player == null || player == direction)) {
                return moves.subList(0, i);
            }
        }
        
        return moves;
    }
    
}
