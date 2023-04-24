package cx.rain.mc.gendermod.forge;

import cx.rain.mc.gendermod.GenderMod;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GenderMod.MOD_ID)
public class GenderModForge {
    public GenderModForge() {
        EventBuses.registerModEventBus(GenderMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        new GenderMod().init();
    }
}
