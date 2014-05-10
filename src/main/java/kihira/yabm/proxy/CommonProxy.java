package kihira.yabm.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import kihira.yabm.YABM;
import kihira.yabm.network.PacketHandler;
import kihira.yabm.util.FMLEventHandler;
import kihira.yabm.util.ForgeEventHandler;
import kihira.yabm.util.GuiHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

    public final PacketHandler packetHandler = new PacketHandler();

    public void registerRenderers() {}

    public void registerKeyBinds() {}

    public void registerTabs() {}

    public void registerItems() {
        GameRegistry.registerItem(YABM.itemBackpack, "backpack");
        GameRegistry.registerItem(YABM.itemVialHolder, "vialholder");
    }

    public void registerHandlers() {
        NetworkRegistry.INSTANCE.registerGuiHandler(YABM.instance, new GuiHandler());
        FMLCommonHandler.instance().bus().register(new FMLEventHandler());
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }
}
