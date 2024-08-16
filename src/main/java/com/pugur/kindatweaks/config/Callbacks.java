package com.pugur.kindatweaks.config;

import com.pugur.kindatweaks.gui.GuiConfigs;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

public class Callbacks {

    public static void init() {
        IHotkeyCallback callbackGeneric = new KeyCallbackHotkeysGeneric();
        Hotkeys.OPEN_CONFIG_GUI.getKeybind().setCallback(callbackGeneric);
    }

    private static class KeyCallbackHotkeysGeneric implements IHotkeyCallback
    {

        public KeyCallbackHotkeysGeneric()
        {
        }

        @Override
        public boolean onKeyAction(KeyAction action, IKeybind key)
        {
            if (key == Hotkeys.OPEN_CONFIG_GUI.getKeybind())
            {
                GuiBase.openGui(new GuiConfigs());
                return true;
            }

            return false;
        }
    }

}
