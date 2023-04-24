package cx.rain.mc.gendermod.effect;

import cx.rain.mc.gendermod.GenderMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class GModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(GenderMod.MOD_ID, Registries.MOB_EFFECT);

    public static void register() {
        EFFECTS.register();
    }

    public static final RegistrySupplier<MobEffect> GENDER_TRANSFORM = EFFECTS.register("gender_transform", GenderTransformEffect::new);

}
