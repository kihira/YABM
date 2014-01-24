package yabm.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import yabm.inventory.ContainerBackpack;

@SideOnly(Side.CLIENT)
public class GuiBackpack extends GuiContainer {

    private final InventoryPlayer inventoryPlayer;

    public GuiBackpack(InventoryPlayer invPlayer) {
        super(new ContainerBackpack(invPlayer, invPlayer));
        inventoryPlayer = invPlayer;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

    }
}
