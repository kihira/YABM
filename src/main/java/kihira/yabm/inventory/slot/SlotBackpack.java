package kihira.yabm.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import kihira.yabm.YABM;

public class SlotBackpack extends Slot {

    public SlotBackpack(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() != YABM.itemBackpack);
    }
}
