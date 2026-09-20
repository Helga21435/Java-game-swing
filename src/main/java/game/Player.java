package game;

import java.awt.*;

/**
 * Простой "игрок" — квадрат, который можно двигать по сетке.
 * Хранит свою позицию и цвет, умеет двигаться в пределах сетки.
 */
public class Player {

    private int row;
    private int col;
    private final Color color;

    public Player(int startRow, int startCol, Color color) {
        this.row = startRow;
        this.col = startCol;
        this.color = color;
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

    /**
     * Попытаться сдвинуться на (deltaRow, deltaCol).
     * Если новая позиция за пределами сетки — движение не выполняется.
     */
    public void move(int deltaRow, int deltaCol, GamePanel panel) {
        int newRow = row + deltaRow;
        int newCol = col + deltaCol;

        if (!panel.isInBounds(newRow, newCol)) {
            return; // за пределами поля — игнорируем
        }

        // Очищаем старую клетку и закрашиваем новую
        panel.clearSquare(row, col);
        row = newRow;
        col = newCol;
        // panel.setSquareColor(row, col, color);
    }
}
