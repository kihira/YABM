package kihira.yabm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import kihira.yabm.client.gui.TabGear;
import kihira.yabm.item.ItemBackpack;
import kihira.yabm.item.ItemVialHolder;
import kihira.yabm.network.OpenGUIMessage;
import kihira.yabm.proxy.CommonProxy;
import kihira.yabm.util.EventHandler;
import kihira.yabm.util.GuiHandler;
import net.minecraftforge.common.MinecraftForge;
import tconstruct.client.tabs.TabRegistry;

@Mod(modid = "YABM", name = "YABM", version = "0.0.1")
public class YABM {

    @Mod.Instance(value = "YABM")
    public static YABM instance;

    @SidedProxy(clientSide = "yabm.proxy.ClientProxy", serverSide = "yabm.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Config config;
    public static ItemBackpack itemBackpack;
    public static ItemVialHolder itemVialHolder;
    public static SimpleNetworkWrapper messageWrapper = new SimpleNetworkWrapper("YABM");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new Config(e.getSuggestedConfigurationFile());

        registerItems();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        TabRegistry.registerTab(new TabGear());
    }

    private void registerMessages() {
        messageWrapper.registerMessage(OpenGUIMessage.class, OpenGUIMessage.class, 0, Side.SERVER);
    }

    private void registerItems() {
        itemBackpack = new ItemBackpack();
        itemVialHolder = new ItemVialHolder();
    }
}
