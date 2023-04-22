package cx.rain.mc.gendermod.stat;

import cx.rain.mc.gendermod.GenderMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GModStats {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, GenderMod.MODID);

    public static final RegistryObject<ResourceLocation> GENDER_TRANSITIONS = STATS.register("gender_transitions", () -> new ResourceLocation(GenderMod.MODID, "gender_transitions"));

    public static void register(IEventBus bus) {
        STATS.register(bus);
    }
}
