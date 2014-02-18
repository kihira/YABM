package kihira.yabm.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import kihira.yabm.YABM;
import net.minecraft.entity.player.EntityPlayerMP;

public class OpenGUIMessage implements IMessage, IMessageHandler<OpenGUIMessage, IMessage> {
    private byte guiID;

    public OpenGUIMessage(){}

    public OpenGUIMessage(byte ID) {
        this.guiID = ID;
    }

    @Override
    public void toBytes(ByteBuf bytes) {
        bytes.writeByte(guiID);
    }

    @Override
    public void fromBytes(ByteBuf bytes) {
        this.guiID = bytes.readByte();
    }

    @Override
    public IMessage onMessage(OpenGUIMessage message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            EntityPlayerMP playerMP = ctx.getServerHandler().playerEntity;
            playerMP.openGui(YABM.instance, message.guiID, playerMP.worldObj, (int) playerMP.posX, (int) playerMP.posY, (int) playerMP.posZ);
        }
        return null;
    }
}
