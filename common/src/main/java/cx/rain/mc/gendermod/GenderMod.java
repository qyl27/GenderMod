package cx.rain.mc.gendermod;

import cx.rain.mc.gendermod.effect.GModEffects;
import cx.rain.mc.gendermod.item.GModItems;
import cx.rain.mc.gendermod.item.potion.GModPotions;
import cx.rain.mc.gendermod.stat.GModStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenderMod {
    public static final String MOD_ID = "gendermod";
    public static final String NAME = "InfinRain's Gender Mod";
    public static final String VERSION = "1.19.4-1.0.0";

    public static GenderMod INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(NAME);

    public GenderMod() {
        INSTANCE = this;
        getLogger().info("Loading InfinRain's Gender Mod! Ver: " + VERSION);
    }

    public static GenderMod getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return logger;
    }

    public void init() {
        GModItems.register();
        GModEffects.register();
        GModStats.register();
        GModPotions.register();

        getLogger().info("Equality!");
    }
}