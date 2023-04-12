package cx.rain.mc.gendermod.utility;

import cx.rain.mc.gendermod.capabilities.ModCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;

public class ClientPlayerCapabilityHelper {
    public static void updateGenderCapability(CompoundTag tag) {
        Minecraft.getInstance().player.getCapability(ModCapabilities.PLAYER_GENDER_CAPABILITY)
                .orElseThrow(RuntimeException::new)
                .deserializeNBT(tag);
    }
}
