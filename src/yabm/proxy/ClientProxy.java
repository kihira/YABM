package yabm.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import yabm.YABM;
import yabm.client.render.ItemBackpackRenderer;

public class ClientProxy extends CommonProxy {

    public static final ResourceLocation backpackTexture = new ResourceLocation("yabm", "textures/model/backpack.png");
    public static final ResourceLocation vialHolderTexture = new ResourceLocation("yabm", "textures/model/vialholder.png");

    @Override
    public void registerRenderers() {
        MinecraftForgeClient.registerItemRenderer(YABM.itemBackpack.itemID, new ItemBackpackRenderer());
    }
}
