package marumasa.show_damage.DamageDisplay;

import marumasa.show_damage.config.Config;
import marumasa.show_damage.database;
import marumasa.show_damage.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;

public class SummonDamageDisplay {
    public static void run(LivingEntity target, double damage, Config config, minecraft mc) {

        TextDisplay textDisplay = (TextDisplay) target.getWorld().spawnEntity(target.getLocation().add(0, 1, 0), EntityType.TEXT_DISPLAY);

        textDisplay.text(Component.text(String.format("%.0f", damage)));
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setSeeThrough(true);
        textDisplay.addScoreboardTag(config.tag.DamageDisplay);

        RemoveDamageDisplay removeDamageDisplay = new RemoveDamageDisplay(textDisplay);
        removeDamageDisplay.runTaskLater(mc, 10);
        database.DamageDisplayList.put(textDisplay, removeDamageDisplay);
    }
}
