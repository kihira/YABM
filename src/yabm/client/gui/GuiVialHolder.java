package yabm.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import yabm.inventory.container.ContainerBackpack;
import yabm.inventory.container.ContainerDynamic;
import yabm.inventory.container.ContainerVialHolder;
import yabm.inventory.slot.SlotVialHolder;

public class GuiVialHolder extends GuiContainer {

    private static final ResourceLocation field_110421_t = new ResourceLocation("yabm", "textures/gui/inventory.png");
    private static final ResourceLocation slotTexture = new ResourceLocation("yabm", "textures/gui/slot.png");
    private final IInventory inventoryPlayer;
    private final IInventory inventoryVialHolder;
    private int inventoryRows;
    private int offset;

    public GuiVialHolder(IInventory invPlayer, IInventory invBackpack) {
        super(new ContainerDynamic(invPlayer, invBackpack, SlotVialHolder.class, IInventory.class, int.class , int.class , int.class));
        this.inventoryPlayer = invPlayer;
        this.inventoryVialHolder = invBackpack;
        this.allowUserInput = false;
        short short1 = 222;
        int i = short1 - 108;
        this.inventoryRows = invBackpack.getSizeInventory() / 9;
        if (inventoryVialHolder.getSizeInventory() % 9 == 0) this.offset = 0;
        else this.offset = ((9 - (inventoryVialHolder.getSizeInventory() % 9)) * 18) / 2;
        if (this.offset != 0) this.inventoryRows++;
        this.ySize = i + this.inventoryRows * 18;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(this.inventoryPlayer.isInvNameLocalized() ? this.inventoryPlayer.getInvName() : I18n.getString(this.inventoryPlayer.getInvName()), 8, 6, 4210752);
        this.fontRenderer.drawString(this.inventoryVialHolder.isInvNameLocalized() ? this.inventoryVialHolder.getInvName() : I18n.getString(this.inventoryVialHolder.getInvName()), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_110421_t);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);

        //Draw the slots
        this.mc.getTextureManager().bindTexture(slotTexture);
        int columnCount = (inventoryVialHolder.getSizeInventory() % 9);
        if (columnCount == 0) columnCount = 9;
        this.inventoryRows = inventoryVialHolder.getSizeInventory() / 9;
        if (this.offset != 0) this.inventoryRows++;

        for (int j = 0; j < this.inventoryRows; ++j) {
            for (int i = 0; i < columnCount; ++i) {
                this.mc.getTextureManager().bindTexture(field_110421_t);
                this.drawTexturedModalRect(k + this.offset + 7 + i * 18, l + 17 + j * 18, 7, 139, 18, 18);
            }
        }
    }
}
