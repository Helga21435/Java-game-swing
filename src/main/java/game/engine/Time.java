package game.engine;

/**
 * Аналог Unity Time: {@link #deltaTime()} — сколько секунд прошло с предыдущего тика.
 * <p>
 * В начале каждого тика игрового цикла нужно вызывать {@link #tick()}.
 * Движение, не зависящее от частоты тиков: {@code x += speed * Time.deltaTime()},
 * где speed — в клетках в секунду.
 */
public final class Time {
    // Защита от больших скачков (окно перетащили, приложение "подвисло" и т.п.)
    private static final float MAX_DELTA_TIME = 0.25f;

    private static long lastTickNanos = System.nanoTime();
    private static float deltaTime;
    private static float time;

    private Time() {
    }

    /**
     * Обновляет deltaTime. Вызывать один раз в начале каждого тика.
     */
    public static void tick() {
        long now = System.nanoTime();
        deltaTime = Math.min((now - lastTickNanos) / 1_000_000_000f, MAX_DELTA_TIME);
        lastTickNanos = now;
        time += deltaTime;
    }

    /**
     * Секунды, прошедшие с предыдущего тика.
     */
    public static float deltaTime() {
        return deltaTime;
    }

    /**
     * Секунды с начала игры (сумма всех deltaTime).
     */
    public static float time() {
        return time;
    }
}
