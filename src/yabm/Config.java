package yabm;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;

public class Config {

    private final Configuration config;

    public int itemBackpackID;
    public int itemVialHolderID;

    public int backpackSize;

    public Config(File file) {
        config = new Configuration(file);
        config.load();
        loadGeneral();
        loadItems();
        config.save();
    }

    private void loadItems() {
        Property prop;
        prop = config.get(Configuration.CATEGORY_ITEM, "Backpack", 31500);
        itemBackpackID = prop.getInt();
        prop = config.get(Configuration.CATEGORY_ITEM, "Vial Holder", 31501);
        itemVialHolderID = prop.getInt();
    }

    private void loadGeneral() {
        Property prop;
        prop = config.get(Configuration.CATEGORY_GENERAL, "Backpack Size", 3);
        prop.comment = "How many rows the backpack has. Default = 3";
        backpackSize = prop.getInt();
    }
}
