package kihira.yabm.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import kihira.yabm.YABM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;

public abstract class YABMMessage implements IMessage {
    public abstract IMessage onMessage(YABMMessage message, ChannelHandlerContext ctx, Side side);

    public static class OpenGUIMessage extends YABMMessage {
        byte guiID;

        public OpenGUIMessage() {}
        public OpenGUIMessage(byte guiID) {
            this.guiID = guiID;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.guiID = buf.readByte();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeByte(guiID);
        }

        @Override
        public IMessage onMessage(YABMMessage message, ChannelHandlerContext ctx, Side side) {
            if (side == Side.SERVER) {
                NetHandlerPlayServer netHandler = (NetHandlerPlayServer) ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                EntityPlayer playerMP = netHandler.playerEntity;
                playerMP.openGui(YABM.instance, this.guiID, playerMP.worldObj, (int) playerMP.posX, (int) playerMP.posY, (int) playerMP.posZ);
            }
            return null;
        }
    }
}
