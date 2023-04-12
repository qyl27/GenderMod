package cx.rain.mc.gendermod.networking.packet;

import cx.rain.mc.gendermod.utility.ClientPlayerCapabilityHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CUpdateGenderCapabilityPacket {
    protected CompoundTag tag;

    public S2CUpdateGenderCapabilityPacket(CompoundTag tag) {
        this.tag = tag;
    }

    public S2CUpdateGenderCapabilityPacket(ByteBuf byteBuf) {
        var buf = new FriendlyByteBuf(byteBuf);
        tag = buf.readNbt();
    }

    public void toBytes(ByteBuf byteBuf) {
        var buf = new FriendlyByteBuf(byteBuf);
        buf.writeNbt(tag);
    }

    public void clientHandleOnMain(Supplier<NetworkEvent.Context> context) {
        ClientPlayerCapabilityHelper.updateGenderCapability(tag);
    }
}
