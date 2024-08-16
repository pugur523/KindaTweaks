package com.pugur.kindatweaks;

import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ClientModInitializer;

public class KindaTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
        KindaTweaks.logger.info("KindaTweaks Client has Initialized");
    }
}
