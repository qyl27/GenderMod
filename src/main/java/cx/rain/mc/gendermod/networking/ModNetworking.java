package cx.rain.mc.gendermod.networking;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.networking.packet.S2CUpdateGenderCapabilityPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {
    private static SimpleChannel CHANNEL;

    private static final ResourceLocation CHANNEL_RL = new ResourceLocation(GenderMod.MODID, "messages");

    private static int ID = 0;

    public ModNetworking() {
        CHANNEL = NetworkRegistry.newSimpleChannel(CHANNEL_RL,
                () -> GenderMod.VERSION,
                GenderMod.VERSION::equals,
                GenderMod.VERSION::equals
        );

        registerMessages();
    }

    private static synchronized int nextId() {
        return ID++;
    }

    public SimpleChannel getChannel() {
        return CHANNEL;
    }

    private void registerMessages() {
        CHANNEL.messageBuilder(S2CUpdateGenderCapabilityPacket.class, nextId())
                .encoder(S2CUpdateGenderCapabilityPacket::toBytes)
                .decoder(S2CUpdateGenderCapabilityPacket::new)
                .consumerMainThread(S2CUpdateGenderCapabilityPacket::clientHandleOnMain)
                .add();
    }

    public static void updateGenderCapability(CompoundTag tag) {
        // Todo: capability sync
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY.noArg(), new S2CUpdateGenderCapabilityPacket(tag));
    }
}
