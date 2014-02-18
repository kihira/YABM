package kihira.yabm.util;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import kihira.yabm.network.OpenGUIMessage;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

public class PacketHandler extends MessageToMessageCodec<FMLProxyPacket, IMessage> {

    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Class<? extends IMessage>> packets = new LinkedList<Class<? extends IMessage>>();

    public void initalise () {
        this.channels = NetworkRegistry.INSTANCE.newChannel("YABM", this);
        registerPackets();
    }

    public void registerPackets () {
        this.packets.add(OpenGUIMessage.class);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IMessage msg, List<Object> out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {

    }
}
