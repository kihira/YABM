package kihira.yabm.item;

import kihira.yabm.YABM;
import kihira.yabm.api.IBackpack;
import kihira.yabm.util.NBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

public class ItemVialHolder extends Item implements IBackpack {

    public ItemVialHolder() {
        super();
        setUnlocalizedName("vialholder");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(YABM.instance, 1, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return itemStack;
    }

    public void onUpdate(ItemStack itemStack, World world, Entity entity, int inventorySlot, boolean currentItem) {
        if (!world.isRemote && world.getTotalWorldTime() % 10 == 0 && itemStack.hasTagCompound()) {
            EntityPlayer player = (EntityPlayer) entity;
            ItemStack[] vialHolderInventory = NBTHelper.loadInventory(itemStack.getTagCompound(), 9);
            if (entity.isBurning()) itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 12)));
            if (player.getMaxHealth() / player.getHealth() > 4) itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 6)));
            if (player.getAir() < 50) {
                player.setAir(300);
                itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 13)));
            }
            //Only apply resistance if attack attacked within the last second and has lost more then one heart of damage. Should prevent accidental casting.
            if ((player.getLastAttackerTime() < 20) && (player.getMaxHealth() - player.getHealth() > 1)) itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 11)));
            //Only apply sprint/jump/haste if player currently has sprint as idk when else to trigger this
            if ((player.getActivePotionEffect(Potion.moveSpeed) != null) && (player.getActivePotionEffect(Potion.moveSpeed).getDuration() < 50)) itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 1)));
            if ((player.getActivePotionEffect(Potion.jump) != null) && (player.getActivePotionEffect(Potion.jump).getDuration() < 50)) itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 8)));
            if ((player.getActivePotionEffect(Potion.digSpeed) != null) && (player.getActivePotionEffect(Potion.digSpeed).getDuration() < 50)) itemStack.setTagCompound(NBTHelper.writeInventory(applyPotionEffectIfFound(vialHolderInventory, player, 3)));
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
                        player.worldObj.playSoundAtEntity(player, "random.drink", 0.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
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
