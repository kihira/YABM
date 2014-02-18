package kihira.yabm.proxy;

import kihira.yabm.YABM;
import kihira.yabm.client.render.ItemBackpackRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    public static final ResourceLocation backpackTexture = new ResourceLocation("yabm", "textures/model/backpack.png");
    public static final ResourceLocation vialHolderTexture = new ResourceLocation("yabm", "textures/model/vialholder.png");

    @Override
    public void registerRenderers() {
        MinecraftForgeClient.registerItemRenderer(YABM.itemBackpack, new ItemBackpackRenderer());
    }
}
