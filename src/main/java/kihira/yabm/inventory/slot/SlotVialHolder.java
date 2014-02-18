package kihira.yabm.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class SlotVialHolder extends Slot {

    public SlotVialHolder(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof ItemPotion);
    }

    public int getSlotStackLimit() {
        return 1;
    }
}
