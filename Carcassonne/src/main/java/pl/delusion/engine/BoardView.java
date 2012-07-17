package pl.delusion.engine;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;

public class BoardView extends ScrollPane {
    
    private static final int SIZE = 50;
    private static final Random RANDOM = new Random();
    
    private int[][] temporaryData = new int[SIZE][SIZE];
    
    private BoardViewContents contents;

    public BoardView(double width, double height) {        
//        setPrefSize(width, height);
//        
//        setHbarPolicy(ScrollBarPolicy.NEVER);
//        setVbarPolicy(ScrollBarPolicy.NEVER);
//        
//        initTemporaryData();
//        
//        this.contents = new BoardViewContents();
//        
//        setContent(contents);
//        contents.redraw(temporaryData);
//        
//        setOnKeyPressed(new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//                System.out.println("PRESSED " + event.getCode());
//                
//                switch(event.getCode()) {
//                    case LEFT:
//                        contents.translateXProperty().subtract(100);
//                        break;
//                    case RIGHT:
//                        contents.translateXProperty().add(100);
//                        break;
//                    case UP:
//                        contents.translateYProperty().subtract(100);
//                        break;
//                    case DOWN:
//                        contents.translateYProperty().add(100);
//                        break;
//                }
//                
//                contents.redraw(temporaryData);
//            }
//        });
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
