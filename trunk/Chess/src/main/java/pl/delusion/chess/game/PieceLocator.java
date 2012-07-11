package pl.delusion.chess.game;

public interface PieceLocator {
    
    Piece getPiece(int x, int y);
    Direction hasPiece(int x, int y);
    Coordinates findPiece(Piece piece);
    
}
