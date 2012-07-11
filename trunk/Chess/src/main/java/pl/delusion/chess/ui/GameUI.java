package pl.delusion.chess.ui;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pl.delusion.chess.game.*;
import pl.delusion.chess.ui.UsedPiecesPane.Orientation;
import pl.delusion.chess.ui.component.CurrentPlayerIndicator;

public class GameUI extends VBox {
    
    public static final Map<Direction, PieceColor> COLOR_MAPPING;
    
    static {
        Map<Direction, PieceColor> temporary = new EnumMap<Direction, PieceColor>(Direction.class);
        temporary.put(Direction.GOING_DOWN, PieceColor.BLACK);
        temporary.put(Direction.GOING_UP, PieceColor.WHITE);
        
        COLOR_MAPPING = Collections.unmodifiableMap(temporary);
    }
      
    private ChessGame chessGame = new ChessGame(new Board());
    
    private CurrentPlayerIndicator currentPlayerIndicator;
    private BoardUI boardUI;
    private PiecesOverlay piecesOverlay;
    private UsedPiecesPane blackPane;
    private UsedPiecesPane whitePane;

    public GameUI(int width, int height) {
        chessGame.start();
        
        getChildren().add(initTop(width, 30));
        getChildren().add(initContents(width, height - 30));
    }
    
    private Node initTop(int width, int height) {
        currentPlayerIndicator = new CurrentPlayerIndicator(width, height);
        return currentPlayerIndicator;
    }

    private Node initContents(int width, int height) {
        GridPane contents = new GridPane();
        
        int boardSize = width * 2 / 3;
        
        if(boardSize != height) {
            throw new IllegalArgumentException("incorrect size ratio");
        }
        
        int usedPiecesPaneWidth = (width - boardSize) / 2;
        int usedPiecesPaneHeight = height / 2;
        
        blackPane = new UsedPiecesPane(usedPiecesPaneWidth, usedPiecesPaneHeight, Direction.GOING_DOWN, Orientation.TOP);
        contents.add(blackPane, 0, 0);
        
        boardUI = new BoardUI(boardSize, height, chessGame.getBoard().getSize());
        contents.add(boardUI, 1, 0);
        
        whitePane = new UsedPiecesPane(usedPiecesPaneWidth, usedPiecesPaneHeight, Direction.GOING_UP, Orientation.BOTTOM);
        contents.add(whitePane, 2, 0);
        
        piecesOverlay = new PiecesOverlay(boardUI, chessGame);
        piecesOverlay.redraw();
        
        piecesOverlay.addPieceRemovedFromBoardListener(blackPane);
        piecesOverlay.addPieceRemovedFromBoardListener(whitePane);
        
        chessGame.addPlayerChangedListener(currentPlayerIndicator);
        currentPlayerIndicator.playerChanged(chessGame.getCurrentPlayer());
        
        return contents;
    }
    
}
