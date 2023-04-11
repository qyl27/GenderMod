package cx.rain.mc.gendermod.client.renderer;

import cx.rain.mc.gendermod.client.GenderModRenderer;
import cx.rain.mc.gendermod.client.renderer.layer.LongHairLayer;
import cx.rain.mc.gendermod.client.renderer.model.LongHairModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class HairRenderer extends LivingEntityRenderer<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> {
    public HairRenderer(EntityRendererProvider.Context context) {
        super(context, new LongHairModel(context.bakeLayer(GenderModRenderer.LONG_HAIR_LOCATION)), 1);
        addLayer(new LongHairLayer(this, new LongHairModel(context.bakeLayer(GenderModRenderer.LONG_HAIR_LOCATION)), 1));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer entity) {
        return entity.getSkinTextureLocation();
    }
}
