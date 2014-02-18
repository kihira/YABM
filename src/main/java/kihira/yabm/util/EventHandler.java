package kihira.yabm.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import kihira.yabm.YABM;
import kihira.yabm.client.render.ItemBackpackRenderer;
import kihira.yabm.client.render.VialHolderRenderer;
import kihira.yabm.network.OpenGUIMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class EventHandler {

    private final ItemBackpackRenderer itemBackpackRenderer = new ItemBackpackRenderer();
    private final VialHolderRenderer vialHolderRenderer = new VialHolderRenderer();

    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Specials.Pre e) {
        for (int i = 0; i < e.entityPlayer.inventory.getSizeInventory(); i++) {
            //Render the backpack
            if (e.entityPlayer.inventory.getStackInSlot(i) != null && e.entityPlayer.inventory.getStackInSlot(i).getItem() == YABM.itemBackpack) {
                e.renderCape = false;
                GL11.glPushMatrix();
                GL11.glTranslatef(0f, 0.35f, 0.16f);
                if (e.entityPlayer.inventory.armorItemInSlot(2) != null) GL11.glTranslatef(0.0F, e.entityPlayer.isSneaking() ? -0.1F : 0.0F, e.entityPlayer.isSneaking() ? 0.025F : 0.06F);
                if (e.entityPlayer.isSneaking()) {
                    GL11.glRotatef(28.799999F, 1.0F, 0.0F, 0.0F);
                    GL11.glTranslatef(0.0F, 0.0F, 0.18F);
                }
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                itemBackpackRenderer.renderBackpackItem(0f, -0.3f, -0.2f, 1f, false, true);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
            //Render the vial holder
            if (e.entityPlayer.inventory.getStackInSlot(i) != null && e.entityPlayer.inventory.getStackInSlot(i).getItem() == YABM.itemVialHolder) {
                e.renderCape = false;
                GL11.glPushMatrix();
                GL11.glTranslatef(0f, 0.35f, 0.16f);
                if (e.entityPlayer.inventory.armorItemInSlot(2) != null) GL11.glTranslatef(0.0F, 0.0F, e.entityPlayer.isSneaking() ? -0.025F : -0.06F);
                if (e.entityPlayer.isSneaking()) {
                    GL11.glRotatef(28.799999F, 1.0F, 0.0F, 0.0F);
                    GL11.glTranslatef(0.0F, 0.0F, 0.18F);
                }
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                vialHolderRenderer.render(0.05f, -0.1f, 0.35f, 0.8f, true);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
        }
    }

    @SubscribeEvent
    public void onKeyEvent(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (key == YABM.openGearGUI.getKeyCode() && Minecraft.getMinecraft().currentScreen == null) {
            YABM.messageWrapper.sendToServer(new OpenGUIMessage((byte) 0));
        }
    }
}
