package kihira.yabm.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kihira.yabm.YABM;
import kihira.yabm.api.IBackpack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

//TODO Serialise backpack to prevent duping from stuff like the PIM or other mods
public class ItemBackpack extends Item implements IBackpack {

    public ItemBackpack() {
        super();
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
                if (player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() == YABM.itemBackpack) backpackCount++;
            }
            if (backpackCount > 1) {
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, backpackCount - 1));
                player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 10, backpackCount - 1));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("\u00a7cEquip backpack to prevent slowdown");
        int i = 0;
        if (par1ItemStack.hasTagCompound()) i = par1ItemStack.getTagCompound().getTagList("Contents", 0).tagCount();
        par3List.add("Used slots: " + i + "/" + YABM.config.backpackSize * 9);
    }
}
