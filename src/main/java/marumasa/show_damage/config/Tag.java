package marumasa.show_damage.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Tag {
    public final String HealthBar;
    public final String DamageDisplay;

    public Tag(FileConfiguration config) {
        //タグ名
        HealthBar = config.getString("tag.HealthBar");
        DamageDisplay = config.getString("tag.DamageDisplay");
    }
}
