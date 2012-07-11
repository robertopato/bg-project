package pl.delusion.chess.game;

import org.testng.annotations.Test;

public class DirectionTest {
    
    @Test
    public void goingUpHasYIncrementOfMinusOne() {
        // given
        
        // when
        int increment = Direction.GOING_UP.getYIncrement();
        
        // then
        assert increment == -1;
    }
    
    @Test
    public void goingDownHasYIncrementOfOne() {
        // given
        
        // when
        int increment = Direction.GOING_DOWN.getYIncrement();
        
        // then
        assert increment == 1;
    }
    
}
