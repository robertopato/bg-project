package pl.delusion.chess.ui.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pl.delusion.chess.game.Direction;
import pl.delusion.chess.game.PlayerChangedListener;
import pl.delusion.chess.ui.GameUI;

public class CurrentPlayerIndicator extends HBox implements PlayerChangedListener {
    
    private Label label;
    private Circle square;

    public CurrentPlayerIndicator(int width, int height) {
        setMinWidth(width);
        setMinHeight(height);
        
        setAlignment(Pos.BASELINE_CENTER);
        
        this.label = new Label("Current player:");
        this.label.setFont(Font.font(null, FontWeight.BOLD, 15));
        
        this.square = new Circle(height / 4);
        this.square.setStroke(Color.BLACK);
        
        getChildren().add(this.label);
        getChildren().add(this.square);
        
        setMargin(this.label, new Insets(0, 5, 0, 0));
        setMargin(this.square, new Insets(2, 0, 0, 0));
    }

    public void playerChanged(Direction newDirection) {
        this.square.setFill(GameUI.COLOR_MAPPING.get(newDirection).getColor());
    }
    
}
