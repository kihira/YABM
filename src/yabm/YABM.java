package yabm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import yabm.item.ItemBackpack;
import yabm.item.ItemVialHolder;
import yabm.proxy.CommonProxy;
import yabm.util.EventHandler;
import yabm.util.GuiHandler;

@Mod(modid = "YABM", name = "YABM", version = "0.0.1")
@NetworkMod(clientSideRequired = true)
public class YABM {

    @Mod.Instance(value = "YABM")
    public static YABM instance;

    @SidedProxy(clientSide = "yabm.proxy.ClientProxy", serverSide = "yabm.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Config config;

    public static ItemBackpack itemBackpack;
    public static ItemVialHolder itemVialHolder;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new Config(e.getSuggestedConfigurationFile());

        registerItems();
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
        proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    private void registerItems() {
        itemBackpack = new ItemBackpack(config.itemBackpackID);
        itemVialHolder = new ItemVialHolder(config.itemVialHolderID);
    }
}
