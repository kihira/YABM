package kihira.yabm;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import kihira.yabm.client.gui.TabGear;
import kihira.yabm.item.ItemBackpack;
import kihira.yabm.item.ItemVialHolder;
import kihira.yabm.network.PacketHandler;
import kihira.yabm.proxy.CommonProxy;
import kihira.yabm.util.FMLEventHandler;
import kihira.yabm.util.ForgeEventHandler;
import kihira.yabm.util.GuiHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tconstruct.client.tabs.InventoryTabVanilla;
import tconstruct.client.tabs.TabRegistry;

@Mod(modid = "YABM", useMetadata = true)
public class YABM {

    @Mod.Instance(value = "YABM")
    public static YABM instance;

    @SidedProxy(clientSide = "kihira.yabm.proxy.ClientProxy", serverSide = "kihira.yabm.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Config config;
    public static final ItemBackpack itemBackpack = new ItemBackpack();
    public static final ItemVialHolder itemVialHolder = new ItemVialHolder();
    public static final KeyBinding openGearGUI = new KeyBinding("key.opengeargui", 34, "key.categories.inventory");
    public static final PacketHandler packetHandler = new PacketHandler();
    public static Logger logger = LogManager.getLogger("YABM");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new Config(e.getSuggestedConfigurationFile());

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.registerRenderers();
        //This registers the key handler
        FMLCommonHandler.instance().bus().register(new FMLEventHandler());
        //And this is for forge events
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
        TabRegistry.registerTab(new InventoryTabVanilla());
        TabRegistry.registerTab(new TabGear());
        registerKeyBindings();
        registerItems();
    }

    private void registerKeyBindings() {
        ClientRegistry.registerKeyBinding(openGearGUI);
    }

    private void registerItems() {
        GameRegistry.registerItem(itemBackpack, "backpack");
        GameRegistry.registerItem(itemVialHolder, "vialholder");
    }
}
