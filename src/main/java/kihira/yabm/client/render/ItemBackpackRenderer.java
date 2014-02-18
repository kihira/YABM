package kihira.yabm.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import kihira.yabm.client.model.ModelBackpack;
import kihira.yabm.proxy.ClientProxy;

public class ItemBackpackRenderer implements IItemRenderer {

    private final ModelBackpack modelBackpack = new ModelBackpack();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY: {
                renderBackpackItem(0f, 0.5f, 0f, 1f, false, false);
                break;
            }
            case EQUIPPED: {
                renderBackpackItem(0.5f, 1f, 0.5f, 1.75f, false, false);
                break;
            }
            case INVENTORY: {
                renderBackpackItem(0.2f, 0.5f, 0f, 1.75f, true, false);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                renderBackpackItem(3f, 0.5f, 0.5f, 2.5f, true, false);
                break;
            }
            default: break;
        }
    }

    public void renderBackpackItem(float x, float y, float z, float scale, boolean isInventory, boolean isEquipped) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxy.backpackTexture);
        GL11.glTranslated(x, y, z);
        GL11.glScalef(scale, scale, scale);
        if (!isInventory) GL11.glRotatef(180f, 180f, 0f, 1f);
        else GL11.glRotatef(180f, 0f, 0f, 1f);
        if (isEquipped) {
            GL11.glRotatef(90f, 0f, 1f, 0f);
            GL11.glRotatef(180f, 1f, 0f, 0f);
        }
        modelBackpack.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
