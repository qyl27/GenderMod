package cx.rain.mc.gendermod.gender.traits;

import cx.rain.mc.gendermod.client.renderer.model.TraitsModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.player.Player;

public interface ITraitHasModel {
    <P extends Player> TraitsModel<P> getTraitModel(ModelPart root);

    LayerDefinition createLayer();

    ModelLayerLocation getTraitModelLayerLocation();
}
