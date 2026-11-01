package game.entities;

import game.engine.GameObject;
import game.engine.GridRenderer;
import game.engine.Keyboard;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Простой "игрок" — квадрат, который можно двигать по сетке.
 * Хранит свою позицию и цвет, умеет двигаться в пределах сетки.
 */
public class Player extends GameObject {
    private int row;
    private int col;
    private final Color color;
    // размеры игрового поля
    private int rowTotal;
    private int colTotal;
    
    public Player(int startRow, int startCol, Color color, int rowTotal, int colTotal) {
        this.row = startRow;
        this.col = startCol;
        this.color = color;
        this.rowTotal = rowTotal;
        this.colTotal = colTotal;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public Color getColor() {
        return color;
    }
    
    @Override
    protected void update() {
        if (Keyboard.isPressed(KeyEvent.VK_D)) {
            move(0, 1);
        } else if (Keyboard.isPressed(KeyEvent.VK_A)) {
            move(0, -1);
        } else if (Keyboard.isPressed(KeyEvent.VK_W)) {
            move(-1, 0);
        }  else if (Keyboard.isPressed(KeyEvent.VK_S)) {
            move(1, 0);
        }
    }
    
    @Override
    protected void render(GridRenderer renderer) {
        renderer.setCellColor(col, row, color);
    }
    
    /**
     * Попытаться сдвинуться на (deltaRow, deltaCol).
     * Если новая позиция за пределами сетки — движение не выполняется.
     */
    private void move(int deltaRow, int deltaCol) {
        int newRow = row + deltaRow;
        int newCol = col + deltaCol;
        
        if (newRow < 0) {
            newRow = rowTotal - 1;
        } else if (newRow >= rowTotal) {
            newRow = 0;
        }
        
        if (newCol < 0) {
            newCol = colTotal - 1;
        }  else if (newCol >= colTotal) {
            newCol = 0;
        }
        
        row = newRow;
        col = newCol;
    }
}
