package pl.delusion.chess.game.movement;

import java.util.List;
import pl.delusion.chess.game.Piece;
import pl.delusion.chess.game.PieceLocator;

public interface MovementStrategy {
    
    List<Move> getPossibleMoves(int startX, int startY, Piece piece, PieceLocator pieceLocator);
    boolean checkMove(int fromX, int fromY, int toX, int toY, Piece piece, PieceLocator pieceLocator);
    
}
