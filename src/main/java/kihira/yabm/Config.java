package kihira.yabm;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

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
        config.save();
    }

    private void loadGeneral() {
        Property prop;
        prop = config.get(Configuration.CATEGORY_GENERAL, "Backpack Size", 3);
        prop.comment = "How many rows the backpack has. Default = 3";
        backpackSize = prop.getInt();
    }
}
