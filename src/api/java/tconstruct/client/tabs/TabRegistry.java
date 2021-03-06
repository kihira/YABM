package tconstruct.client.tabs;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;

import java.util.ArrayList;
import java.util.List;

public class TabRegistry
{
    private static ArrayList<AbstractTab> tabList = new ArrayList<AbstractTab>();

    public static void registerTab (AbstractTab tab)
    {
        tabList.add(tab);
    }

    public static ArrayList<AbstractTab> getTabList ()
    {
        return tabList;
    }

    public static void addTabsToInventory (GuiContainer gui)
    {
        if (gui.getClass() == GuiInventory.class)
        {
            // Values are public at runtime.
            int cornerX = gui.xSize;
            int cornerY = gui.guiTop;
            gui.buttonList.clear();

            updateTabValues(cornerX, cornerY, InventoryTabVanilla.class);
            addTabsToList(gui.buttonList);
        }
    }

    private static Minecraft mc = FMLClientHandler.instance().getClient();

    public static void openInventoryGui ()
    {
        GuiInventory inventory = new GuiInventory(mc.thePlayer);
        mc.displayGuiScreen(inventory);
        TabRegistry.addTabsToInventory(inventory);
    }

    public static void updateTabValues (int cornerX, int cornerY, Class<?> selectedButton)
    {
        int count = 2;
        for (int i = 0; i < tabList.size(); i++)
        {
            AbstractTab t = tabList.get(i);

            if (t.shouldAddToList())
            {
                t.id = count;
                t.xPosition = cornerX + (count - 2) * 28;
                t.yPosition = cornerY - 28;
                t.enabled = !t.getClass().equals(selectedButton);
                count++;
            }
        }
    }

    public static void addTabsToList (List buttonList)
    {
        for (AbstractTab tab : tabList)
        {
            if (tab.shouldAddToList())
            {
                buttonList.add(tab);
            }
        }
    }
}