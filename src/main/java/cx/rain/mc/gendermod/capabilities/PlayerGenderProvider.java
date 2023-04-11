package cx.rain.mc.gendermod.capabilities;

import cx.rain.mc.gendermod.gender.GenderRegistry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PlayerGenderProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    public static final Random RANDOM = new Random();

    private IPlayerGender gender = new PlayerGender();

    private final LazyOptional<IPlayerGender> optional = LazyOptional.of(() -> gender);

    public PlayerGenderProvider() {
        if (gender.getGender() == null) {
            if (RANDOM.nextBoolean()) {
                gender.setGender(GenderRegistry.MALE.get());
            } else {
                gender.setGender(GenderRegistry.FEMALE.get());
            }
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        return gender.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        gender.deserializeNBT(tag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
        if (cap == ModCapabilities.PLAYER_GENDER_CAPABILITY) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }
}
