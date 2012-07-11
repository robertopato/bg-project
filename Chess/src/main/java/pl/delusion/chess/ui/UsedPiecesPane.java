package pl.delusion.chess.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import pl.delusion.chess.game.Direction;
import pl.delusion.chess.game.Piece;

/**
 * @author keiran
 */
public class UsedPiecesPane extends GridPane implements PieceRemovedFromBoardListener {

    private Direction direction;
    
    private int currentX = 0;
    private int currentY = 0;
    
    private FlowPane contents;
    
    private PieceOverlayProducer pieceOverlayProducer = new PieceOverlayProducer();
    
    public UsedPiecesPane(int width, int height, Direction direction, Orientation orientation) {
        this.direction = direction;
        
        setAlignment(orientation == Orientation.TOP ? Pos.TOP_CENTER : Pos.BOTTOM_CENTER);

        this.contents = new FlowPane();
        add(this.contents, 0, 0);
    }

    public void pieceRemovedFromBoard(Piece piece) {
        if(piece.getDirection() == direction) {
            if(currentY == 1) {
                currentX++;
                currentY = 0;
            }
            
            this.contents.getChildren().add(pieceOverlayProducer.createPieceOverlay(piece));
        }
    }
    
    public static enum Orientation {
        TOP, BOTTOM
    }
    
}
