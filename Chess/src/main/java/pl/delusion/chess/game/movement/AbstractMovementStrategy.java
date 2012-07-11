package pl.delusion.chess.game.movement;

import pl.delusion.chess.game.Piece;
import pl.delusion.chess.game.PieceLocator;

public abstract class AbstractMovementStrategy implements MovementStrategy {

    public boolean checkMove(int fromX, int fromY, int toX, int toY, Piece piece, PieceLocator pieceLocator) {
        return getPossibleMoves(fromX, fromY, piece, pieceLocator).contains(new Move(toX, toY));
    }
    
}
