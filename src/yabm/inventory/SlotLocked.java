package yabm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

//Used to prevent a player from picking up an item from a slot. Use on slots that contain backpacks etc
//to prevent duping
public class SlotLocked extends Slot {

    public SlotLocked(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
        return false;
    }
}
