package marumasa.show_damage.DamageDisplay;

import marumasa.show_damage.database;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveDamageDisplay extends BukkitRunnable {
    private final TextDisplay target;

    public RemoveDamageDisplay(TextDisplay textDisplay) {
        target = textDisplay;
    }

    @Override
    public void run() {
        database.DamageDisplayList.remove(target);
        target.remove();
    }
}
