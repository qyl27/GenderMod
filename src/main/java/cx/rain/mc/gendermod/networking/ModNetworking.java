package cx.rain.mc.gendermod.networking;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.capabilities.IPlayerGender;
import cx.rain.mc.gendermod.networking.packet.UpdateGenderCapabilityS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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
        channel.messageBuilder(UpdateGenderCapabilityS2CPacket.class, nextId())
                .encoder(UpdateGenderCapabilityS2CPacket::toBytes)
                .decoder(UpdateGenderCapabilityS2CPacket::new)
                .consumerMainThread(UpdateGenderCapabilityS2CPacket::clientHandleOnMain)
                .add();
    }

    public void updateGenderCapability(Entity entity, IPlayerGender cap) {
        channel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new UpdateGenderCapabilityS2CPacket(cap.serializeNBT()));
    }
}
