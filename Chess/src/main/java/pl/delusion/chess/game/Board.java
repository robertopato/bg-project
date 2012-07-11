package pl.delusion.chess.game;

import java.util.Arrays;
import pl.delusion.chess.game.movement.MovementStrategy;

public class Board implements PieceLocator {
    
    public static final int SIZE = 8;
    
    private PieceLogicFactory pieceLogicFactory = new PieceLogicFactory();
    private Piece[][] squares  = new Piece[SIZE][SIZE];
    
    public Piece[][] getSquares() {
        Piece[][] result = new Piece[SIZE][];
        
        for(int i = 0; i < SIZE; i++) {
            result[i] = Arrays.copyOf(squares[i], squares[i].length);
        }
        
        return result;
    }

    public Piece getPiece(int i, int j) {
        return squares[i][j];
    }

    public Direction hasPiece(int x, int y) {
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return null;
        }
        
        return getPiece(x, y) == null ? null : getPiece(x, y).getDirection();
    }
    
    public int getSize() {
        return Board.SIZE;
    }

    public Coordinates findPiece(Piece piece) {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if(piece.equals(getPiece(i, j))) {
                    return new Coordinates(i, j);
                }
            }
        }
        
        return null;
    }

    void init() {
        squares[0][0] = new Piece(PieceType.ROOK, Direction.GOING_DOWN);
        squares[0][1] = new Piece(PieceType.KNIGHT, Direction.GOING_DOWN);
        squares[0][2] = new Piece(PieceType.BISHOP, Direction.GOING_DOWN);
        squares[0][3] = new Piece(PieceType.QUEEN, Direction.GOING_DOWN);
        squares[0][4] = new Piece(PieceType.KING, Direction.GOING_DOWN);
        squares[0][5] = new Piece(PieceType.BISHOP, Direction.GOING_DOWN);
        squares[0][6] = new Piece(PieceType.KNIGHT, Direction.GOING_DOWN);
        squares[0][7] = new Piece(PieceType.ROOK, Direction.GOING_DOWN);
        for(int j = 0; j < SIZE; j++) {
            squares[1][j] = new Piece(PieceType.PAWN, Direction.GOING_DOWN);
        }
        
        for(int j = 0; j < SIZE; j++) {
            squares[6][j] = new Piece(PieceType.PAWN, Direction.GOING_UP);
        }
        squares[7][0] = new Piece(PieceType.ROOK, Direction.GOING_UP);
        squares[7][1] = new Piece(PieceType.KNIGHT, Direction.GOING_UP);
        squares[7][2] = new Piece(PieceType.BISHOP, Direction.GOING_UP);
        squares[7][3] = new Piece(PieceType.QUEEN, Direction.GOING_UP);
        squares[7][4] = new Piece(PieceType.KING, Direction.GOING_UP);
        squares[7][5] = new Piece(PieceType.BISHOP, Direction.GOING_UP);
        squares[7][6] = new Piece(PieceType.KNIGHT, Direction.GOING_UP);
        squares[7][7] = new Piece(PieceType.ROOK, Direction.GOING_UP);
    }

    void put(int i, int j, Piece piece) {
        squares[i][j] = piece;
    }

    Piece move(int iFrom, int jFrom, int iTo, int jTo) throws IllegalMoveException {
        Piece piece = getPiece(iFrom, jFrom);
        
        if(piece == null) {
            throw new IllegalArgumentException();
        }
        
        MovementStrategy movementStrategy
                = pieceLogicFactory.getMovementStrategy(piece.getType());
        
        if(movementStrategy.checkMove(iFrom, jFrom, iTo, jTo, piece, this)) {
            Piece atDestination = squares[iTo][jTo];
            
            squares[iTo][jTo] = squares[iFrom][jFrom];
            squares[iFrom][jFrom] = null;
            
            piece.move();
            
            return atDestination;
            
        } else {
            throw new IllegalMoveException();
            
        }
    }

    void setPieceLogicFactory(PieceLogicFactory pieceLogicFactory) {
        this.pieceLogicFactory = pieceLogicFactory;
    }
    
}
