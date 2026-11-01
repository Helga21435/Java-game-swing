package game.entities;

import game.engine.GameObject;
import game.engine.GridRenderer;
import java.awt.*;

public class InitialField extends GameObject {
    private int rows;
    private int cols;
    
    public InitialField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    
    @Override
    protected void render(GridRenderer renderer) {
        example(renderer);
    }
    
    private void example(GridRenderer renderer) {
        renderer.drawCellInstantly(0, 0, Color.BLUE);
        renderer.drawCellInstantly(rows - 1, cols - 1, Color.GREEN);
    }
    
    private void drawLinesWithHoles(GridRenderer renderer) {
        int[] numbers = new int[cols];
        int length = numbers.length;
        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 0) {
                numbers[i] = 1;
            } else {
                numbers[i] = 0;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1) {
                renderer.drawCellInstantly(0, i, Color.RED);
            }
        }
        
    }
    
    private void drawChess(GridRenderer renderer) {
        int[][] matrix = new int[cols][rows];
        for (int i = 0; i < matrix.length; i++) {
            
            for (int j = 0; j < matrix[i].length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        matrix[i][j] = 1;
                    }
                    
                } else {
                    if (j % 2 != 0) {
                        matrix[i][j] = 1;
                    }
                    
                }
            }
            
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    renderer.drawCellInstantly(j, i, Color.BLACK);
                }
            }
        }
    }
    
    private void drawRectangle1(GridRenderer renderer) {
        int[][] rectangle = new int[cols][rows];
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle[i].length; j++) { // [i]
                
                if (i == 0 || i == rectangle.length - 1) {
                    rectangle[i][j] = 1;
                }
                if (j == 0 || j == rectangle[i].length - 1) {
                    rectangle[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle[i].length; j++) {
                if (rectangle[i][j] == 1) {
                    renderer.drawCellInstantly(j, i, Color.BLACK);
                }
            }
        }
    }
}
