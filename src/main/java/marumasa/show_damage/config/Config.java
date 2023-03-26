package marumasa.show_damage.config;

import marumasa.show_damage.database;
import marumasa.show_damage.minecraft;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public final Tag tag;
    public final Show show;

    public Config(final minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        tag = new Tag(config);
        show = new Show(config);

        if (show.DamageDisplay) database.RemoveTagList[0] = tag.DamageDisplay;
        if (show.HealthBar) database.RemoveTagList[1] = tag.HealthBar;
    }
}
