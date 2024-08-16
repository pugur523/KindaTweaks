package com.pugur.kindatweaks.tweaks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class SafeStepProtection {
    public static boolean isPositionAllowedByBreakingRestriction(BlockPos pos) {
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;

        if (player != null) {
            //boolean isMovingForward = isMovingForward(player);
            boolean isMovingForward = mc.options.forwardKey.isPressed();

            // posがplayerのy座標より小さいかチェック
            boolean isPosBelowPlayer = pos.getY() < player.getY();

            // 両方が真ならfalseを返す
            return !isMovingForward || !isPosBelowPlayer;
        }

        return true;
    }

    private static boolean isMovingForward(PlayerEntity player) {
        Vec3d velocity = player.getVelocity();
        Direction facing = player.getHorizontalFacing();
        boolean isMovingForward = false;
        if (facing == Direction.NORTH || facing == Direction.SOUTH) {
            isMovingForward = velocity.z > 0 && facing == Direction.SOUTH || velocity.z < 0 && facing == Direction.NORTH;
        } else if (facing == Direction.EAST || facing == Direction.WEST) {
            isMovingForward = velocity.x > 0 && facing == Direction.EAST || velocity.x < 0 && facing == Direction.WEST;
        }
        return isMovingForward;
    }
}
