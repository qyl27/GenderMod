package cx.rain.mc.gendermod.effect;

import cx.rain.mc.gendermod.mixins.interfaces.IGenderHolder;
import cx.rain.mc.gendermod.stat.GModStats;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class GenderTransformEffect extends InstantenousMobEffect {
    protected GenderTransformEffect() {
        super(MobEffectCategory.NEUTRAL, 0x5BCEFA);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource,
                                        LivingEntity livingEntity, int amplifier, double health) {
        if (livingEntity instanceof Player player) {
            var gender = (IGenderHolder) player;
            if (!gender.hasTransformedGender()) {
                player.awardStat(GModStats.get(GModStats.GENDER_TRANSITIONS.get()));

                System.out.println(1);
                // Todo: change gender.
                gender.setTransformedGender(true);
            }
        }
    }
}
