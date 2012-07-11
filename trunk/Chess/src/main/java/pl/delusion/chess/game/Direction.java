package pl.delusion.chess.game;

public enum Direction {
    
    GOING_UP(-1), 
    GOING_DOWN(1);
    
    private int yIncrement;

    private Direction(int yIncrement) {
        this.yIncrement = yIncrement;
    }

    public int getYIncrement() {
        return yIncrement;
    }
    
}
