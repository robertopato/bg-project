package pl.delusion.chess.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.delusion.chess.game.*;
import pl.delusion.chess.ui.component.LayeredGroup;

public class PiecesOverlay {
    
    public static final String PIECES_KEY = "PIECES";
    
    private BoardUI boardUI;
    private ChessGame game;
    
    private Map<LayeredGroup, Coordinates> coordinates = new HashMap<LayeredGroup, Coordinates>();
    
    private Piece selectedPiece;
    private PieceSelectedOverlay pieceSelectedOverlay;
    private BoardGroupSelectedEventHandler boardGroupSelectedEventHandler = new BoardGroupSelectedEventHandler();
    private PieceOverlayProducer pieceOverlayProducer = new PieceOverlayProducer();
    
    private List<PieceRemovedFromBoardListener> pieceRemovedFromBoardListeners = new ArrayList<PieceRemovedFromBoardListener>();

    public PiecesOverlay(BoardUI boardUI, ChessGame game) {
        this.boardUI = boardUI;
        this.game = game;
        
        for (int i = 0; i < this.game.getBoard().getSize(); i++) {
            for (int j = 0; j < this.game.getBoard().getSize(); j++) {
                this.coordinates.put(this.boardUI.getBoardSquares()[i][j], new Coordinates(i, j));
            }
        }
    }
    
    public void redraw() {
        Board board = game.getBoard();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                LayeredGroup group = boardUI.getBoardSquares()[i][j];
                Piece piece = board.getPiece(i, j);
                
                group.removeChild(PIECES_KEY);
                
                if(piece != null) {
                    group.putChild(PIECES_KEY, pieceOverlayProducer.createPieceOverlay(piece));
                }
                
                // make sure we won't duplicate the event handler
                group.removeEventHandler(MouseEvent.MOUSE_CLICKED, boardGroupSelectedEventHandler);
                group.addEventHandler(MouseEvent.MOUSE_CLICKED, boardGroupSelectedEventHandler);
            }
        }
    }
    
    public void addPieceRemovedFromBoardListener(PieceRemovedFromBoardListener pieceRemovedFromBoardListener) {
        this.pieceRemovedFromBoardListeners.add(pieceRemovedFromBoardListener);
    }

    private class BoardGroupSelectedEventHandler implements EventHandler<MouseEvent> {

        public void handle(MouseEvent event) {
            Board board = game.getBoard();
            Coordinates clickedAt = coordinates.get((LayeredGroup) event.getSource());
            Piece destination = board.getPiece(clickedAt.getX(), clickedAt.getY());
            
            // no change in selection - do nothing
            if(destination == selectedPiece) {
                return;
            }
            
            // there was a change of selection so remove the previous selected overlay (if any)
            if(pieceSelectedOverlay != null) {
                pieceSelectedOverlay.destroy();
                pieceSelectedOverlay = null;
            }
            
            // something has been selected previously
            if(selectedPiece != null) {
                // current player changes piece
                if(destination != null && destination.getDirection() == game.getCurrentPlayer()) {
                    drawSelectedOverlay(destination, board, clickedAt);
                    
                // otherwise it's a move attempt
                } else {
                    Coordinates wasAt = board.findPiece(selectedPiece);
                    
                    try {
                        Piece removed = game.move(wasAt.getX(), wasAt.getY(), clickedAt.getX(), clickedAt.getY());
                        
                        if(removed != null) {
                            for(PieceRemovedFromBoardListener pieceRemovedFromBoardListener : pieceRemovedFromBoardListeners) {
                                pieceRemovedFromBoardListener.pieceRemovedFromBoard(removed);
                            }
                        }
                        
                        // successful move - redraw board
                        eraseSelectedOverlay();
                        redraw();
                        
                    } catch(IllegalMoveException ex) {
                        // unsuccessful move - unselect the current piece
                        eraseSelectedOverlay();
                        
                    }
                    
                }
                
            // no previous selection and clicked on a piece belonging to current player
            } else if(destination != null && destination.getDirection() == game.getCurrentPlayer()) {
                    drawSelectedOverlay(destination, board, clickedAt);
                    
            }
        }

        private void drawSelectedOverlay(Piece piece, Board board, Coordinates at) {
            pieceSelectedOverlay = new PieceSelectedOverlay(boardUI, board, at);
            pieceSelectedOverlay.draw();

            selectedPiece = piece;
        }

        private void eraseSelectedOverlay() {
            if(pieceSelectedOverlay != null) {
                pieceSelectedOverlay.destroy();
                pieceSelectedOverlay = null;
            }
            
            selectedPiece = null;
        }
        
    }
    
}
