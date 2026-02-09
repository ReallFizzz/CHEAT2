package dev.knockbacktrainer.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class KnockbackConfigScreen extends Screen {
    private final Screen parent;
    private YawSlider yawSlider;
    private DelaySlider delaySlider;
    
    public KnockbackConfigScreen(Screen parent) {
        super(Text.literal("Knockback Trainer Config"));
        this.parent = parent;
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int startY = this.height / 4;
        
        // Yaw Offset Slider
        this.yawSlider = new YawSlider(
            centerX - 100,
            startY,
            200,
            20
        );
        this.addDrawableChild(yawSlider);
        
        // Delay Ticks Slider
        this.delaySlider = new DelaySlider(
            centerX - 100,
            startY + 40,
            200,
            20
        );
        this.addDrawableChild(delaySlider);
        
        // Done Button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Done"),
            button -> {
                if (this.client != null) {
                    this.client.setScreen(parent);
                }
            }
        ).dimensions(centerX - 75, this.height - 40, 150, 20).build());
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        
        // Рисуем заголовки для слайдеров
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            "Yaw Offset: " + String.format("%.1f", KnockbackConfig.getYawOffset()) + "°",
            this.width / 2,
            this.height / 4 - 15,
            0xFFFFFF
        );
        
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            "Delay Ticks: " + KnockbackConfig.getDelayTicks(),
            this.width / 2,
            this.height / 4 + 25,
            0xFFFFFF
        );
    }
    
    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(parent);
        }
    }
    
    // Слайдер для Yaw Offset
    private static class YawSlider extends SliderWidget {
        public YawSlider(int x, int y, int width, int height) {
            super(x, y, width, height, Text.empty(), 
                (KnockbackConfig.getYawOffset() - KnockbackConfig.MIN_YAW) / 
                (KnockbackConfig.MAX_YAW - KnockbackConfig.MIN_YAW));
            updateMessage();
        }
        
        @Override
        protected void updateMessage() {
            this.setMessage(Text.literal(String.format("%.1f°", KnockbackConfig.getYawOffset())));
        }
        
        @Override
        protected void applyValue() {
            float value = (float) (KnockbackConfig.MIN_YAW + 
                this.value * (KnockbackConfig.MAX_YAW - KnockbackConfig.MIN_YAW));
            KnockbackConfig.setYawOffset(value);
        }
    }
    
    // Слайдер для Delay Ticks
    private static class DelaySlider extends SliderWidget {
        public DelaySlider(int x, int y, int width, int height) {
            super(x, y, width, height, Text.empty(),
                (double) (KnockbackConfig.getDelayTicks() - KnockbackConfig.MIN_DELAY) /
                (KnockbackConfig.MAX_DELAY - KnockbackConfig.MIN_DELAY));
            updateMessage();
        }
        
        @Override
        protected void updateMessage() {
            this.setMessage(Text.literal(String.valueOf(KnockbackConfig.getDelayTicks())));
        }
        
        @Override
        protected void applyValue() {
            int value = (int) Math.round(KnockbackConfig.MIN_DELAY +
                this.value * (KnockbackConfig.MAX_DELAY - KnockbackConfig.MIN_DELAY));
            KnockbackConfig.setDelayTicks(value);
        }
    }
}
