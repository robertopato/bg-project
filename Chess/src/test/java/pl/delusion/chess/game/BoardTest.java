package pl.delusion.chess.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import pl.delusion.chess.game.movement.MovementStrategy;

public class BoardTest {
    
    private Board board;
    
    private PieceLogicFactory pieceLogicFactory;
    private MovementStrategy movementStrategy;
    
    @BeforeMethod
    public void beforeMethod() {
        board = new Board();
        
        pieceLogicFactory = mock(PieceLogicFactory.class);
        movementStrategy = mock(MovementStrategy.class);
        
        when(pieceLogicFactory.getMovementStrategy(any(PieceType.class))).thenReturn(movementStrategy);
        when(movementStrategy.checkMove(anyInt(), anyInt(), anyInt(), anyInt(), any(Piece.class), any(PieceLocator.class))).thenReturn(true);
        
        board.setPieceLogicFactory(pieceLogicFactory);
    }
    
    @Test
    public void boardHasEightRows() {
        // given
        
        // when
        int rows = board.getSquares().length;
        
        // then
        assert rows == 8;
    }
    
    @Test
    public void eachRowHasEightColumns() {
        // given
        
        for(int i = 0; i < 8; i++) {
            // when
            int columns = board.getSquares()[i].length;
            
            // then
            assert columns == 8;
        }
    }
    
    @Test
    public void youCanPutPiecesOnABoard() {
        // given
        Piece piece = mock(Piece.class);
        
        // when
        board.put(0, 1, piece);
        
        // then
        assert board.getPiece(0, 1) == piece;
    }
    
    @Test
    public void youCanMovePiecesOnABoard() throws IllegalMoveException {
        // given
        Piece piece = mock(Piece.class);
        board.put(0, 1, piece);
        
        // when
        board.move(0, 1, 4, 5);
        
        // then
        assert board.getPiece(0, 1) == null;
        assert board.getPiece(4, 5) == piece;
    }
    
    @Test
    public void boardReturnedByGetSquaresIsImmutable() {
        // given
        Piece piece = mock(Piece.class);
        
        // when
        board.getSquares()[1][1] = piece;
        
        // then
        assert board.getPiece(1, 1) == null;
    }
    
