package game.entities;

import game.engine.GameObject;
import game.engine.GridRenderer;
import java.awt.*;

public class Mouse extends GameObject {
    private Color color = Color.GRAY;
    private int health;
    private int x;
    private int y;
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setHealth(int value) {
        health = value;
    }
    
    public void move() {
        x += 1;
        if (x >= 20) {
            x = 0;
        }
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    protected void update() {
        this.move();
    }
    
    @Override
    protected void render(GridRenderer renderer) {
        renderer.setCellColor(x, y, color);
    }
}
