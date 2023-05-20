package cx.rain.mc.gendermod.fabric.mixins.mixin.brewing_stand_menu;

import cx.rain.mc.gendermod.item.GModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.inventory.BrewingStandMenu$PotionSlot")
public abstract class PotionSlotMixin {
    @Inject(method = "mayPlace", at = @At("TAIL"), cancellable = true)
    private void gendermod$mayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(Items.DRAGON_BREATH)
                || stack.is(GModItems.GENDER_TRANSFORM_POTION.get())
                || stack.is(GModItems.GENDER_TRANSFORM_SPLASH_POTION.get())
                || stack.is(GModItems.GENDER_TRANSFORM_LINGERING_POTION.get())) {
            cir.setReturnValue(true);
        }
    }
}
