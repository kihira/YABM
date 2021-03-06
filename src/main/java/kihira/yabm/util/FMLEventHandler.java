package kihira.yabm.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kihira.yabm.YABM;
import kihira.yabm.network.YABMMessage;
import kihira.yabm.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.lwjgl.input.Keyboard;
import tconstruct.client.tabs.TabRegistry;

public class FMLEventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyEvent(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        Minecraft minecraft = Minecraft.getMinecraft();
        //System.out.println(Keyboard.areRepeatEventsEnabled());
        if (key == ClientProxy.openGearGUI.getKeyCode() && minecraft.currentScreen == null) {;
            YABM.proxy.packetHandler.sendToServer(new YABMMessage.OpenGUIMessage((byte) 2));
        }
    }

    //Similar to how TiCon does it
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onTick(TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if ((event.phase == TickEvent.Phase.END) && Keyboard.getEventKey() == minecraft.gameSettings.keyBindInventory.getKeyCode() && (minecraft.currentScreen != null) && (minecraft.currentScreen.getClass() == GuiInventory.class)) {
            TabRegistry.addTabsToInventory((GuiContainer) minecraft.currentScreen);
        }
    }
}
