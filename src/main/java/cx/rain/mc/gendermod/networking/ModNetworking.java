package cx.rain.mc.gendermod.networking;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.networking.packet.S2CUpdateGenderCapabilityPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {
    private SimpleChannel channel;

    private static final ResourceLocation MESSAGES_NAME = new ResourceLocation(GenderMod.MODID, "messages");

    private static int ID = 0;

    public ModNetworking() {
        channel = NetworkRegistry.newSimpleChannel(MESSAGES_NAME,
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
        return channel;
    }

    private void registerMessages() {
        channel.messageBuilder(S2CUpdateGenderCapabilityPacket.class, nextId())
                .encoder(S2CUpdateGenderCapabilityPacket::toBytes)
                .decoder(S2CUpdateGenderCapabilityPacket::new)
                .consumerMainThread(S2CUpdateGenderCapabilityPacket::clientHandleOnMain)
                .add();
    }

    public void updateGenderCapability(CompoundTag tag) {
        channel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.noArg(), new S2CUpdateGenderCapabilityPacket(tag));
    }

    public void updateGenderCapability(CompoundTag tag, ServerPlayer player) {
        channel.sendTo(new S2CUpdateGenderCapabilityPacket(tag), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}
