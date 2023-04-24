package cx.rain.mc.gendermod.stat;

import cx.rain.mc.gendermod.GenderMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;

public class GModStats {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(GenderMod.MOD_ID, Registries.CUSTOM_STAT);

    public static final RegistrySupplier<ResourceLocation> GENDER_TRANSITIONS = STATS.register("gender_transforms", () -> new ResourceLocation(GenderMod.MOD_ID, "gender_transforms"));

    public static void register() {
        STATS.register();
    }

    public static Stat<ResourceLocation> get(ResourceLocation key) {
        return Stats.CUSTOM.get(key);
    }
}
