package pl.delusion.chess.ui.component;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Node;

public class LayeredGroup extends Group {
    
    private Map<String, Node> children = new HashMap<String, Node>();
    
    public void putChild(String key, Node value) {
        if(children.get(key) == null) {
            getChildren().add(value);
            
        } else {
            getChildren().set(getChildren().indexOf(children.get(key)), value);
            
        }
        
        children.put(key, value);
    }
    
    public Node getChild(String key) {
        return children.get(key);
    }
    
    public void removeChild(String key) {
        getChildren().remove(children.remove(key));
    }
    
}
