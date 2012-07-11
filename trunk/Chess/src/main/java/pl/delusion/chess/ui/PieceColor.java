package pl.delusion.chess.ui;

import javafx.scene.paint.Color;

public enum PieceColor {
    
    BLACK(Color.BLACK), 
    WHITE(Color.WHITE);
    
    private Color color;

    private PieceColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
