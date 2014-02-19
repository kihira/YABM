package kihira.yabm.client.gui;

import kihira.yabm.YABM;
import kihira.yabm.network.YABMMessage;
import net.minecraft.item.ItemStack;
import tconstruct.client.tabs.AbstractTab;

public class TabGear extends AbstractTab {

    public TabGear() {
        super(0, 0, 0, new ItemStack(YABM.itemBackpack));
    }

    @Override
    public void onTabClicked() {
        //YABM.messageWrapper.sendToServer(new OpenGUIMessage((byte) 0));
        YABM.packetHandler.sendToServer(new YABMMessage.OpenGUIMessage((byte) 0));
    }

    @Override
    public boolean shouldAddToList() {
        return true;
    }
}
