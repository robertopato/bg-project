package pl.delusion.chess.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.delusion.chess.game.movement.Move;

import static org.mockito.Mockito.*;

public class CollisionDetectorImplTest {
    
    private static final Direction DIRECTION = Direction.GOING_DOWN;
    
    private CollisionDetectorImpl collisionDetector;
    private PieceLocator pieceLocator;
    
    @BeforeMethod
    public void beforeMethod() {
        collisionDetector = new CollisionDetectorImpl();
        
        pieceLocator = mock(PieceLocator.class);
    }
    
    private static final List<Move> ROUTE = Arrays.asList(new Move[] {
        new Move(4, 5),
        new Move(5, 5),
        new Move(6, 5),
        new Move(7, 5)
    });
    
    @Test
    public void returnsUpToCollisionWithAnybodyOnARouteWhereThereIsACollision() {
        // given
        when(pieceLocator.hasPiece(6, 5)).thenReturn(DIRECTION);
        
        // when
        List<Move> result = collisionDetector.upToCollisionWithAnybody(ROUTE, pieceLocator);
        
        // then
        assert result.equals(Arrays.asList(new Move[] {
            new Move(4, 5),
            new Move(5, 5)
        }));
    }
    
    @Test
    public void returnsWholeRouteWhenThereIsNoCollisionWithAnybody() {
        // given
        
        // when
        List<Move> result = collisionDetector.upToCollisionWithAnybody(ROUTE, pieceLocator);
        
        // then
        assert result.equals(ROUTE);
    }
    
    @Test
    public void returnsEmptyMovesListWhenThereIsACollisionWithAnybodyOnAFirstPartOfARoute() {
        // given
        when(pieceLocator.hasPiece(4, 5)).thenReturn(DIRECTION);
        
        // when
        List<Move> result = collisionDetector.upToCollisionWithAnybody(ROUTE, pieceLocator);
        
        // then
        assert result.equals(Collections.emptyList());
    }
    
    @Test
    public void returnsUpToCollisionWithTheChosenPlayerOnARouteWhereThereIsACollision() {
        // given
        when(pieceLocator.hasPiece(6, 5)).thenReturn(DIRECTION);
        
        // when
        List<Move> result = collisionDetector.upToCollisionWith(ROUTE, pieceLocator, DIRECTION);
        
        // then
        assert result.equals(Arrays.asList(new Move[] {
            new Move(4, 5),
            new Move(5, 5)
        }));
    }
    
    @Test
    public void returnsWholeRouteWhenThereIsNoCollisionWithTheChosenPlayer() {
        // given
        
        // when
        List<Move> result = collisionDetector.upToCollisionWith(ROUTE, pieceLocator, DIRECTION);
        
        // then
        assert result.equals(ROUTE);
    }
    
    @Test
    public void returnsEmptyMovesListWhenThereIsACollisionWithTheChosenPlayerOnAFirstPartOfARoute() {
        // given
        when(pieceLocator.hasPiece(4, 5)).thenReturn(DIRECTION);
        
        // when
        List<Move> result = collisionDetector.upToCollisionWith(ROUTE, pieceLocator, DIRECTION);
        
        // then
        assert result.equals(Collections.emptyList());
    }
    
}
