package yabm.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import yabm.YABM;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();

        if (packet.channel.equals("YABM")) {
            if (side == Side.SERVER) handleServerPacket(packet, (EntityPlayerMP) player);
        }
    }

    private void handleServerPacket(Packet250CustomPayload payload, EntityPlayer player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(payload.data));
        try {
            int packetID = inputStream.readByte();

            //Gear GUI
            if (packetID == 0) {
                player.openGui(YABM.instance, 2, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendBytePacket(int ... bytes) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);

        try {
            for (int i = 0; i < bytes.length; i++) {
                outputStream.writeByte(bytes[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "YABM";
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        PacketDispatcher.sendPacketToServer(packet);
    }
}
