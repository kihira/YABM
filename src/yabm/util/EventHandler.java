package yabm.util;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;
import yabm.YABM;
import yabm.client.render.ItemBackpackRenderer;

public class EventHandler {

    private final ItemBackpackRenderer itemBackpackRenderer = new ItemBackpackRenderer();

    @ForgeSubscribe
    public void onPlayerRender(RenderPlayerEvent.Specials.Pre e) {
        for (int i = 0; i < e.entityPlayer.inventory.getSizeInventory(); i++) {
            //Render the backpack
            if (e.entityPlayer.inventory.getStackInSlot(i) != null && e.entityPlayer.inventory.getStackInSlot(i).itemID == YABM.itemBackpack.itemID) {
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
                break;
            }
        }
    }
}
