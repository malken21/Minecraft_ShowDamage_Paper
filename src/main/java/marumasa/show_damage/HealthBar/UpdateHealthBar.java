package marumasa.show_damage.HealthBar;

import marumasa.show_damage.config.Config;
import marumasa.show_damage.database;
import marumasa.show_damage.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateHealthBar extends BukkitRunnable {

    private final LivingEntity target;
    private final Config cfg;
    private final minecraft mc;

    private final double old_health;

    public UpdateHealthBar(LivingEntity entity, Config config, minecraft minecraft) {
        target = entity;
        cfg = config;
        mc = minecraft;
        old_health = entity.getHealth();
    }

    @Override
    public void run() {
        final double health = target.getHealth();
        if (old_health == health || cfg.hideList.HealthBar.contains(target.getType().name())) return;

        if (target.isDead()) {
            final RemoveHealthBar old_UndoHealthBar = database.ShowHealthBarEntityList.get(target);
            if (old_UndoHealthBar == null) return;
            old_UndoHealthBar.cancel();
            new RemoveHealthBar(old_UndoHealthBar.text, target).run();
            return;
        }

        final AttributeInstance MaxHealthAttribute = target.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (MaxHealthAttribute == null) return;

        final StringBuilder name = new StringBuilder("§c▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏▏§r");
        name.insert((int) ((30 * health / MaxHealthAttribute.getValue()) + 2), "§8");

        final RemoveHealthBar removeHealthBar;
        if (database.ShowHealthBarEntityList.containsKey(target)) {

            final RemoveHealthBar old_RemoveHealthBar = database.ShowHealthBarEntityList.get(target);
            old_RemoveHealthBar.cancel();
            old_RemoveHealthBar.text.text(Component.text(name.toString()));

            removeHealthBar = new RemoveHealthBar(old_RemoveHealthBar.text, target);

        } else {

            final SummonHealthBar summonHealthBar = new SummonHealthBar(target, name.toString(), cfg);
            summonHealthBar.runTaskLater(mc, 2);
            removeHealthBar = new RemoveHealthBar(summonHealthBar.textDisplay, target);

        }
        database.ShowHealthBarEntityList.put(target, removeHealthBar);
        removeHealthBar.runTaskLater(mc, 40);
    }
}
