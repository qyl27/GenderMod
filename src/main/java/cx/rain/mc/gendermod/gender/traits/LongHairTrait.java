package cx.rain.mc.gendermod.gender.traits;

import cx.rain.mc.gendermod.client.renderer.model.provider.IModelProvider;
import cx.rain.mc.gendermod.client.renderer.model.provider.LongHairModelProvider;

public class LongHairTrait extends GenderTrait implements ITraitHasModel {

    public LongHairTrait(String name) {
        super(name);
    }

    @Override
    public IModelProvider getTraitModelProvider() {
        return new LongHairModelProvider();
    }
}
