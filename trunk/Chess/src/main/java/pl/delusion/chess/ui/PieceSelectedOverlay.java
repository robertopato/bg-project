package pl.delusion.chess.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import pl.delusion.chess.game.Coordinates;
import pl.delusion.chess.game.Piece;
import pl.delusion.chess.game.PieceLocator;
import pl.delusion.chess.game.PieceLogicFactory;
import pl.delusion.chess.game.movement.Move;
import pl.delusion.chess.game.movement.MovementStrategy;
import pl.delusion.chess.ui.component.LayeredGroup;
import pl.delusion.chess.ui.util.ImageHelper;

public class PieceSelectedOverlay {
    
    public static final String SELECTED_KEY = "SELECTED";

    private PieceLogicFactory pieceLogicFactory = new PieceLogicFactory();
    private BoardUI boardUI;
    private PieceLocator pieceLocator;
    private Coordinates at;
    
    private List<LayeredGroup> overlayedGroups = new ArrayList<LayeredGroup>();
    private FadeTransition fadeTransition;

    public PieceSelectedOverlay(BoardUI boardUI, PieceLocator pieceLocator, Coordinates at) {
        this.boardUI = boardUI;
        this.pieceLocator = pieceLocator;
        this.at = at;
    }

    public void draw() {
        if (overlayedGroups == null) {
            throw new IllegalStateException();
        }
        
        runSelectedAnimation();
        drawAvailableMoves();
    }

    public void destroy() {
        if (overlayedGroups == null) {
            throw new IllegalStateException();
        }
        
        clearAvailableMoves();
        stopSelectedAnimation();
    }

    private void drawAvailableMoves() {
        Piece piece = pieceLocator.getPiece(at.getX(), at.getY());
        MovementStrategy movementStrategy = pieceLogicFactory.getMovementStrategy(piece.getType());

        List<Move> moves = movementStrategy.getPossibleMoves(at.getX(), at.getY(), piece, pieceLocator);
        for (Move move : moves) {
            LayeredGroup group = boardUI.getBoardSquares()[move.getX()][move.getY()];

            Piece pieceAt = pieceLocator.getPiece(move.getX(), move.getY());
            
            String imageSource = pieceAt != null ? "img/fire.png" : "img/selected.png";
            ImageView view = new ImageView(ImageHelper.getImage(imageSource));
            
            group.putChild(SELECTED_KEY, view);
            overlayedGroups.add(group);
        }
    }

    private void clearAvailableMoves() {
        for (LayeredGroup overlayedGroup : overlayedGroups) {
            overlayedGroup.removeChild(SELECTED_KEY);
        }

        overlayedGroups = null;
    }

    private void runSelectedAnimation() {
        fadeTransition = new FadeTransition(Duration.millis(750), getPieceOverlay());
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    private void stopSelectedAnimation() {
        fadeTransition.stop();        
        fadeTransition = null;
        
        getPieceOverlay().setOpacity(1.0);
    }
    
    private Node getPieceOverlay() {
        return boardUI.getBoardSquares()[at.getX()][at.getY()].getChild(PiecesOverlay.PIECES_KEY);
    }
    
}
