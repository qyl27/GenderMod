package cx.rain.mc.gendermod.capabilities;

import cx.rain.mc.gendermod.GenderMod;
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
public class GModCapabilities {
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
        if (!event.isWasDeath()) {
            event.getOriginal().getCapability(PLAYER_GENDER_CAPABILITY).ifPresent(original ->
            {
                event.getEntity().getCapability(PLAYER_GENDER_CAPABILITY).ifPresent(newCap -> {
                    newCap.deserializeNBT(original.serializeNBT());
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        syncCapability(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerSpawn(PlayerEvent.PlayerRespawnEvent event) {
        syncCapability(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncCapability(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        var target = event.getTarget();
        if (target instanceof Player player) {
            syncCapability(player);
        }
    }

    private static void syncCapability(Player capabilityHolder) {
        var cap = capabilityHolder.getCapability(PLAYER_GENDER_CAPABILITY);
        if (cap.isPresent()) {
            var genderCap = cap.orElseThrow(RuntimeException::new);
            GenderMod.getInstance().getNetworking().updateGenderCapability(capabilityHolder, genderCap);
        }
    }
}
