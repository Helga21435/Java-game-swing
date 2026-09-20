package game;

import java.awt.*;

public class InitialField {
    private GamePanel gamePanel;
    private int rows;
    private int cols;
    
    public InitialField(GamePanel gamePanel, int rows, int cols) {
        this.gamePanel = gamePanel;
        this.rows = rows;
        this.cols = cols;
    }
    
    public void fill() {
        drawRectangle1();
    }
    
    private void example() {
        gamePanel.setSquareColor(0, 0, Color.BLUE);
        gamePanel.setSquareColor(rows - 1, cols - 1, Color.GREEN);
    }
    
    private void drawLinesWithHoles() {
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
                gamePanel.setSquareColor(0, i, Color.RED);
            }
        }
        
    }
    private void drawChess() {
        int[][] matrix = new int[cols][rows];
        for (int i = 0; i < matrix.length; i++) {
            
            for (int j = 0; j < matrix[i].length; j++) {
                if (i % 2 == 0 ) {
                    if(j % 2 == 0) {
                        matrix[i][j] = 1;
                    }
                    
                }
                else {
                    if( j % 2 != 0) {
                        matrix[i][j] = 1;
                    }
                    
                }
            }
            
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    gamePanel.setSquareColor(j, i , Color.BLACK);
                }
            }
        }
    }
    
    private void drawRectangle1() {
        int [][] rectangle = new int[cols][rows];
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle[i].length; j++) { // [i]
                
                if (i == 0 || i == rectangle.length - 1 ) {
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
                    gamePanel.setSquareColor(j, i , Color.BLACK);
                }
            }
        }
    }
}