    @Test
    public void viewOfSquaresReturnedByGetSquaresIsCorrect() {
        // given
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.put(i, j, mock(Piece.class));
            }
        }
        
        // when
        Piece[][] squares = board.getSquares();
        
        // then
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assert board.getPiece(i, j) == squares[i][j];
            }
        }
    }
    
    @Test
    public void initializedBoardContainsCorrectPieces() {
        // given
        
        // when
        board.init();
        
        // then
        assert board.getPiece(0, 0).getType() == PieceType.ROOK;
        assert board.getPiece(0, 1).getType() == PieceType.KNIGHT;
        assert board.getPiece(0, 2).getType() == PieceType.BISHOP;
        assert board.getPiece(0, 3).getType() == PieceType.QUEEN;
        assert board.getPiece(0, 4).getType() == PieceType.KING;
        assert board.getPiece(0, 5).getType() == PieceType.BISHOP;
        assert board.getPiece(0, 6).getType() == PieceType.KNIGHT;
        assert board.getPiece(0, 7).getType() == PieceType.ROOK;
        for(int j = 0; j < 8; j++) {
            assert board.getPiece(1, j).getType() == PieceType.PAWN;
        }
        
        for(int j = 6; j < 8; j++) {
            assert board.getPiece(1, j).getType() == PieceType.PAWN;
        }
        assert board.getPiece(7, 0).getType() == PieceType.ROOK;
        assert board.getPiece(7, 1).getType() == PieceType.KNIGHT;
        assert board.getPiece(7, 2).getType() == PieceType.BISHOP;
        assert board.getPiece(7, 3).getType() == PieceType.QUEEN;
        assert board.getPiece(7, 4).getType() == PieceType.KING;
        assert board.getPiece(7, 5).getType() == PieceType.BISHOP;
        assert board.getPiece(7, 6).getType() == PieceType.KNIGHT;
        assert board.getPiece(7, 7).getType() == PieceType.ROOK;
    }
    
    @Test
    public void initializedBoardContainsPiecesWithCorrectDirections() {
        // given
        
        // when
        board.init();
        
        // then
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 8; j++) {
                assert board.getPiece(i, j).getDirection() == Direction.GOING_DOWN;
            }
        }
        
        for(int i = 6; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                assert board.getPiece(i, j).getDirection() == Direction.GOING_UP;
            }
        }
    }
    
    @Test
    public void locatesPiecesOnBoard() {
        Piece upPiece = mock(Piece.class);
        when(upPiece.getDirection()).thenReturn(Direction.GOING_UP);
        
        assert board.hasPiece(5, 5) == null;
        board.put(5, 5, upPiece);
        assert board.hasPiece(5, 5) == Direction.GOING_UP;
    }
    
    @Test
    public void attemptToLocatePieceBeyondBoardReturnsFalse() {
        assert board.hasPiece(-1, 0) == null;
        assert board.hasPiece(0, -1) == null;
        assert board.hasPiece(8, 0) == null;
        assert board.hasPiece(0, 8) == null;
        assert board.hasPiece(-1, -1) == null;
        assert board.hasPiece(8, 8) == null;
    }
    
    @Test
    public void boardHasSize() {
        // given
        
        // when
        int size = board.getSize();
        
        // then
        assert size == Board.SIZE;
    }
    
    @Test
    public void findsPiecesOnBoard() {
        Piece upPiece = mock(Piece.class);
        Piece downPiece = mock(Piece.class);
        
        board.put(3, 3, upPiece);
        
        assert board.findPiece(upPiece).equals(new Coordinates(3, 3));
        assert board.findPiece(downPiece) == null;
    }
    
    @Test
    public void boardChecksIfMoveIsPermittedBeforeApplyingIt() throws IllegalMoveException {
        // given
        Piece piece = mock(Piece.class);
        when(piece.getType()).thenReturn(PieceType.PAWN);
        board.put(3, 3, piece);
        
        when(movementStrategy.checkMove(3, 3, 4, 4, piece, board)).thenReturn(true);
        
        assert board.getPiece(3, 3) == piece;
        assert board.getPiece(4, 4) == null;
        
        // when
        board.move(3, 3, 4, 4);
        
        // then
        assert board.getPiece(3, 3) == null;
        assert board.getPiece(4, 4) == piece;
        
        verify(movementStrategy).checkMove(3, 3, 4, 4, piece, board);
    }
    
    @Test(expectedExceptions = IllegalMoveException.class)
    public void boardWillNotApplyAnImpossibleMove() throws IllegalMoveException {
        // given
        Piece piece = mock(Piece.class);
        when(piece.getType()).thenReturn(PieceType.PAWN);
        board.put(3, 3, piece);
        
        when(movementStrategy.checkMove(3, 3, 4, 4, piece, board)).thenReturn(false);
        
        // when
        board.move(3, 3, 4, 4);
        
        // then exception...
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void cannotMoveFromAnEmptySquare() throws IllegalMoveException {
        // given
        assert board.getPiece(4, 4) == null;
        
        // when
        board.move(4, 4, 5, 5);
        
        // then exception...
    }
    
    @Test
    public void movingAPieceSendsItAMoveSignal() throws IllegalMoveException {
        // given
        Piece piece = mock(Piece.class);
        when(piece.getType()).thenReturn(PieceType.PAWN);
        board.put(3, 3, piece);
        
        // when
        board.move(3, 3, 4, 4);
        
        // then
        verify(piece).move();
    }
    
    @Test
    public void movingAPieceReturnsNullIfMovedToEmptySpace() throws IllegalMoveException {
        // given
        Piece piece = mock(Piece.class);
        when(piece.getType()).thenReturn(PieceType.PAWN);
        board.put(3, 3, piece);
        
        // when
        Piece result = board.move(3, 3, 4, 4);
        
        // then
        assert result == null;
    }
    
    @Test
    public void movingAPieceReturnsAPieceThatHasBeenKilledIfAny() throws IllegalMoveException {
        // given
        Piece from = mock(Piece.class);
        when(from.getType()).thenReturn(PieceType.PAWN);
        board.put(3, 3, from);
        
        Piece to = mock(Piece.class);
        when(to.getType()).thenReturn(PieceType.PAWN);
        board.put(4, 4, to);
        
        // when
        Piece result = board.move(3, 3, 4, 4);
        
        // then
        assert result == to;
    }
    
}
