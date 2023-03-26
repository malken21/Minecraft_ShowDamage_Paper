package marumasa.show_damage.HealthBar;

import marumasa.show_damage.config.Config;
import marumasa.show_damage.database;
import marumasa.show_damage.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class UpdateHealthBar extends BukkitRunnable {

    private final LivingEntity target;
    private final Config cfg;
    private final minecraft mc;

    public UpdateHealthBar(LivingEntity entity, Config config, minecraft minecraft) {
        target = entity;
        cfg = config;
        mc = minecraft;
    }

    @Override
    public void run() {

        if (cfg.hideList.HealthBar.contains(target.getType().name())) return;

        if (target.isDead()) {
            if (database.ShowHealthBarEntityList.containsKey(target)) {
                final RemoveHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(target);
                old_UndoHealthBar.cancel();
                new RemoveHealthBar(old_UndoHealthBar.text, target).run();
            }
            return;
        }

        final AttributeInstance MaxHealthAttribute = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (MaxHealthAttribute == null) return;

        final int Health = (int) Math.round(target.getHealth());
        final int MaxHealth = (int) Math.round(MaxHealthAttribute.getValue());

        final StringBuilder name = new StringBuilder("§c▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏§r");
        name.insert((30 * Health / MaxHealth) + 2, "§8");

        final RemoveHealthBar removeHealthBar;
        if (database.ShowHealthBarEntityList.containsKey(target)) {

            final RemoveHealthBar old_RemoveHealthBar = database.ShowHealthBarEntityList.get(target);
            old_RemoveHealthBar.cancel();
            old_RemoveHealthBar.text.text(Component.text(name.toString()));

            removeHealthBar = new RemoveHealthBar(old_RemoveHealthBar.text, target);

        } else {

            removeHealthBar = new RemoveHealthBar(SummonHealthBar.run(target, name.toString(), cfg), target);

        }
        database.ShowHealthBarEntityList.put(target, removeHealthBar);
        removeHealthBar.runTaskLater(mc, 40);
    }
}
