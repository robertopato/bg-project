package pl.delusion.chess.ui;

import java.util.EnumMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import pl.delusion.chess.game.*;
import pl.delusion.chess.ui.UsedPiecesPane.Orientation;

public class GameUI extends GridPane {
    
    static final Map<Direction, PieceColor> COLOR_MAPPING 
            = new EnumMap<Direction, PieceColor>(Direction.class);
    
    static {
        COLOR_MAPPING.put(Direction.GOING_DOWN, PieceColor.BLACK);
        COLOR_MAPPING.put(Direction.GOING_UP, PieceColor.WHITE);
    }
      
    private ChessGame chessGame = new ChessGame(new Board());
    
    private BoardUI boardUI;
    private PiecesOverlay piecesOverlay;
    private UsedPiecesPane blackPane;
    private UsedPiecesPane whitePane;

    public GameUI(int width, int height) {
        chessGame.start();
        
        initUI(width, height);
    }

    private void initUI(int width, int height) {
        int boardSize = width * 2 / 3;
        
        if(boardSize != height) {
            throw new IllegalArgumentException("incorrect size ratio");
        }
        
        int usedPiecesPaneWidth = (width - boardSize) / 2;
        int usedPiecesPaneHeight = height / 2;
        
        blackPane = new UsedPiecesPane(usedPiecesPaneWidth, usedPiecesPaneHeight, Direction.GOING_DOWN, Orientation.TOP);
        add(blackPane, 0, 0);
        
        boardUI = new BoardUI(boardSize, height, chessGame.getBoard().getSize());
        add(boardUI, 1, 0);
        
        whitePane = new UsedPiecesPane(usedPiecesPaneWidth, usedPiecesPaneHeight, Direction.GOING_UP, Orientation.BOTTOM);
        add(whitePane, 2, 0);
        
        piecesOverlay = new PiecesOverlay(boardUI, chessGame);
        piecesOverlay.redraw();
        
        piecesOverlay.addPieceRemovedFromBoardListener(blackPane);
        piecesOverlay.addPieceRemovedFromBoardListener(whitePane);
    }
    
}
