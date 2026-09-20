package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Панель с сеткой квадратов.
 * Отвечает только за отрисовку и хранение цвета каждой клетки.
 * Логика игры (движение, ввод с клавиатуры и т.п.) находится в других классах.
 */
public class GamePanel extends JPanel {
    private final int rows;
    private final int cols;
    private final int cellSize;
    private final Color[][] cellColors;
    // Цвета по умолчанию
    private static final Color DEFAULT_CELL_COLOR = Color.WHITE;
    private static final Color GRID_LINE_COLOR = Color.GRAY;
    // Цвет, которым закрашивается клетка по клику мыши
    private Color clickColor = Color.ORANGE;
    
    public GamePanel(int rows, int cols, int cellSize) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.cellColors = new Color[rows][cols];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cellColors[r][c] = DEFAULT_CELL_COLOR;
            }
        }
        
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        setBackground(DEFAULT_CELL_COLOR);
        
        // Клик по клетке закрашивает её (или сбрасывает, если она уже закрашена)
        /*
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;
                if (!isInBounds(row, col)) {
                    return;
                }
                if (cellColors[row][col].equals(clickColor)) {
                    clearSquare(row, col); // повторный клик — снимает закраску
                } else {
                    setSquareColor(row, col, clickColor);
                }
            }
        }); */
    }
    
    /**
     * Задать цвет, которым будет закрашиваться клетка по клику мыши.
     */
    public void setClickColor(Color color) {
        this.clickColor = color;
    }
    
    /**
     * Закрасить квадрат по координатам (row, col) в заданный цвет.
     * Координаты: row — строка (0..rows-1), col — столбец (0..cols-1).
     */
    public void setSquareColor(int row, int col, Color color) {
        if (!isInBounds(row, col)) {
            throw new IllegalArgumentException("Координаты вне сетки: row=" + row + ", col=" + col);
        }
        cellColors[row][col] = color;
        repaint();
    }
    
    /**
     * Сбросить квадрат к цвету по умолчанию.
     */
    public void clearSquare(int row, int col) {
        setSquareColor(row, col, DEFAULT_CELL_COLOR);
    }
    
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Закрашиваем клетки
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2.setColor(cellColors[r][c]);
                g2.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
        
        // Рисуем линии сетки
        g2.setColor(GRID_LINE_COLOR);
        for (int r = 0; r <= rows; r++) {
            g2.drawLine(0, r * cellSize, cols * cellSize, r * cellSize);
        }
        for (int c = 0; c <= cols; c++) {
            g2.drawLine(c * cellSize, 0, c * cellSize, rows * cellSize);
        }
    }
}
