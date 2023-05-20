package cx.rain.mc.gendermod.utility;

import cx.rain.mc.gendermod.item.GModItems;
import cx.rain.mc.gendermod.item.potion.GModPotions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

public class PotionHelper {
    public static ItemStack getGenderTransformPotion() {
        var stack = new ItemStack(GModItems.GENDER_TRANSFORM_POTION.get());
        PotionUtils.setPotion(stack, GModPotions.GENDER_TRANSFORM.get());
        return stack;
    }

    public static ItemStack getGenderTransformSplashPotion() {
        var stack = new ItemStack(GModItems.GENDER_TRANSFORM_SPLASH_POTION.get());
        PotionUtils.setPotion(stack, GModPotions.GENDER_TRANSFORM.get());
        return stack;
    }

    public static ItemStack getGenderTransformLingeringPotion() {
        var stack = new ItemStack(GModItems.GENDER_TRANSFORM_LINGERING_POTION.get());
        PotionUtils.setPotion(stack, GModPotions.GENDER_TRANSFORM.get());
        return stack;
    }
}
