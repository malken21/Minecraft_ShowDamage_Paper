package marumasa.show_damage.config;

import marumasa.show_damage.minecraft;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public final Tag tag;
    public final Show show;
    public final HideList hideList;

    public final List<String> removeTagList = new ArrayList<>();

    public Config(final minecraft plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        tag = new Tag(config);
        show = new Show(config);
        hideList = new HideList(config);

        if (show.DamageDisplay) removeTagList.add(tag.DamageDisplay);
        if (show.HealthBar) removeTagList.add(tag.HealthBar);
    }
}
