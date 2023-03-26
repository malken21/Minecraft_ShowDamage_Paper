package marumasa.show_damage.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class HideList {
    public final List<String> HealthBar;
    public final List<String> DamageDisplay;

    public HideList(FileConfiguration config) {
        //非表示リスト

        HealthBar = config.getStringList("hideList.HealthBar");
        DamageDisplay = config.getStringList("hideList.DamageDisplay");

        HealthBar.replaceAll(String::toUpperCase);
        DamageDisplay.replaceAll(String::toUpperCase);
    }
}
