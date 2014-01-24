package yabm.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import yabm.YABM;
import yabm.client.render.ItemBackpackRenderer;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        MinecraftForgeClient.registerItemRenderer(YABM.itemBackpack.itemID, new ItemBackpackRenderer());
    }
}
