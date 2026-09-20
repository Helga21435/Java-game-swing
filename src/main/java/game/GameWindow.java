package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Главное окно приложения.
 * Создаёт сетку, размещает на ней "игрока" и обрабатывает нажатия клавиш
 * (стрелки / WASD), чтобы двигать квадрат по полю.
 */
public class GameWindow extends JFrame {

    public GameWindow() {
        int rows = 15;
        int cols = 20;
        int cellSize = 30;

        GamePanel gamePanel = new GamePanel(rows, cols, cellSize);
        Player player = new Player(rows / 2, cols / 2, Color.RED);

        // Отрисовываем стартовую позицию игрока
        // gamePanel.setSquareColor(player.getRow(), player.getCol(), player.getColor());

        var initialField = new InitialField(gamePanel, rows, cols);
        initialField.fill();
        
        setTitle("Java Grid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);

        // Обработка клавиатуры — движение игрока
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP, KeyEvent.VK_W -> player.move(-1, 0, gamePanel);
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S -> player.move(1, 0, gamePanel);
                    case KeyEvent.VK_LEFT, KeyEvent.VK_A -> player.move(0, -1, gamePanel);
                    case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.move(0, 1, gamePanel);
                }
            }
        });

        setVisible(true);
    }
}
