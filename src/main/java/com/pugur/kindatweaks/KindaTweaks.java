package com.pugur.kindatweaks;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KindaTweaks implements ModInitializer {
    public static Logger logger;
    @Override
    public void onInitialize() {
        logger = LoggerFactory.getLogger(Reference.MOD_NAME);
        logger.info("KindaTweaks has initialized");
    }
}
