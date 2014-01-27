package yabm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import yabm.YABM;

public class ItemVialHolder extends Item {

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

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        //TODO Auto consume potions based on player stats
    }
}
