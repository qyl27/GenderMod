package cx.rain.mc.gendermod.capabilities;

import cx.rain.mc.gendermod.gender.Gender;
import cx.rain.mc.gendermod.gender.traits.GenderTrait;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Set;

public interface IPlayerGender extends INBTSerializable<CompoundTag> {

    void randGender();
    Gender getGender();
    void setGender(Gender gender);

    boolean hasGenderTrait(GenderTrait trait);
    Set<GenderTrait> getGenderTrait();
    void setGenderTrait(Set<GenderTrait> traits);
    void addTrait(GenderTrait trait);
    void removeTrait(GenderTrait trait);
    void clearTrait(GenderTrait trait);
}
