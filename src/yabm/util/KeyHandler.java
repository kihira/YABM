package yabm.util;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;

public class KeyHandler extends KeyBindingRegistry.KeyHandler {

    private static final KeyBinding gearGUIKeyBinding = new KeyBinding("Open Gear GUI", 34);

    public KeyHandler() {
        super(new KeyBinding[]{gearGUIKeyBinding}, new boolean[]{false});
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        if (tickEnd) {
            //Gear GUI
            if (kb == gearGUIKeyBinding && Minecraft.getMinecraft().currentScreen == null) {
                //System.out.println("Gear GUI button pressed!");
                doBytePacket(0);
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {}

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "YABM";
    }

    private void doBytePacket(int ... bytes) {

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
