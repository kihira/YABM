package kihira.yabm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryGear extends InventoryBasic {

    private final EntityPlayer player;

    public InventoryGear(EntityPlayer entityPlayer) {
        super("", false, 4);
        this.player = entityPlayer;
        this.openChest();
    }

    public void onInventoryChanged() {
        super.onInventoryChanged();
        NBTTagCompound tags = this.player.getEntityData();
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound invSlot;

        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.getStackInSlot(i) != null) {
                invSlot = new NBTTagCompound();
                invSlot.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(invSlot);
                tagList.appendTag(invSlot);
            }
        }
        tags.setTag("YABM.Gear", tagList);
    }

    public void openChest() {
        NBTTagCompound tags = this.player.getEntityData();
        NBTTagList tagList = tags.getTagList("YABM.Gear");
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.tagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
            if (itemstack != null) this.setInventorySlotContents(j, itemstack);
        }
    }

    public void closeChest() {
        this.onInventoryChanged();
    }

    public int getInventoryStackLimit() {
        return 1;
    }
}
