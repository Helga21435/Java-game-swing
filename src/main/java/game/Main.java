package game;

import game.engine.GameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Запуск GUI в потоке обработки событий Swing (правильная практика)
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
