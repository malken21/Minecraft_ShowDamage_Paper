package marumasa.show_damage;

import marumasa.show_damage.DamageDisplay.RemoveDamageDisplay;
import marumasa.show_damage.HealthBar.RemoveHealthBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;

import java.util.HashMap;
import java.util.Map;

public class database {
    public static final Map<LivingEntity, RemoveHealthBar> ShowHealthBarEntityList = new HashMap<>();
    public static final Map<TextDisplay, RemoveDamageDisplay> DamageDisplayList = new HashMap<>();
    public static final String[] RemoveTagList = new String[2];
}
