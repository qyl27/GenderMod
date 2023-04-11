package cx.rain.mc.gendermod.client;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.client.renderer.HairRenderer;
import cx.rain.mc.gendermod.client.renderer.layer.LongHairLayer;
import cx.rain.mc.gendermod.client.renderer.model.LongHairModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GenderMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenderModRenderer {
    public static final ModelLayerLocation LONG_HAIR_LOCATION = new ModelLayerLocation(new ResourceLocation(GenderMod.MODID, "long_hair"), "main");

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityType.PLAYER, ctx -> (EntityRenderer) new HairRenderer(ctx));
    }

    @SubscribeEvent
    public static void onRegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LONG_HAIR_LOCATION, () -> LongHairModel.createLayer(CubeDeformation.NONE));
    }

    @SubscribeEvent
    public static void onAddLayer(EntityRenderersEvent.AddLayers event) {
        var renderer =  event.getRenderer(EntityType.PLAYER);
        renderer.addLayer((RenderLayer) new LongHairLayer((RenderLayerParent) renderer, new LongHairModel(event.getEntityModels().bakeLayer(GenderModRenderer.LONG_HAIR_LOCATION)), 1));
    }
}
