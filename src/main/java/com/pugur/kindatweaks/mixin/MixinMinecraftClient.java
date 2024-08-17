package com.pugur.kindatweaks.mixin;

import com.pugur.kindatweaks.KindaTweaksClient;
import com.pugur.kindatweaks.config.Configs;
import com.pugur.kindatweaks.config.FeatureToggle;
import com.pugur.kindatweaks.util.InventoryUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Shadow @Nullable public Screen currentScreen;
    @Shadow @Final public GameOptions options;
    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void onProcessKeybindsPre(CallbackInfo ci)
    {
        if (this.currentScreen == null)
        {
            if (FeatureToggle.TWEAK_HOLD_FORWARD.getBooleanValue())
            {
                KeyBinding.setKeyPressed(InputUtil.fromTranslationKey(this.options.forwardKey.getBoundKeyTranslationKey()), true);
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (player == null || KindaTweaksClient.mc == null) return;
        if (KindaTweaksClient.mc.getNetworkHandler() == null || KindaTweaksClient.mc.interactionManager == null) return;tw
        if (Configs.Generic.MOD_ENABLED.getBooleanValue() && FeatureToggle.TWEAK_AUTO_EAT.getBooleanValue()) {
            if (player.getHungerManager().isNotFull()) {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack stack = player.getInventory().getStack(i);
                    if (stack.getItem().isFood()) {
                        InventoryUtil.tryToSwap(KindaTweaksClient.mc, i);
                        KeyBinding.setKeyPressed(InputUtil.fromTranslationKey(this.options.useKey.getBoundKeyTranslationKey()), true);
                        return;
                    }
                }
            }
        }
    }
}