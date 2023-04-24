package cx.rain.mc.gendermod.fabric;

import cx.rain.mc.gendermod.GenderMod;
import net.fabricmc.api.ModInitializer;

public class GenderModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new GenderMod().init();
    }
}
