package cx.rain.mc.gendermod;

import cx.rain.mc.gendermod.gender.GenderRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(GenderMod.MODID)
public class GenderMod {
    public static final String MODID = "gendermod";
    public static final String NAME = "InfinRain's Gender Mod";
    public static final String VERSION = "1.19.4-1.0.0";

    public static GenderMod INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(NAME);

    public GenderMod() {
        INSTANCE = this;

        getLogger().info("Loading InfinRain's Gender Mod!");

        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        GenderRegistry.register(bus);

        getLogger().info("Equality!");
    }

    public static GenderMod getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return logger;
    }
}
