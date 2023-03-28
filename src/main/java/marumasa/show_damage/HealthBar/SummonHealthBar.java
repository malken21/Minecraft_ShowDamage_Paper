package marumasa.show_damage.HealthBar;

import marumasa.show_damage.config.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

public class SummonHealthBar extends BukkitRunnable {
    private final String text;

    public final TextDisplay textDisplay;

    public SummonHealthBar(LivingEntity target, String HealthBar, Config config) {
        text = HealthBar;

        textDisplay = (TextDisplay) target.getWorld().spawnEntity(target.getLocation(), EntityType.TEXT_DISPLAY);

        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setSeeThrough(true);
        Transformation transformation = textDisplay.getTransformation();
        textDisplay.addScoreboardTag(config.tag.HealthBar);

        textDisplay.setTransformation(new Transformation(
                transformation.getTranslation().add(0, 0.5f, 0),
                transformation.getLeftRotation(),
                transformation.getScale(),
                transformation.getRightRotation()
        ));

        target.addPassenger(textDisplay);
    }

    @Override
    public void run() {
        textDisplay.text(Component.text(text));
    }
}
