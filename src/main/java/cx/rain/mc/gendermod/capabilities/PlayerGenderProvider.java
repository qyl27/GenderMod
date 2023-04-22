package cx.rain.mc.gendermod.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerGenderProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    private IPlayerGender gender = new PlayerGender(true);

    private final LazyOptional<IPlayerGender> optional = LazyOptional.of(() -> gender);

    @Override
    public CompoundTag serializeNBT() {
        if (gender == null) {
            gender = optional.orElse(new PlayerGender(true));
        }

        return gender.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (gender == null) {
            gender = optional.orElse(new PlayerGender(true));
        }

        gender.deserializeNBT(tag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
        if (cap == GModCapabilities.PLAYER_GENDER_CAPABILITY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    public void invalidate() {
        optional.invalidate();
    }
}
