package cx.rain.mc.gendermod.capabilities;

import cx.rain.mc.gendermod.gender.Gender;
import cx.rain.mc.gendermod.gender.traits.GenderTrait;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public interface IPlayerGender extends INBTSerializable<CompoundTag> {

    Gender getGender();
    void setGender(Gender gender);

    List<GenderTrait> getGenderTrait();
    void setGenderTrait(List<GenderTrait> traits);
    void addTrait(GenderTrait trait);
    void removeTrait(GenderTrait trait);
    void clearTrait(GenderTrait trait);
}
