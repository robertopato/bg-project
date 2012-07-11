package pl.delusion.chess.game;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    
    public static final Direction DEFAULT_DIRECTION = Direction.GOING_DOWN;
    
    private boolean started = false;
    
    private Board board;
    private Direction currentPlayer = DEFAULT_DIRECTION;
            
    private List<PlayerChangedListener> playerChangedListeners = new ArrayList<PlayerChangedListener>();

    public ChessGame(Board board) {
        this.board = board;
    }
    
    public void start() {
        if(started) {
            throw new IllegalStateException();
        }
        
        board.init();
        started = true;
    }
    
    public void stop() {
        if(!started) {
            throw new IllegalStateException();
        }
        started = false;
    }
    
    public Piece move(int fromX, int fromY, int toX, int toY) throws IllegalMoveException {
        Piece result = board.move(fromX, fromY, toX, toY);
        currentPlayer = (currentPlayer == Direction.GOING_DOWN ? Direction.GOING_UP : Direction.GOING_DOWN);
        
        for(PlayerChangedListener playerChangedListener : playerChangedListeners) {
            playerChangedListener.playerChanged(currentPlayer);
        }
        
        return result;
    }

    public boolean isStarted() {
        return started;
    }

    public Direction getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }
    
    public void addPlayerChangedListener(PlayerChangedListener playerChangedListener) {
        this.playerChangedListeners.add(playerChangedListener);
    }

    void setCurrentPlayer(Direction currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
}
