package pl.delusion.chess.game;

public class Piece {
    
    private int moveCount = 0;
    
    private PieceType type;
    private Direction direction;

    public Piece(PieceType type, Direction direction) {
        this.type = type;
        this.direction = direction;
    }
    
    public void move() {
        moveCount++;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public PieceType getType() {
        return this.type;
    }

    public Direction getDirection() {
        return direction;
    }
    
}
