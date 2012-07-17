package pl.delusion.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class BoardViewContents extends Pane {
    
    private static final int SIZE = 50;
    private static final Random RANDOM = new Random();
    
    private int[][] temporaryData = new int[SIZE][SIZE];
    
    private BoardViewContents contents;

    public BoardViewContents() {
        initTemporaryData();
        redraw();
        
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                System.out.println("PRESSED " + event.getCode());
                
                switch(event.getCode()) {
                    case LEFT:
                        contents.translateXProperty().subtract(100);
                        break;
                    case RIGHT:
                        contents.translateXProperty().add(100);
                        break;
                    case UP:
                        contents.translateYProperty().subtract(100);
                        break;
                    case DOWN:
                        contents.translateYProperty().add(100);
                        break;
                }
                
                contents.redraw();
            }
        });
    }
    
    private void redraw() {        
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        for(int i = 0; i < temporaryData.length; i++) {
            for (int j = 0; j < temporaryData[i].length; j++) {
                if(temporaryData[i][j] > 0) {
                    Rectangle rectangle = new Rectangle(10, 10);
                    rectangle.relocate(i * 10, j * 10);
                    
                    rectangles.add(rectangle);
                }
            }
        }
        getChildren().addAll(rectangles);
    }
    
    private void initTemporaryData() {
        for (int i = 0; i < temporaryData.length; i++) {
            for (int j = 0; j < temporaryData[i].length; j++) {
                if(RANDOM.nextBoolean()) {
                    temporaryData[i][j] = 1;
                }
            }
        }
    }
    
}
