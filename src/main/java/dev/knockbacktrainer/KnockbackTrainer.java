package dev.knockbacktrainer;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnockbackTrainer implements ClientModInitializer {
    public static final String MOD_ID = "knockbacktrainer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static boolean isEnabled = false;
    
    @Override
    public void onInitializeClient() {
        LOGGER.info("===================================");
        LOGGER.info("Initializing Knockback Trainer Mod");
        LOGGER.info("===================================");
        
        try {
            // Регистрация биндов
            ModKeybinds.register();
            LOGGER.info("[OK] Keybinds registered");
            
            // Регистрация перехватчика атак
            AttackInterceptor.register();
            LOGGER.info("[OK] Attack interceptor registered");
            
            LOGGER.info("===================================");
            LOGGER.info("Knockback Trainer Mod initialized!");
            LOGGER.info("Press R to toggle (default key)");
            LOGGER.info("===================================");
            
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Knockback Trainer!", e);
        }
    }
    
    public static boolean isEnabled() {
        return isEnabled;
    }
    
    public static void toggleEnabled() {
        isEnabled = !isEnabled;
        String status = isEnabled ? "ENABLED" : "DISABLED";
        LOGGER.info("============================");
        LOGGER.info("Knockback Trainer: {}", status);
        LOGGER.info("============================");
    }
}
