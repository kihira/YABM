package yabm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import yabm.YABM;
import yabm.api.IBackpack;

public class ItemBackpack extends Item implements IBackpack {

    public ItemBackpack(int id) {
        super(id);
        setUnlocalizedName("backpack");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(YABM.instance, 0, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        if (!world.isRemote) {
            short backpackCount = 0;
            EntityPlayer player = (EntityPlayer) entity;

            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).itemID == YABM.itemBackpack.itemID) backpackCount++;
            }
            if (backpackCount > 1) {
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, backpackCount - 1));
                player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 10, backpackCount - 1));
            }
        }
    }
}
