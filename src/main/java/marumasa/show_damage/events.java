package marumasa.show_damage;

import marumasa.show_damage.DamageDisplay.SummonDamageDisplay;
import marumasa.show_damage.HealthBar.UpdateHealthBar;
import marumasa.show_damage.config.Config;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.event.world.EntitiesUnloadEvent;

import java.util.Set;

public class events implements Listener {

    private final minecraft mc;
    private final Config cfg;

    public events(Config config, minecraft data) {
        mc = data;
        cfg = config;
    }

    @EventHandler
    public void onEntityLoad(EntitiesLoadEvent event) {
        //エンティティがロードされたら
        for (Entity entity : event.getEntities()) remove(entity);
    }

    @EventHandler
    public void onEntityUnload(EntitiesUnloadEvent event) {
        //エンティティがアンロードされたら
        for (Entity entity : event.getEntities()) remove(entity);
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        //エンティティの体力が回復したら

        //回復されたエンティティが生きているエンティティだったら
        if (event.getEntity() instanceof LivingEntity entity)
            //体力バーを更新
            if (cfg.show.HealthBar) new UpdateHealthBar(entity, cfg, mc).runTaskLater(mc, 0);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        //エンティティがダメージを受けたら

        //ダメージを受けたエンティティが生きているエンティティだったら
        if (event.getEntity() instanceof LivingEntity livingEntity)
            //体力バーを更新
            if (cfg.show.HealthBar) new UpdateHealthBar(livingEntity, cfg, mc).runTaskLater(mc, 0);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //エンティティによってエンティティがダメージを受けたら

        //攻撃されたエンティティが生きているエンティティだったら
        if (event.getEntity() instanceof LivingEntity entity)
            if (cfg.show.DamageDisplay) SummonDamageDisplay.run(entity, event.getDamage(), cfg, mc);
    }

    private void remove(Entity entity) {
        final Set<String> tags = entity.getScoreboardTags();
        for (final String tag : tags)
            if (cfg.removeTagList.contains(tag)) {
                entity.remove();
                return;
            }
    }
}
