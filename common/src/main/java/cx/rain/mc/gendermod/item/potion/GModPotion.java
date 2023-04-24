package cx.rain.mc.gendermod.item.potion;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.effect.GModEffects;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class GModPotion {
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(GenderMod.MOD_ID, Registries.POTION);

    public static void register() {
        POTION.register();
    }

    public static final RegistrySupplier<Potion> GENDER_TRANSFORM = POTION.register("gender_transform", () -> new Potion(new MobEffectInstance(GModEffects.GENDER_TRANSFORM.get())));
}
