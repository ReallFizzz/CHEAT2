package dev.knockbacktrainer.config;

public class KnockbackConfig {
    private static float yawOffset = 45.0f;  // Угол поворота в градусах
    private static int delayTicks = 2;        // Задержка в тиках (1 тик = 50мс)
    
    // Границы значений
    public static final float MIN_YAW = -180.0f;
    public static final float MAX_YAW = 180.0f;
    public static final int MIN_DELAY = 0;
    public static final int MAX_DELAY = 20;
    
    public static float getYawOffset() {
        return yawOffset;
    }
    
    public static void setYawOffset(float value) {
        yawOffset = Math.max(MIN_YAW, Math.min(MAX_YAW, value));
    }
    
    public static int getDelayTicks() {
        return delayTicks;
    }
    
    public static void setDelayTicks(int value) {
        delayTicks = Math.max(MIN_DELAY, Math.min(MAX_DELAY, value));
    }
}
