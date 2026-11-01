package game.engine;

import game.entities.InitialField;
import game.entities.Player;
import game.entities.Mouse;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    private final List<GameObject> gameObjects = new LinkedList<GameObject>();
    private final GridRenderer renderer;
    
    public Scene(GridRenderer renderer) {
        this.renderer = renderer;
    }
    
    public void addGameObjects() {
        var mouse = new Mouse();
        var player = new Player(
            renderer.getRows() / 2,
            renderer.getCols() / 2,
            Color.RED,
            renderer.getRows(),
            renderer.getCols()
        );
        var initialField = new InitialField(renderer.getRows(), renderer.getCols());
        
        gameObjects.add(initialField);
        gameObjects.add(mouse);
        gameObjects.add(player);
    }
    
    public void awake() {
        gameObjects.forEach(obj -> {
            obj.awake();
        });
    }
    
    public void update() {
        gameObjects.forEach(obj -> {
            obj.update();
        });
    }
    
    public void render() {
        renderer.clear();
        gameObjects.forEach(obj -> {
            obj.render(renderer);
        });
        renderer.render();
    }
}
