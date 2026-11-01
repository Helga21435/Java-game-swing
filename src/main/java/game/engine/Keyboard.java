package game.engine;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * Состояние клавиатуры в стиле Unity Input.
 * <p>
 * Использование (коды клавиш — это KeyEvent.VK_*):
 * <pre>
 *   if (Keyboard.wasPressedThisFrame(KeyEvent.VK_SPACE)) { ... } // нажали в этом тике
 *   if (Keyboard.isPressed(KeyEvent.VK_W)) { ... }               // удерживается
 *   if (Keyboard.wasReleasedThisFrame(KeyEvent.VK_W)) { ... }    // отпустили в этом тике
 * </pre>
 * Нужно один раз вызвать {@link #init()} и в конце каждого тика игрового цикла
 * (после scene.update()) вызывать {@link #endFrame()}.
 * События клавиатуры и Timer работают в одном потоке (EDT), поэтому синхронизация не нужна.
 */
public final class Keyboard {
    private static final Set<Integer> down = new HashSet<>();
    private static final Set<Integer> pressedThisFrame = new HashSet<>();
    private static final Set<Integer> releasedThisFrame = new HashSet<>();
    private static boolean initialized;
    
    private Keyboard() {
    }
    
    /**
     * Подписывается на клавиатуру всего приложения, не зависит от того, какой компонент в фокусе.
     */
    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED -> {
                    // add вернёт false, если клавиша уже зажата (автоповтор ОС) — тогда это не новое нажатие
                    if (down.add(e.getKeyCode())) {
                        pressedThisFrame.add(e.getKeyCode());
                    }
                }
                case KeyEvent.KEY_RELEASED -> {
                    if (down.remove(e.getKeyCode())) {
                        releasedThisFrame.add(e.getKeyCode());
                    }
                }
            }
            return false; // не "съедаем" событие, остальные компоненты тоже его получат
        });
    }
    
    /**
     * Клавиша сейчас удерживается.
     */
    public static boolean isPressed(int keyCode) {
        return down.contains(keyCode);
    }
    
    /**
     * Клавишу нажали после предыдущего тика (true ровно один тик).
     */
    public static boolean wasPressedThisFrame(int keyCode) {
        return pressedThisFrame.contains(keyCode);
    }
    
    /**
     * Клавишу отпустили после предыдущего тика (true ровно один тик).
     */
    public static boolean wasReleasedThisFrame(int keyCode) {
        return releasedThisFrame.contains(keyCode);
    }
    
    /**
     * Сбрасывает флаги "в этом кадре". Вызывать в конце каждого тика.
     */
    public static void endFrame() {
        pressedThisFrame.clear();
        releasedThisFrame.clear();
    }
}
