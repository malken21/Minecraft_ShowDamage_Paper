package marumasa.show_damage.DamageDisplay;

import marumasa.show_damage.config.Config;
import marumasa.show_damage.database;
import marumasa.show_damage.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class SummonDamageDisplay {
    public static void run(LivingEntity target, double damage, Config cfg, minecraft mc) {
        if (cfg.hideList.DamageDisplay.contains(target.getType().name())) return;
        TextDisplay textDisplay = (TextDisplay) target.getWorld().spawnEntity(target.getLocation().add(0, 1, 0), EntityType.TEXT_DISPLAY);
        run(damage, cfg, mc, textDisplay);
    }

    public static void run(LivingEntity target, Entity attacker, double damage, Config cfg, minecraft mc) {
        final Location location = target.getLocation().clone();

        Vector direction = attacker.getLocation().toVector().subtract(location.toVector());
        location.add(direction.normalize().multiply(target.getWidth() * 1.5));
        location.add(0, 1, 0);

        TextDisplay textDisplay = (TextDisplay) target.getWorld().spawnEntity(location, EntityType.TEXT_DISPLAY);
        run(damage, cfg, mc, textDisplay);
    }

    public static void run(double damage, Config cfg, minecraft mc, TextDisplay textDisplay) {

        textDisplay.text(Component.text(String.format("%.0f", damage)));
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setSeeThrough(true);
        textDisplay.addScoreboardTag(cfg.tag.DamageDisplay);

        RemoveDamageDisplay removeDamageDisplay = new RemoveDamageDisplay(textDisplay);
        removeDamageDisplay.runTaskLater(mc, 20);
        database.DamageDisplayList.put(textDisplay, removeDamageDisplay);

        new AnimationDamageDisplay(textDisplay).runTaskLater(mc, 2);
    }
}
