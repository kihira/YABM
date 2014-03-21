package kihira.yabm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import kihira.yabm.YABM;

public class InventoryNBT extends InventoryBasic {

    private EntityPlayer entityPlayer;
    private ItemStack backpackItemStack;

    public InventoryNBT(String name, boolean isLocalised, int size, EntityPlayer player, ItemStack itemStack) {
        super(name, isLocalised, size);
        this.backpackItemStack = itemStack;
        this.entityPlayer = player;
    }

    @Override
    public void openInventory() {
        if (this.backpackItemStack.hasTagCompound()) {
            NBTTagList nbtTagList = this.backpackItemStack.getTagCompound().getTagList("Contents", 0);
            for (int i = 0; i < nbtTagList.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = nbtTagList.getCompoundTagAt(i);
                int j = nbttagcompound1.getByte("Slot") & 255;
                if (j >= 0 && j < getSizeInventory()) setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
            }
        }
    }

    @Override
    public void closeInventory() {
        markDirty();
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound tagCompound = new NBTTagCompound();

        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                getStackInSlot(i).writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagCompound.setTag("Contents", nbttaglist);
        this.backpackItemStack.setTagCompound(tagCompound);
        this.entityPlayer.setCurrentItemOrArmor(0, this.backpackItemStack);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return itemstack.getItem() != YABM.itemBackpack;
    }
}
