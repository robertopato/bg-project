package pl.delusion.chess.game.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.delusion.chess.game.*;

public class PawnMovementStrategy extends AbstractMovementStrategy implements MovementStrategy {

    private CollisionDetector collisionDetector = new CollisionDetectorImpl();
    
    public List<Move> getPossibleMoves(int startX, int startY, Piece piece, PieceLocator pieceLocator) {
        List<Move> result = new ArrayList<Move>();
        
        // moving forward
        List<Move> goForward;
        
        // first or subsequent move?
        if(piece.getMoveCount() == 0) {
            goForward = Arrays.asList(new Move(startX + piece.getDirection().getYIncrement(), startY),
                                      new Move(startX + 2 * piece.getDirection().getYIncrement(), startY));
            
        } else {
            goForward = Arrays.asList(new Move(startX + piece.getDirection().getYIncrement(), startY));
            
        }
        
        // up to first collision
        goForward = collisionDetector.upToCollisionWithAnybody(goForward, pieceLocator);
        
        // moving diagonally
        List<Move> goDiagonally = new ArrayList<Move>();
        
        // try left and right
        for(int i = -1; i < 2; i += 2) {
            Direction direction 
                    = pieceLocator.hasPiece(startX + piece.getDirection().getYIncrement(), startY + i);
            
            if(direction != null && direction != piece.getDirection()) {
                goDiagonally.add(new Move(startX + piece.getDirection().getYIncrement(), startY + i));
            }
        }
        
        List<Move> filtered = new ArrayList<Move>(result.size());
        
        goDiagonally.addAll(goForward);
        for(Move move : goDiagonally) {
            boolean valid = true;
            
            // out of bounds
            if(move.getX() < 0 || move.getX() >= Board.SIZE) {
                valid = false;
            }
            
            // valid move?
            if(valid) {
                filtered.add(move);
            }
        }
        
        return filtered;
    }
    
}
