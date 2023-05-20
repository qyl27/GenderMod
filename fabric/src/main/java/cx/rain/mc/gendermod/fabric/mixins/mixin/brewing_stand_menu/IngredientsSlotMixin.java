package cx.rain.mc.gendermod.fabric.mixins.mixin.brewing_stand_menu;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.inventory.BrewingStandMenu$IngredientsSlot")
public abstract class IngredientsSlotMixin {
    @Inject(method = "mayPlace", at = @At("TAIL"), cancellable = true)
    private void gendermod$mayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(Items.LIGHT_BLUE_DYE)) {
            cir.setReturnValue(true);
        }
    }
}
