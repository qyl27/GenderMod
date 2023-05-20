package cx.rain.mc.gendermod.fabric;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.fabric.core.GenderManagerFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public class GenderModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        new GenderMod().init();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new GenderManagerFabric());
    }
}
