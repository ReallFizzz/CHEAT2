package dev.knockbacktrainer.mixin;

import dev.knockbacktrainer.AttackInterceptor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    
    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void onAttack(CallbackInfo ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;
        
        if (client.targetedEntity != null) {
            boolean shouldCancel = !AttackInterceptor.onAttackStart(client, client.targetedEntity);
            if (shouldCancel) {
                ci.cancel();
            }
        }
    }
}
