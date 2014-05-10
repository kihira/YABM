package kihira.yabm.util;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import kihira.yabm.YABM;
import kihira.yabm.client.gui.GuiBackpack;
import kihira.yabm.client.gui.GuiGear;
import kihira.yabm.client.gui.GuiVialHolder;
import kihira.yabm.inventory.InventoryGear;
import kihira.yabm.inventory.InventoryNBT;
import kihira.yabm.inventory.container.ContainerBackpack;
import kihira.yabm.inventory.container.ContainerDynamic;
import kihira.yabm.inventory.container.ContainerGear;
import kihira.yabm.inventory.slot.SlotVialHolder;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) return new ContainerBackpack(player.inventory, new InventoryNBT("Backpack", false, YABM.backpackSize * 9, player, player.getCurrentEquippedItem()));
        else if (ID == 1) return new ContainerDynamic(player.inventory, new InventoryNBT("Vial Holder", false, 9, player, player.getCurrentEquippedItem()), SlotVialHolder.class, IInventory.class, int.class , int.class , int.class);
        else if (ID == 2) return new ContainerGear(player.inventory, new InventoryGear(player));
        else return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) return new GuiBackpack(player.inventory, new InventoryNBT("Backpack", false, YABM.backpackSize * 9, player, player.getCurrentEquippedItem()));
        else if (ID == 1) return new GuiVialHolder(player.inventory, new InventoryNBT("Vial Holder", false, 9, player, player.getCurrentEquippedItem()));
        else if (ID == 2) return new GuiGear(player.inventory, new InventoryGear(player));
        else return null;
    }
}