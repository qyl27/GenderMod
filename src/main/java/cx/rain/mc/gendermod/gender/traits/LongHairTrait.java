package cx.rain.mc.gendermod.gender.traits;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.client.renderer.model.LongHairModel;
import cx.rain.mc.gendermod.client.renderer.model.TraitsModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class LongHairTrait extends GenderTrait implements ITraitHasModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(GenderMod.MODID, "long_hair"), "main");

    public LongHairTrait(String name) {
        super(name);
    }

    @Override
    public <P extends Player> TraitsModel<P> getTraitModel(ModelPart root) {
        return new LongHairModel<>(root);
    }

    @Override
    public LayerDefinition createLayer() {
        return LongHairModel.createLayer();
    }

    @Override
    public ModelLayerLocation getTraitModelLayerLocation() {
        return LAYER_LOCATION;
    }
}
