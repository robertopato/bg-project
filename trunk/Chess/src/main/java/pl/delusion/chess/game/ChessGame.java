package pl.delusion.chess.game;

public class ChessGame {
    
    private boolean started = false;
    
    private Board board;
    private Direction currentPlayer = Direction.GOING_DOWN;

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

    void setCurrentPlayer(Direction currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
}
