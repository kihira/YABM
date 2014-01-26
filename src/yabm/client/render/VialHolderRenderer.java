package yabm.client.render;

import cpw.mods.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;
import yabm.client.model.ModelVialHolder;
import yabm.proxy.ClientProxy;

public class VialHolderRenderer {

    private final ModelVialHolder modelVialHolder = new ModelVialHolder();

    public void render(float x, float y, float z, float scale, boolean isEquipped) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ClientProxy.vialHolderTexture);
        GL11.glTranslated(x, y, z);
        GL11.glScalef(scale, scale, scale);
        //if (!isInventory) GL11.glRotatef(180f, 180f, 0f, 1f);
        //else GL11.glRotatef(180f, 0f, 0f, 1f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        if (isEquipped) {
            GL11.glRotatef(180f, 1f, 0f, 0f);
            GL11.glRotatef(-45f, 0f, 0f, 1f);
        }
        modelVialHolder.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
