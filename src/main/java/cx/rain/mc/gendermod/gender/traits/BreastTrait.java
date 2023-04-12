package cx.rain.mc.gendermod.gender.traits;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.client.renderer.model.BreastModel;
import cx.rain.mc.gendermod.client.renderer.model.LongHairModel;
import cx.rain.mc.gendermod.client.renderer.model.TraitsModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class BreastTrait extends GenderTrait implements ITraitHasModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(GenderMod.MODID, "breast"), "main");

    public BreastTrait(String name) {
        super(name);
    }

    @Override
    public <P extends Player> TraitsModel<P> getTraitModel(ModelPart root) {
        return new BreastModel<>(root);
    }

    @Override
    public LayerDefinition createLayer() {
        return BreastModel.createLayer();
    }

    @Override
    public ModelLayerLocation getTraitModelLayerLocation() {
        return LAYER_LOCATION;
    }
}
