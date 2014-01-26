package yabm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import yabm.item.ItemBackpack;

public class ContainerBackpack extends Container {

    private IInventory backpackInventory;
    private int numRows;

    public ContainerBackpack(IInventory invPlayer, IInventory invBackpack) {
        this.backpackInventory = invBackpack;
        this.numRows = invBackpack.getSizeInventory() / 9;
        invBackpack.openChest();
        int i = (this.numRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < this.numRows; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlotToContainer(new SlotBackpack(invBackpack, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(invPlayer, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(invPlayer, j, 8 + j * 18, 161 + i));
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.backpackInventory.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < this.numRows * 9) {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), false)) return null;
            }
            else if (itemstack1.getItem() instanceof ItemBackpack) return null;
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) return null;

            if (itemstack1.stackSize == 0) slot.putStack(null);
            else slot.onSlotChanged();
        }

        return itemstack;
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        this.backpackInventory.closeChest();
    }
}
