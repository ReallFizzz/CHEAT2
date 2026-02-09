package dev.knockbacktrainer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    private static KeyBinding toggleKeybind;
    private static boolean wasPressed = false;
    
    public static void register() {
        toggleKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.knockbacktrainer.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.knockbacktrainer"
        ));
        
        // Регистрируем обработчик тиков
        ClientTickEvents.END_CLIENT_TICK.register(client -> handleKeybinds());
        
        KnockbackTrainer.LOGGER.info("Toggle keybind registered: R key");
    }
    
    private static void handleKeybinds() {
        if (toggleKeybind == null) return;
        
        boolean isPressed = toggleKeybind.isPressed();
        
        // Обнаружение нажатия (не удержания)
        if (isPressed && !wasPressed) {
            KnockbackTrainer.toggleEnabled();
        }
        
        wasPressed = isPressed;
    }
}
