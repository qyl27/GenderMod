package cx.rain.mc.gendermod.item;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.item.potion.GenderTransLingeringPotionItem;
import cx.rain.mc.gendermod.item.potion.GenderTransPotionItem;
import cx.rain.mc.gendermod.item.potion.GenderTransSplashPotionItem;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GenderMod.MOD_ID, Registries.ITEM);

    public static void register() {
        ITEMS.register();
    }

//    public static final CreativeTabRegistry.TabSupplier TAB = CreativeTabRegistry.create(new ResourceLocation(GenderMod.MOD_ID, "tab"), () -> new ItemStack(GModItems.GENDER_TRANSFORM_SPLASH_POTION.get()));

    public static final RegistrySupplier<Item> GENDER_TRANSFORM_POTION = ITEMS.register("gender_transform_potion", GenderTransPotionItem::new);
    public static final RegistrySupplier<Item> GENDER_TRANSFORM_SPLASH_POTION = ITEMS.register("gender_transform_splash_potion", GenderTransSplashPotionItem::new);
    public static final RegistrySupplier<Item> GENDER_TRANSFORM_LINGERING_POTION = ITEMS.register("gender_transform_lingering_potion", GenderTransLingeringPotionItem::new);
}
