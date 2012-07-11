package pl.delusion.chess.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class ChessGameTest {
    
    private ChessGame chessGame;
    private Board board;
    private PlayerChangedListener playerChangedListener;
    
    @BeforeMethod
    public void beforeMethod() {
        board = mock(Board.class);
        playerChangedListener = mock(PlayerChangedListener.class);
        
        chessGame = new ChessGame(board);
        chessGame.addPlayerChangedListener(playerChangedListener);
    }
    
    @Test
    public void chessGameContainsABoard() {
        // given
        
        // when
        Board got = chessGame.getBoard();
        
        // then
        assert got == board;
    }
    
    @Test
    public void startingGameInitializesBoard() {
        // given
        
        // when
        chessGame.start();
        
        // then
        verify(board).init();
    }
    
    @Test
    public void stopsAStartedGame() {
        assert !chessGame.isStarted();
        chessGame.start();
        assert chessGame.isStarted();
        chessGame.stop();
        assert !chessGame.isStarted();
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void cannotStartAStartedGame() {
        // given
        
        // when
        chessGame.start();
        chessGame.start();
        
        // then exception...
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void cannotStopAStoppedGame() {
        // given
        
        // when
        chessGame.stop();
        
        // then exception...
    }
    
    @Test
    public void goingDownIsTheDefaultDirection() {
        // given
        
        // when
        
        // then
        assert Direction.GOING_DOWN == chessGame.getCurrentPlayer();
    }
    
    private static final Direction DIRECTION = Direction.GOING_DOWN;
    
    @Test
    public void knowsWhoIsTheCurrentPlayer() {
        // given
        chessGame.setCurrentPlayer(DIRECTION);
        
        // when
        Direction direction = chessGame.getCurrentPlayer();
        
        // then
        assert direction == DIRECTION;
    }
    
    private static final int FROM_X = 1;
    private static final int FROM_Y = 2;
    private static final int TO_X = 3;
    private static final int TO_Y = 4;
    
    @Test
    public void delegatesMoveToBoard() throws IllegalMoveException {
        // given
        
        // when
        chessGame.move(FROM_X, FROM_Y, TO_X, TO_Y);
        
        // then
        verify(board).move(FROM_X, FROM_Y, TO_X, TO_Y);
    }
    
    @Test(expectedExceptions = IllegalMoveException.class)
    public void delegatesIllegalMoveExceptionsComingFromBoard() throws IllegalMoveException {
        // given
        doThrow(IllegalMoveException.class).when(board).move(FROM_X, FROM_Y, TO_X, TO_Y);
        
        // when
        chessGame.move(FROM_X, FROM_Y, TO_X, TO_Y);
        
        // then exception...
    }
    
    @Test
    public void successfulMoveChangesCurrentPlayer() throws IllegalMoveException {
        // given
        
        // when
        assert Direction.GOING_DOWN == chessGame.getCurrentPlayer();
        chessGame.move(FROM_X, FROM_Y, TO_X, TO_Y);
        
        // then
        assert Direction.GOING_UP == chessGame.getCurrentPlayer();
    }
    
    @Test
    public void returnsPieceReturnedByBoardWhenMoving() throws IllegalMoveException {
        // given
        Piece piece = mock(Piece.class);
        when(board.move(FROM_X, FROM_Y, TO_X, TO_Y)).thenReturn(piece);
        
        // when
        Piece result = chessGame.move(FROM_X, FROM_Y, TO_X, TO_Y);
        
        // then
        assert result == piece;
    }
    
    @Test
    public void sendsPlayerChangedEventsAfterMove() throws IllegalMoveException {
        // given
        
        // when
        chessGame.move(FROM_X, FROM_Y, TO_X, TO_Y);
        
        // then
        verify(playerChangedListener).playerChanged(Direction.GOING_UP);
    }
    
}
