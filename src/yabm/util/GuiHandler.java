package yabm.util;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import yabm.YABM;
import yabm.client.gui.GuiBackpack;
import yabm.client.gui.GuiVialHolder;
import yabm.inventory.container.ContainerBackpack;
import yabm.inventory.container.ContainerDynamic;
import yabm.inventory.container.ContainerVialHolder;
import yabm.inventory.InventoryNBT;
import yabm.inventory.slot.SlotVialHolder;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) return new ContainerBackpack(player.inventory, new InventoryNBT("Backpack", false, YABM.config.backpackSize * 9, player, player.getCurrentEquippedItem()));
        else if (ID == 1) return new ContainerDynamic(player.inventory, new InventoryNBT("Vial Holder", false, 9, player, player.getCurrentEquippedItem()), SlotVialHolder.class, IInventory.class, int.class , int.class , int.class);
        else return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) return new GuiBackpack(player.inventory, new InventoryNBT("Backpack", false, YABM.config.backpackSize * 9, player, player.getCurrentEquippedItem()));
        else if (ID == 1) return new GuiVialHolder(player.inventory, new InventoryNBT("Vial Holder", false, 9, player, player.getCurrentEquippedItem()));
        else return null;
    }
}