package yabm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import yabm.item.ItemBackpack;
import yabm.proxy.CommonProxy;
import yabm.util.GuiHandler;

@Mod(modid = "YABM", name = "YABM", version = "0.0.1")
@NetworkMod(clientSideRequired = true)
public class YABM {

    @Mod.Instance(value = "YABM")
    public static YABM instance;

    public static Config config;

    public static ItemBackpack itemBackpack;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new Config(e.getSuggestedConfigurationFile());

        registerItems();

        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
    }

    private void registerItems() {
        itemBackpack = new ItemBackpack(config.itemBackpackID);
    }
}
