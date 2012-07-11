package pl.delusion.chess.ui;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import pl.delusion.chess.game.Piece;
import pl.delusion.chess.ui.util.ImageHelper;

public class PieceOverlayProducer {
    
    public Node createPieceOverlay(Piece piece) {
        return createPieceOverlay(piece, null, null);
    }
    
    public Node createPieceOverlay(Piece piece, Integer customWidth, Integer customHeight) {
        String playerName = GameUI.COLOR_MAPPING.get(piece.getDirection()).name().toLowerCase();
        String pieceName = piece.getType().name().toLowerCase();

        String imageSource 
                = "img/" + playerName + "_" + pieceName + ".png";
        ImageView view = new ImageView(ImageHelper.getImage(imageSource));
        
        if(customWidth != null) {
            view.setFitWidth(customWidth);
        }
        if(customHeight != null) {
            view.setFitHeight(customHeight);
        }

        return view;
    }
    
}
