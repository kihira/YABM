package yabm;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;

public class Config {

    private final Configuration config;

    public int itemBackpackID;

    public Config(File file) {
        config = new Configuration(file);
        config.load();
        loadGeneral();
        config.save();
    }

    private void loadGeneral() {
        Property prop;
        prop = config.get(Configuration.CATEGORY_ITEM, "Backpack", 31500);
        itemBackpackID = prop.getInt();
    }
}
