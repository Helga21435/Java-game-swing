package game.engine;

import javax.swing.*;
import java.awt.*;

/**
 * Панель с сеткой квадратов.
 * Отвечает только за отрисовку и хранение цвета каждой клетки.
 * Логика игры (движение, ввод с клавиатуры и т.п.) находится в других классах.
 */
public class GridRenderer extends JPanel {
    private final int rows;
    private final int cols;
    private final int cellSize;
    private final Color[][] cellColors;
    private final String[][] cellTexts;
    private final Color[][] cellTextColors;
    // Цвета по умолчанию
    private static final Color DEFAULT_CELL_COLOR = Color.WHITE;
    private static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final Color GRID_LINE_COLOR = Color.GRAY;
    // Цвет, которым закрашивается клетка по клику мыши
    private Color clickColor = Color.ORANGE;
    
    public GridRenderer(int rows, int cols, int cellSize) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.cellColors = new Color[rows][cols];
        this.cellTexts = new String[rows][cols];
        this.cellTextColors = new Color[rows][cols];

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
    
    public void setCellColor(int x, int y, Color color) {
        if (!isInBounds(y, x)) {
            throw new IllegalArgumentException("Координаты вне сетки: row=" + y + ", col=" + x);
        }
        cellColors[y][x] = color;
    }
    
    /**
     * Написать текст (цифру, букву, любую строку) поверх клетки (x — столбец, y — строка).
     * Цвет текста по умолчанию — чёрный.
     */
    public void setCellText(int x, int y, String text) {
        setCellText(x, y, text, DEFAULT_TEXT_COLOR);
    }

    public void setCellText(int x, int y, String text, Color color) {
        if (!isInBounds(y, x)) {
            throw new IllegalArgumentException("Координаты вне сетки: row=" + y + ", col=" + x);
        }
        cellTexts[y][x] = text;
        cellTextColors[y][x] = color;
    }

    public void clear() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cellColors[r][c] = DEFAULT_CELL_COLOR;
                cellTexts[r][c] = null;
            }
        }
    }
    
    public void render() {
        repaint();
    }
    
    /**
     * Закрасить квадрат по координатам (row, col) в заданный цвет.
     * Координаты: row — строка (0..rows-1), col — столбец (0..cols-1).
     */
    public void drawCellInstantly(int row, int col, Color color) {
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
        drawCellInstantly(row, col, DEFAULT_CELL_COLOR);
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
        
        // Рисуем текст в клетках, по центру каждой
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(getFont().deriveFont(Font.BOLD, cellSize * 0.5f));
        FontMetrics fm = g2.getFontMetrics();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String text = cellTexts[r][c];
                if (text == null) {
                    continue;
                }
                int textX = c * cellSize + (cellSize - fm.stringWidth(text)) / 2;
                int textY = r * cellSize + (cellSize - fm.getHeight()) / 2 + fm.getAscent();
                g2.setColor(cellTextColors[r][c]);
                g2.drawString(text, textX, textY);
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
