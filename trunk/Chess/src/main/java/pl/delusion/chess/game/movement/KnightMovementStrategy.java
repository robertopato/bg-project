package pl.delusion.chess.game.movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.delusion.chess.game.*;

public class KnightMovementStrategy extends AbstractMovementStrategy implements MovementStrategy {

    private CollisionDetector collisionDetector = new CollisionDetectorImpl();
    
    public List<Move> getPossibleMoves(int startX, int startY, Piece piece, PieceLocator pieceLocator) {
        List<Move> result = new ArrayList<Move>();
        
        // moving forward
        List<Move> initial = new ArrayList<Move>();
        initial.add(new Move(startX - 1, startY - 2));
        initial.add(new Move(startX - 2, startY - 1));
        initial.add(new Move(startX - 2, startY + 1));
        initial.add(new Move(startX - 1, startY + 2));
        initial.add(new Move(startX + 1, startY - 2));
        initial.add(new Move(startX + 2, startY - 1));
        initial.add(new Move(startX + 2, startY + 1));
        initial.add(new Move(startX + 1, startY + 2));
        
        List<Move> filtered = new ArrayList<Move>(result.size());
        
        for(Move move : initial) {
            boolean valid = true;
            
            // out of bounds
            if(move.getX() < 0 || move.getX() >= Board.SIZE 
                    || move.getY() < 0 || move.getY() >= Board.SIZE) {
                valid = false;
            }
            
            // valid move?
            if(valid) {
                List<Move> singleMove = Arrays.asList(move);
                filtered.addAll(collisionDetector.upToCollisionWith(singleMove, pieceLocator, piece.getDirection()));
            }
        }
        
        return filtered;
    }
    
}
