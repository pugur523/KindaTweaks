package com.pugur.kindatweaks;

import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class KindaTweaksClient implements ClientModInitializer {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
        KindaTweaks.logger.info("KindaTweaks Client has Initialized");
    }
}
