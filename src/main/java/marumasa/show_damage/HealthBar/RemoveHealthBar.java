package marumasa.show_damage.HealthBar;

import marumasa.show_damage.database;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveHealthBar extends BukkitRunnable {

    public final TextDisplay text;
    public final LivingEntity target;

    public RemoveHealthBar(TextDisplay textDisplay, LivingEntity entity) {
        text = textDisplay;
        target = entity;
    }

    @Override
    public void run() {
        database.ShowHealthBarEntityList.remove(target);
        text.remove();
    }
}
