package pl.delusion.chess;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.delusion.chess.ui.GameUI;

public class Chess extends Application {
    
    private static final int WIDTH = 725;
    private static final int HEIGHT = 530;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, WIDTH, HEIGHT);
        
        grid.add(new GameUI(WIDTH - 50, HEIGHT - 50), 0, 0);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
