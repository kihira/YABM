package yabm.client.gui;

import net.minecraft.item.ItemStack;
import tconstruct.client.tabs.AbstractTab;
import yabm.YABM;
import yabm.util.PacketHandler;

public class TabGear extends AbstractTab {

    public TabGear() {
        super(0, 0, 0, new ItemStack(YABM.itemBackpack));
    }

    @Override
    public void onTabClicked() {
        PacketHandler.sendBytePacket(0);
    }

    @Override
    public boolean shouldAddToList() {
        return true;
    }
}
