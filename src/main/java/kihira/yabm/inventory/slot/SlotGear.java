package kihira.yabm.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import kihira.yabm.api.IBackpack;

public class SlotGear extends Slot {

    public SlotGear(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof IBackpack);
    }
}
