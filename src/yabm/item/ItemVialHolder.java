package yabm.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVialHolder extends Item {

    public ItemVialHolder(int id) {
        super(id);
        setUnlocalizedName("vialholder");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        //TODO Auto consume potions based on player stats
    }
}
