package marumasa.show_damage.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Show {
    public final boolean HealthBar;
    public final boolean DamageDisplay;

    public Show(FileConfiguration config) {
        //有効かどうか
        HealthBar = config.getBoolean("show.HealthBar");
        DamageDisplay = config.getBoolean("show.DamageDisplay");
    }
}
