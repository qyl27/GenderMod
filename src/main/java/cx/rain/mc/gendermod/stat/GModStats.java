package cx.rain.mc.gendermod.stat;

import cx.rain.mc.gendermod.GenderMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraftforge.eventbus.api.IEventBus;

public class GModStats {
    public static final ResourceLocation TRANSGENDER_TIMES = makeCustomStat(new ResourceLocation(GenderMod.MODID, "transgender_times"), StatFormatter.DEFAULT);

    public static void register(IEventBus bus) {
        // Do nothing.
    }

    public static ResourceLocation makeCustomStat(ResourceLocation key, StatFormatter formatter) {
        Registry.register(BuiltInRegistries.CUSTOM_STAT, key.getPath(), key);
        Stats.CUSTOM.get(key, formatter);
        return key;
    }
}
