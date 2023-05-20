package cx.rain.mc.gendermod.forge;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.item.GModItems;
import cx.rain.mc.gendermod.utility.PotionHelper;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GenderMod.MOD_ID)
public class GenderModForge {
    public GenderModForge() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(GenderMod.MOD_ID, bus);
        bus.addListener(this::setup);
        new GenderMod().init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(Items.DRAGON_BREATH), Ingredient.of(Items.LIGHT_BLUE_DYE), PotionHelper.getGenderTransformPotion());
        BrewingRecipeRegistry.addRecipe(Ingredient.of(GModItems.GENDER_TRANSFORM_POTION.get()), Ingredient.of(Items.GUNPOWDER), PotionHelper.getGenderTransformSplashPotion());
        BrewingRecipeRegistry.addRecipe(Ingredient.of(GModItems.GENDER_TRANSFORM_POTION.get()), Ingredient.of(Items.DRAGON_BREATH), PotionHelper.getGenderTransformLingeringPotion());
    }
}
