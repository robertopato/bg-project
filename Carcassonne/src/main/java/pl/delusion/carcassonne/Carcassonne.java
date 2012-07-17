package pl.delusion.carcassonne;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.delusion.engine.BoardViewContents;

public class Carcassonne extends Application {
    
    private static final double WINDOW_WIDTH = 1240;
    private static final double WINDOW_HEIGHT = 800;

    @Override
    public void start(Stage stage) throws Exception {
        BoardViewContents view 
                = new BoardViewContents();
        
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        
        borderPane.setCenter(view);
        
        stage.setTitle("Carcassonne");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
