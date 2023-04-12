package cx.rain.mc.gendermod.gender;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.gender.traits.BreastTrait;
import cx.rain.mc.gendermod.gender.traits.GenderTrait;
import cx.rain.mc.gendermod.gender.traits.LongHairTrait;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class GenderRegistry {
    public static final DeferredRegister<Gender> GENDERS = DeferredRegister.create(new ResourceLocation(GenderMod.MODID, "gender"), GenderMod.MODID);
    public static final Supplier<IForgeRegistry<Gender>> GENDER_REGISTRY = GENDERS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<Gender> MALE = GENDERS.register("male", () -> new Gender("male"));
    public static final RegistryObject<Gender> FEMALE = GENDERS.register("female", () -> new Gender("female"));
    public static final RegistryObject<Gender> NON_BINARY = GENDERS.register("non_binary", () -> new Gender("non_binary"));

    public static final DeferredRegister<GenderTrait> GENDER_TRAITS = DeferredRegister.create(new ResourceLocation(GenderMod.MODID, "gender_traits"), GenderMod.MODID);
    public static final Supplier<IForgeRegistry<GenderTrait>> GENDER_TRAITS_REGISTRY = GENDER_TRAITS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<GenderTrait> LONG_HAIR = GENDER_TRAITS.register("long_hair", () -> new LongHairTrait("long_hair"));
    public static final RegistryObject<GenderTrait> BREAST = GENDER_TRAITS.register("breast", () -> new BreastTrait("breast"));
    public static final RegistryObject<GenderTrait> BEARD = GENDER_TRAITS.register("beard", () -> new GenderTrait("beard"));

    public static void register(IEventBus bus) {
        GENDERS.register(bus);
        GENDER_TRAITS.register(bus);
    }
}
