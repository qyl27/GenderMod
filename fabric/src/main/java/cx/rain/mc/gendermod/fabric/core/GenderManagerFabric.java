package cx.rain.mc.gendermod.fabric.core;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.core.gender.GenderManager;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;

public class GenderManagerFabric extends GenderManager implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(GenderMod.MOD_ID, "genders");
    }
}
