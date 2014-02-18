package kihira.yabm.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import kihira.yabm.inventory.slot.SlotArmour;
import kihira.yabm.inventory.slot.SlotGear;

public class ContainerGear extends Container {

    private IInventory inventoryPlayer;
    private IInventory inventoryGear;

    public ContainerGear(IInventory invPlayer, IInventory invGear) {
        this.inventoryPlayer = invPlayer;
        this.inventoryGear = invGear;

        //Gear slots
        this.addSlotToContainer(new SlotGear(invGear, 0, 80, 17));
        this.addSlotToContainer(new SlotGear(invGear, 1, 80, 35));
        this.addSlotToContainer(new SlotGear(invGear, 2, 116, 17));
        this.addSlotToContainer(new SlotGear(invGear, 3, 116, 35));

        //Player Inventory
        for (int playerArmor = 0; playerArmor < 4; ++playerArmor) {
            this.addSlotToContainer(new SlotArmour(invPlayer, invPlayer.getSizeInventory() - 1 - playerArmor, 98, 8 + playerArmor * 18, playerArmor));
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }

    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        this.inventoryGear.closeChest();
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}
