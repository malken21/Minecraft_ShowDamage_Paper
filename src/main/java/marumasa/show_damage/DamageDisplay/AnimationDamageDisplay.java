package marumasa.show_damage.DamageDisplay;

import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

public class AnimationDamageDisplay extends BukkitRunnable {
    private final TextDisplay target;
    private final Transformation transformation;

    public AnimationDamageDisplay(TextDisplay textDisplay) {
        target = textDisplay;
        transformation = target.getTransformation();

        target.setTransformation(
                new Transformation(
                        transformation.getTranslation(),
                        transformation.getLeftRotation(),
                        transformation.getScale().set(1.5),
                        transformation.getRightRotation()
                )
        );
        target.setInterpolationDuration(20);
    }

    @Override
    public void run() {
        target.setTransformation(
                new Transformation(
                        transformation.getTranslation().add(0, 0.5f, 0),
                        transformation.getLeftRotation(),
                        transformation.getScale().set(0),
                        transformation.getRightRotation()
                )
        );
    }
}
