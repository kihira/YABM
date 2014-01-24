package yabm.util;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import yabm.inventory.ContainerBackpack;
import yabm.inventory.InventoryBackpack;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) return new ContainerBackpack(player.inventory, new InventoryBackpack(player, player.getCurrentEquippedItem()));
        else return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //if (ID == 0) return new GuiBackpack(player.inventory);
        if (ID == 0) return new GuiChest(player.inventory, new InventoryBackpack(player, player.getCurrentEquippedItem()));
        else return null;
    }
}