package kihira.yabm.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import kihira.yabm.YABM;
import kihira.yabm.network.YABMMessage;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class FMLEventHandler {

    @SubscribeEvent
    public void onKeyEvent(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (key == YABM.openGearGUI.getKeyCode() && Minecraft.getMinecraft().currentScreen == null) {;
            YABM.packetHandler.sendToServer(new YABMMessage.OpenGUIMessage((byte) 0));
        }
    }
}
