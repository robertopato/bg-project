package pl.delusion.chess.ui.util;

import javafx.scene.image.Image;

public final class ImageHelper {

    private ImageHelper() {
    }
    
    public static Image getImage(String path) {
        return new Image(ImageHelper.class.getClassLoader().getResourceAsStream(path));
    }
    
}
