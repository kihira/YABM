package kihira.yabm.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import kihira.yabm.YABM;
import kihira.yabm.client.gui.TabGear;
import kihira.yabm.client.render.ItemBackpackRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import tconstruct.client.tabs.InventoryTabVanilla;
import tconstruct.client.tabs.TabRegistry;

public class ClientProxy extends CommonProxy {

    public static final ResourceLocation backpackTexture = new ResourceLocation("yabm", "textures/model/backpack.png");
    public static final ResourceLocation vialHolderTexture = new ResourceLocation("yabm", "textures/model/vialholder.png");

    @Override
    public void registerRenderers() {
        MinecraftForgeClient.registerItemRenderer(YABM.itemBackpack, new ItemBackpackRenderer());
    }

    @Override
    public void registerKeyBinds() {
        ClientRegistry.registerKeyBinding(YABM.openGearGUI);
    }

    @Override
    public void registerTabs() {
        TabRegistry.registerTab(new InventoryTabVanilla());
        TabRegistry.registerTab(new TabGear());
    }
}
