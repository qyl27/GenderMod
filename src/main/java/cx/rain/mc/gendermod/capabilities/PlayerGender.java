package cx.rain.mc.gendermod.capabilities;

import cx.rain.mc.gendermod.gender.Gender;
import cx.rain.mc.gendermod.gender.GenderRegistry;
import cx.rain.mc.gendermod.gender.traits.GenderTrait;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

public class PlayerGender implements IPlayerGender {
    public static final String TAG_GENDER_NAME = "gender";
    public static final String TAG_GENDER_TRAITS_NAME = "traits";

    public static final Random RANDOM = new Random();

    protected Gender gender;

    protected Set<GenderTrait> traits = new HashSet<>();

    public PlayerGender(boolean random) {
        if (random) {
            randGender();
        }
    }

    @Override
    public void randGender() {
        if (RANDOM.nextBoolean()) {
            gender = GenderRegistry.MALE.get();
        } else {
            gender = GenderRegistry.FEMALE.get();
        }
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean hasGenderTrait(GenderTrait trait) {
        return traits.contains(trait);
    }

    @Override
    public Set<GenderTrait> getGenderTrait() {
        return traits;
    }

    @Override
    public void setGenderTrait(Set<GenderTrait> traits) {
        this.traits = traits;
    }

    @Override
    public void addTrait(GenderTrait trait) {
        traits.add(trait);
    }

    @Override
    public void removeTrait(GenderTrait trait) {
        traits.remove(trait);
    }

    @Override
    public void clearTrait(GenderTrait trait) {
        traits.clear();
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();

        tag.putString(TAG_GENDER_NAME, GenderRegistry.GENDER_REGISTRY.get().getKey(gender).toString());

        var traitsList = new ListTag();
        for (var trait : traits) {
            traitsList.add(StringTag.valueOf(GenderRegistry.GENDER_TRAITS_REGISTRY.get().getKey(trait).toString()));
        }
        tag.put(TAG_GENDER_TRAITS_NAME, traitsList);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        var genderName = tag.getString(TAG_GENDER_NAME);
        gender = GenderRegistry.GENDER_REGISTRY.get().getValue(new ResourceLocation(genderName));

//        traits.clear();
        for (var traitTag : tag.getList(TAG_GENDER_TRAITS_NAME, Tag.TAG_STRING)) {
            if (traitTag instanceof StringTag traitName) {
                traits.add(GenderRegistry.GENDER_TRAITS_REGISTRY.get().getValue(new ResourceLocation(traitName.getAsString())));
            }
        }
    }
}
