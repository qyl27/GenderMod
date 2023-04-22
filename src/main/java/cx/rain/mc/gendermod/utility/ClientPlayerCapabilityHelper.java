package cx.rain.mc.gendermod.utility;

import cx.rain.mc.gendermod.capabilities.GModCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;

public class ClientPlayerCapabilityHelper {
    public static void updateGenderCapability(CompoundTag tag) {
        Minecraft.getInstance().player.getCapability(GModCapabilities.PLAYER_GENDER_CAPABILITY)
                .orElseThrow(RuntimeException::new)
                .deserializeNBT(tag);
    }
}
