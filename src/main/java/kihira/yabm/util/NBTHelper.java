package kihira.yabm.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper {

    public static ItemStack[] loadInventory(NBTTagCompound tagCompound, int size) {
        NBTTagList nbtTagList = tagCompound.getTagList("Contents", 0);
        ItemStack[] itemStacks = new ItemStack[size];

        for (int i = 0; i < nbtTagList.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = nbtTagList.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;
            if (j >= 0 && j < size) itemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
        return itemStacks;
    }

    public static NBTTagCompound writeInventory(ItemStack[] inventory) {
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound tagCompound = new NBTTagCompound();

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        tagCompound.setTag("Contents", nbttaglist);
        return tagCompound;
    }
}
