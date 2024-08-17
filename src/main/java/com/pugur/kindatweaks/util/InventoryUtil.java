package com.pugur.kindatweaks.util;

import com.pugur.kindatweaks.KindaTweaks;
import com.pugur.kindatweaks.KindaTweaksClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;

import java.util.Objects;

public class InventoryUtil {
    private static int FOOD_SWITCH_SLOT;
    public static void setFoodSwitchSlot(int slot) {
        if (slot > 0 && slot <= 9) {
            FOOD_SWITCH_SLOT = slot - 1;
            KindaTweaks.logger.info("a");
        }
    }

    public static void tryToSwap(MinecraftClient mc, int slot) {
        PlayerEntity player = mc.player;
        if (player != null && player.getWorld() != null) {
            PlayerInventory inventory = player.getInventory();
            ScreenHandler container = player.playerScreenHandler;
            if (slot >= 0 && slot - 36 != inventory.selectedSlot && player.currentScreenHandler == player.playerScreenHandler) {
                if (PlayerInventory.isValidHotbarIndex(slot)) {
                    inventory.selectedSlot = slot;
                    mc.getNetworkHandler().sendPacket(new UpdateSelectedSlotC2SPacket(inventory.selectedSlot));
                } else {
                    if (inventory.selectedSlot != FOOD_SWITCH_SLOT) {
                        inventory.selectedSlot = FOOD_SWITCH_SLOT;
                        Objects.requireNonNull(KindaTweaksClient.mc.getNetworkHandler()).sendPacket(new UpdateSelectedSlotC2SPacket(player.getInventory().selectedSlot));
                    }
                    if (slot != inventory.selectedSlot) {
                        mc.interactionManager.clickSlot(container.syncId, slot, FOOD_SWITCH_SLOT, SlotActionType.SWAP, mc.player);
                    }
                }
            }
        }
    }
}
