package yabm.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import yabm.inventory.container.ContainerBackpack;

@SideOnly(Side.CLIENT)
public class GuiBackpack extends GuiContainer {

    private static final ResourceLocation field_110421_t = new ResourceLocation("textures/gui/container/generic_54.png");
    private final IInventory inventoryPlayer;
    private final IInventory inventoryBackpack;
    private int inventoryRows;

    public GuiBackpack(IInventory invPlayer, IInventory invBackpack) {
        super(new ContainerBackpack(invPlayer, invBackpack));
        inventoryPlayer = invPlayer;
        inventoryBackpack = invBackpack;
        this.allowUserInput = false;
        short short1 = 222;
        int i = short1 - 108;
        this.inventoryRows = invBackpack.getSizeInventory() / 9;
        this.ySize = i + this.inventoryRows * 18;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(this.inventoryPlayer.isInvNameLocalized() ? this.inventoryPlayer.getInvName() : I18n.getString(this.inventoryPlayer.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(this.inventoryBackpack.isInvNameLocalized() ? this.inventoryBackpack.getInvName() : I18n.getString(this.inventoryBackpack.getInvName()), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_110421_t);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}
