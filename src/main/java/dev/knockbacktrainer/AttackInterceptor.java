package dev.knockbacktrainer;

import dev.knockbacktrainer.config.KnockbackConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class AttackInterceptor {
    private static boolean isProcessing = false;
    private static int ticksWaited = 0;
    private static float originalYaw = 0.0f;
    private static Entity targetEntity = null;
    private static boolean shouldAttack = false;
    
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(AttackInterceptor::tick);
    }
    
    private static void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        
        // Если мод выключен
        if (!KnockbackTrainer.isEnabled()) {
            if (isProcessing) {
                resetState(client);
            }
            return;
        }
        
        // Если в процессе обработки
        if (isProcessing) {
            ticksWaited++;
            
            if (ticksWaited >= KnockbackConfig.getDelayTicks()) {
                if (shouldAttack && targetEntity != null) {
                    performAttack(client);
                }
                resetState(client);
            }
        }
    }
    
    // Вызывается перед атакой
    public static boolean onAttackStart(MinecraftClient client, Entity target) {
        if (!KnockbackTrainer.isEnabled() || client.player == null) {
            return true; // Разрешаем обычную атаку
        }
        
        if (isProcessing) {
            return false; // Блокируем повторную атаку во время обработки
        }
        
        // Начинаем процесс
        startRotation(client, target);
        return false; // Блокируем стандартную атаку
    }
    
    private static void startRotation(MinecraftClient client, Entity target) {
        if (client.player == null) return;
        
        // Сохраняем состояние
        originalYaw = client.player.getYaw();
        targetEntity = target;
        
        // Применяем поворот
        float newYaw = originalYaw + KnockbackConfig.getYawOffset();
        client.player.setYaw(newYaw);
        client.player.headYaw = newYaw;
        
        // Устанавливаем флаги
        isProcessing = true;
        shouldAttack = true;
        ticksWaited = 0;
        
        KnockbackTrainer.LOGGER.info("Rotation started: {}° -> {}° (offset: {}°)", 
            String.format("%.1f", originalYaw), 
            String.format("%.1f", newYaw),
            String.format("%.1f", KnockbackConfig.getYawOffset()));
    }
    
    private static void performAttack(MinecraftClient client) {
        if (client.player == null || client.interactionManager == null || targetEntity == null) {
            return;
        }
        
        // Выполняем атаку
        client.interactionManager.attackEntity(client.player, targetEntity);
        client.player.swingHand(client.player.getActiveHand());
        
        KnockbackTrainer.LOGGER.info("Attack performed on: {}", targetEntity.getName().getString());
    }
    
    private static void resetState(MinecraftClient client) {
        if (client.player == null) return;
        
        // Возвращаем yaw
        client.player.setYaw(originalYaw);
        client.player.headYaw = originalYaw;
        
        // Сбрасываем состояние
        isProcessing = false;
        shouldAttack = false;
        ticksWaited = 0;
        targetEntity = null;
        
        KnockbackTrainer.LOGGER.info("Rotation reset to: {}°", String.format("%.1f", originalYaw));
    }
}
