package cx.rain.mc.gendermod.capabilities;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.networking.ModNetworking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GenderMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities {
    public static final ResourceLocation PLAYER_GENDER_CAPABILITY_NAME = new ResourceLocation(GenderMod.MODID, "player_gender");

    public static final Capability<IPlayerGender> PLAYER_GENDER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IPlayerGender.class);
    }

    @SubscribeEvent
    public static void onAttachPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            var provider = new PlayerGenderProvider();
            event.addCapability(PLAYER_GENDER_CAPABILITY_NAME, provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PLAYER_GENDER_CAPABILITY).ifPresent(original ->
            {
                event.getEntity().getCapability(PLAYER_GENDER_CAPABILITY).ifPresent(newCap -> {
                    newCap.deserializeNBT(original.serializeNBT());
                    newCap.randGender();
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerSpawn(PlayerEvent.PlayerRespawnEvent event) {
        // Todo: capability sync.
    }
}
