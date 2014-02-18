package kihira.yabm.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import kihira.yabm.api.IBackpack;
import kihira.yabm.inventory.slot.SlotLocked;
import kihira.yabm.item.ItemBackpack;

public class ContainerDynamic extends Container {

    private IInventory inventoryUpper;
    private int numRows;

    public ContainerDynamic(IInventory invLower, IInventory invUpper, Class<? extends Slot> upperSlotClass, Class<?> ... constructorArgs) {
        this.inventoryUpper = invUpper;
        invUpper.openChest();
        int offset;

        if (invUpper.getSizeInventory() % 9 == 0) offset = 0;
        else offset = ((9 -(invUpper.getSizeInventory() % 9)) * 18) / 2;
        int columnCount = (invUpper.getSizeInventory() % 9);
        if (columnCount == 0) columnCount = 9;
        this.numRows = invUpper.getSizeInventory() / 9;
        if (offset != 0) this.numRows++;
        int i = (this.numRows - 4) * 18;
        int j;
        int k;

        for (j = 0; j < this.numRows; ++j) {
            for (k = 0; k < columnCount; ++k) {
                try {
                    if (k + j * 9 < invUpper.getSizeInventory()) this.addSlotToContainer(upperSlotClass.getConstructor(constructorArgs).newInstance(invUpper, k + j * 9, offset + 8 + k * 18, 18 + j * 18));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        for (j = 0; j < 3; ++j) {
            for (k = 0; k < 9; ++k) {
                if (invLower.getStackInSlot(k + j * 9 + 9) != null && invLower.getStackInSlot(k + j * 9 + 9).getItem() instanceof IBackpack) this.addSlotToContainer(new SlotLocked(invLower, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
                else this.addSlotToContainer(new Slot(invLower, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j) {
            if (invLower.getStackInSlot(j) != null && invLower.getStackInSlot(j).getItem() instanceof IBackpack) this.addSlotToContainer(new SlotLocked(invLower, j, 8 + j * 18, 161 + i));
            else this.addSlotToContainer(new Slot(invLower, j, 8 + j * 18, 161 + i));
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.inventoryUpper.isUseableByPlayer(par1EntityPlayer);
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
        this.inventoryUpper.closeChest();
    }
}
