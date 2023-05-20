package cx.rain.mc.gendermod.fabric.mixins.mixin;

import cx.rain.mc.gendermod.item.GModItems;
import cx.rain.mc.gendermod.utility.PotionHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public abstract class BrewingStandBlockEntityMixin {
    @Shadow public abstract ItemStack getItem(int slot);

    @Inject(method = "canPlaceItem", at = @At("TAIL"), cancellable = true)
    private void gendermod$canPlaceItem(int index, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            boolean flag = false;

            if (index == 3) {
                if (stack.is(Items.LIGHT_BLUE_DYE)) {
                    flag = true;
                }
            } else if (stack.is(Items.DRAGON_BREATH)
                    || stack.is(GModItems.GENDER_TRANSFORM_POTION.get())
                    || stack.is(GModItems.GENDER_TRANSFORM_SPLASH_POTION.get())
                    || stack.is(GModItems.GENDER_TRANSFORM_LINGERING_POTION.get())) {
                flag = true;
            }

            if (!getItem(index).isEmpty()) {
                flag = false;
            }

            cir.setReturnValue(flag);
        }
    }

    @Inject(method = "isBrewable", at = @At("RETURN"), cancellable = true)
    private static void gendermod$isBrewable(NonNullList<ItemStack> items, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            var ingredient = items.get(3);
            if (ingredient.is(Items.LIGHT_BLUE_DYE)) {
                for (int i = 0; i < 3; ++i) {
                    ItemStack toBrew = items.get(i);
                    if (toBrew.is(Items.DRAGON_BREATH)) {
                        cir.setReturnValue(true);
                    }
                }
            } else if (ingredient.is(Items.GUNPOWDER)) {
                for (int i = 0; i < 3; ++i) {
                    ItemStack toBrew = items.get(i);
                    if (toBrew.is(GModItems.GENDER_TRANSFORM_POTION.get())) {
                        cir.setReturnValue(true);
                    }
                }
            } else if (ingredient.is(Items.DRAGON_BREATH)) {
                for (int i = 0; i < 3; ++i) {
                    ItemStack toBrew = items.get(i);
                    if (toBrew.is(GModItems.GENDER_TRANSFORM_POTION.get())) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

    @Inject(method = "doBrew", at = @At("HEAD"), cancellable = true)
    private static void gendermod$doBrew(Level level, BlockPos pos, NonNullList<ItemStack> items, CallbackInfo ci) {
        var flag = false;
        var ingredient = items.get(3);
        if (ingredient.is(Items.LIGHT_BLUE_DYE)) {
            for (int i = 0; i < 3; ++i) {
                ItemStack toBrew = items.get(i);
                if (toBrew.is(Items.DRAGON_BREATH)) {
                    items.set(i, PotionHelper.getGenderTransformPotion());
                    flag = true;
                }
            }
        } else if (ingredient.is(Items.GUNPOWDER)) {
            for (int i = 0; i < 3; ++i) {
                ItemStack toBrew = items.get(i);
                if (toBrew.is(GModItems.GENDER_TRANSFORM_POTION.get())) {
                    items.set(i, PotionHelper.getGenderTransformSplashPotion());
                    flag = true;
                }
            }
        } else if (ingredient.is(Items.DRAGON_BREATH)) {
            for (int i = 0; i < 3; ++i) {
                ItemStack toBrew = items.get(i);
                if (toBrew.is(GModItems.GENDER_TRANSFORM_POTION.get())) {
                    items.set(i, PotionHelper.getGenderTransformLingeringPotion());
                    flag = true;
                }
            }
        }

        if (flag) {
            ingredient.shrink(1);

            if (ingredient.getItem().hasCraftingRemainingItem()) {
                ItemStack craftingRemaining = new ItemStack(ingredient.getItem().getCraftingRemainingItem());
                if (ingredient.isEmpty()) {
                    ingredient = craftingRemaining;
                } else {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), craftingRemaining);
                }
            }
            items.set(3, ingredient);
            level.levelEvent(1035, pos, 0);
            ci.cancel();
        }
    }
}
