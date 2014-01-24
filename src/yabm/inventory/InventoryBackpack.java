package yabm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import yabm.YABM;

public class InventoryBackpack implements IInventory {

    private final ItemStack[] inventoryContents;
    private final ItemStack backpackItemStack;
    private final EntityPlayer entityPlayer;

    public InventoryBackpack(EntityPlayer player, ItemStack itemStack) {
        backpackItemStack = itemStack;
        entityPlayer = player;

        if (itemStack.hasTagCompound()) {
            NBTTagList nbtTagList = itemStack.getTagCompound().getTagList("Contents");
            inventoryContents = new ItemStack[this.getSizeInventory()];
            for (int i = 0; i < nbtTagList.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbtTagList.tagAt(i);
                int j = nbttagcompound1.getByte("Slot") & 255;

                if (j >= 0 && j < inventoryContents.length) inventoryContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        else inventoryContents = new ItemStack[27];
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventoryContents[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (inventoryContents[i] != null){
            ItemStack itemstack;
            if (inventoryContents[i].stackSize <= j) {
                itemstack = this.inventoryContents[i];
                inventoryContents[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            else {
                itemstack = inventoryContents[i].splitStack(j);
                if (inventoryContents[i].stackSize == 0) inventoryContents[i] = null;
                onInventoryChanged();
                return itemstack;
            }
        }
        else return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return inventoryContents[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        if (itemstack != null) {
            if (itemstack.itemID == YABM.itemBackpack.itemID) return;
            inventoryContents[i] = itemstack;
            if (itemstack.stackSize > getInventoryStackLimit()) itemstack.stackSize = getInventoryStackLimit();
            onInventoryChanged();
        }
    }

    @Override
    public String getInvName() {
        return "Backpack";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound tagCompound = new NBTTagCompound();

        for (int i = 0; i < inventoryContents.length; i++) {
            if (inventoryContents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                inventoryContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagCompound.setTag("Contents", nbttaglist);
        backpackItemStack.setTagCompound(tagCompound);
        entityPlayer.setCurrentItemOrArmor(0, backpackItemStack);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
    }

    @Override
    public void openChest() { }

    @Override
    public void closeChest() {
        onInventoryChanged();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return itemstack.itemID != YABM.itemBackpack.itemID;
    }
}
