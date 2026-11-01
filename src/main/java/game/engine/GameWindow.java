package game.engine;

import javax.swing.*;

/**
 * Главное окно приложения.
 */
public class GameWindow extends JFrame {
    // Частота тиков игрового цикла
    private static final int TICK_DELAY_MS = 1000;
    private final Scene scene;
    
    public GameWindow() {
        // настраиваем размеры игрового поля
        int rows = 15;
        int cols = 20;
        int cellSize = 30;
        
        // создаём сетку, которая хранит данные - где какой квадрат, каким цветом закрашен
        var gridRenderer = new GridRenderer(rows, cols, cellSize);
        
        scene = new Scene(gridRenderer);
        // добавляем все объекты на сцену
        scene.addGameObjects();
        // зовём у всех объектов init
        scene.awake();
        
        setTitle("Java Grid Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(gridRenderer);
        pack();
        setLocationRelativeTo(null);
        
        // Ввод с клавиатуры: дальше его можно читать из любого GameObject через Keyboard.*
        Keyboard.init();
        scene.render();
        
        setVisible(true);
        
        // Запускаем игровой цикл.
        // Timer работает в отдельном thread'е Swing. Там можно обрабатывать любые события ввода
        // (мышь, клавиатура и т.п.)
        Timer gameLoop = new Timer(TICK_DELAY_MS, e -> update());
        gameLoop.start();
    }
    
    /**
     * Вызывается на каждом тике игрового цикла.
     * Сюда в будущем можно добавить логику, которая должна выполняться
     * постоянно: движение по таймеру, анимации, спавн объектов, проверку
     * столкновений и т.п.
     */
    private void update() {
        // пока пусто — заглушка под будущую логику
        
        // считаем, сколько времени прошло с прошлого тика (для Time.deltaTime())
        Time.tick();

        scene.update();
        scene.render();

        // сбрасываем флаги "нажато/отпущено в этом кадре"
        Keyboard.endFrame();
    }
}
