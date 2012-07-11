package pl.delusion.chess.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pl.delusion.chess.ui.component.LayeredGroup;

public class BoardUI extends GridPane {
    
    public static final String BOARD_KEY = "BOARD";
    private static final int LABEL_SIZE = 25;
    
    private LayeredGroup[][] boardSquares;

    public BoardUI(int width, int height, int boardSize) {
        setHgap(0);
        setVgap(0);
        
        boardSquares = new LayeredGroup[boardSize][boardSize];
        
        int xUnit = (width - 2 * LABEL_SIZE) / boardSize;
        int yUnit = (height - 2 * LABEL_SIZE) / boardSize;
        
        // draw board
        for(int i = 0; i < boardSize; i++) {
            // letters around board
            String number = "" + (boardSize - i);
            Label leftLabel = prepareNumberLabel(number);
            Label rightLabel = prepareNumberLabel(number);
            add(leftLabel, 0, i + 1);
            add(rightLabel, boardSize + 1, i + 1);
            
            String letter = "" + (char) ('a' + i);
            Label topLabel = prepareLetterLabel(letter, xUnit);
            Label bottomLabel = prepareLetterLabel(letter, xUnit);
            add(topLabel, i + 1, 0);
            add(bottomLabel, i + 1, boardSize + 1);
            
            // squares of board
            for(int j = 0; j < boardSize; j++) {
                Color color = (i + j) % 2 == 0 ? Color.web("#FFCE9E") : Color.web("#D18B47");
                
                LayeredGroup group = new LayeredGroup();
                
                Rectangle square = new Rectangle(xUnit, yUnit);
                square.setFill(color);
                square.setStroke(Color.BLACK);
                
                group.putChild(BOARD_KEY, square);
                
                add(group, j + 1, i + 1);
                
                boardSquares[i][j] = group;
            }
        }
    }

    public LayeredGroup[][] getBoardSquares() {
        return boardSquares;
    }

    private Label prepareNumberLabel(String number) {
        Label result = new Label(number);
        result.setFont(Font.font(null, FontWeight.BOLD, 12));
        result.setMinWidth(LABEL_SIZE);
        result.setAlignment(Pos.CENTER);
        
        return result;
    }

    private Label prepareLetterLabel(String letter, int squareWidth) {
        Label result = new Label(letter);
        result.setFont(Font.font(null, FontWeight.BOLD, 12));
        result.setMinWidth(squareWidth);
        result.setMinHeight(LABEL_SIZE);
        result.setAlignment(Pos.CENTER);
        
        return result;
    }
    
}
