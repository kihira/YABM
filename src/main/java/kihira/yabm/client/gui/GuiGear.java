package kihira.yabm.client.gui;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tconstruct.client.tabs.TabRegistry;
import kihira.yabm.inventory.container.ContainerGear;

public class GuiGear extends InventoryEffectRenderer {

    private static final ResourceLocation background = new ResourceLocation("yabm", "textures/gui/gear.png");
    private final IInventory inventoryPlayer;
    private final IInventory inventoryGear;
    private float xSize_lo;
    private float ySize_lo;

    public GuiGear(IInventory invPlayer, IInventory invGear) {
        super(new ContainerGear(invPlayer, invGear));
        this.inventoryPlayer = invPlayer;
        this.inventoryGear = invGear;
        this.allowUserInput = false;
    }

    @Override
    public void initGui() {
        super.initGui();

        int cornerX = guiLeft;
        int cornerY = (this.height - this.ySize) / 2;
        this.buttonList.clear();

        TabRegistry.updateTabValues(cornerX, cornerY, TabGear.class);
        TabRegistry.addTabsToList(this.buttonList);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
        this.xSize_lo = (float)par1;
        this.ySize_lo = (float)par2;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) { }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GuiInventory.func_147046_a(k + 33, l + 75, 30, (float) (k + 51) - this.xSize_lo, (float) (l + 75 - 50) - this.ySize_lo, this.mc.thePlayer);
    }
}
