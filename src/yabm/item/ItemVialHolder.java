package yabm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import yabm.YABM;
import yabm.api.IBackpack;
import yabm.inventory.InventoryNBT;
import yabm.util.NBTHelper;

import java.util.List;

public class ItemVialHolder extends Item implements IBackpack {

    public ItemVialHolder(int id) {
        super(id);
        setUnlocalizedName("vialholder");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(YABM.instance, 1, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return itemStack;
    }

    public void onUpdate(ItemStack itemStack, World world, Entity entity, int inventorySlot, boolean currentItem) {
        //TODO Auto consume potions based on player stats
        if (!world.isRemote && itemStack.hasTagCompound()) {
            EntityPlayer player = (EntityPlayer) entity;
            if (entity.isBurning()) {
                ItemStack[] vialHolderInventory = NBTHelper.loadInventory(itemStack.getTagCompound(), 9);
                itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 12)));
            }
        }
    }

    //Returns an updated array of the vial holder's inventory.
    private ItemStack[] applyPotionEffectIfFound(ItemStack[] vialHolderInventory, EntityPlayer player, int potionID) {
        for (int i = 0; i < vialHolderInventory.length; i++) {
            boolean flag = false;
            if (vialHolderInventory[i] != null) {
                ItemPotion itemPotion = (ItemPotion) vialHolderInventory[i].getItem();
                List potionEffects = itemPotion.getEffects(vialHolderInventory[i]);
                for (Object object:potionEffects) {
                    PotionEffect potionEffect = (PotionEffect) object;
                    if (potionEffect.getPotionID() == potionID) {
                        player.addPotionEffect(potionEffect);
                        player.playSound("random.drink", 0.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                        vialHolderInventory[i] = null;
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }
        return vialHolderInventory;
    }
}
