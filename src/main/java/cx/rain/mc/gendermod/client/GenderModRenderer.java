package cx.rain.mc.gendermod.client;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.client.renderer.layer.PlayerModelLayer;
import cx.rain.mc.gendermod.gender.GenderRegistry;
import cx.rain.mc.gendermod.gender.traits.ITraitHasModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GenderMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GenderModRenderer {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onRegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (var traits : GenderRegistry.GENDER_TRAITS_REGISTRY.get().getValues()) {
            if (traits instanceof ITraitHasModel model) {
                var provider = model.getTraitModelProvider();
                event.registerLayerDefinition(provider.getTraitModelLayerLocation(), provider::createLayer);
            }
        }
    }

    @SubscribeEvent
    public static void onAddLayer(EntityRenderersEvent.AddLayers event) {
        LivingEntityRenderer<Player, PlayerModel<Player>> renderer = event.getSkin("default");
        LivingEntityRenderer<Player, PlayerModel<Player>> rendererSlim = event.getSkin("slim");

        var modelSet = event.getEntityModels();
        addLayers(renderer, modelSet);
        addLayers(rendererSlim, modelSet);
    }

    private static void addLayers(LivingEntityRenderer<Player, PlayerModel<Player>> renderer, EntityModelSet modelSet) {
        for (var traits : GenderRegistry.GENDER_TRAITS_REGISTRY.get().getValues()) {
            if (traits instanceof ITraitHasModel model) {
                var provider = model.getTraitModelProvider();
                renderer.addLayer(new PlayerModelLayer<>(renderer,
                        provider.getTraitModel(modelSet.bakeLayer(provider.getTraitModelLayerLocation()))));
            }
        }
    }
}
