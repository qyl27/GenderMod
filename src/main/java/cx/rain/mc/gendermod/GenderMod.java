package cx.rain.mc.gendermod;

import cx.rain.mc.gendermod.gender.GenderRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GenderMod.MODID)
public class GenderMod {
    public static final String MODID = "gendermod";
    public static final String NAME = "InfinRain's Gender Mod";
    public static final String VERSION = "1.19.4-1.0.0";

    public GenderMod() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        GenderRegistry.register(bus);
    }
}
