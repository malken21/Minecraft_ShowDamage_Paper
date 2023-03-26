package marumasa.show_damage.HealthBar;

import marumasa.show_damage.config.Config;
import marumasa.show_damage.minecraft;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;

public class SummonHealthBar {
    public static TextDisplay run(LivingEntity target, String HealthBar, Config config, minecraft mc) {

        final TextDisplay textDisplay = (TextDisplay) target.getWorld().spawnEntity(target.getLocation(), EntityType.TEXT_DISPLAY);
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
        textDisplay.text(Component.text(HealthBar));
        target.addPassenger(textDisplay);

        return textDisplay;
    }
}
