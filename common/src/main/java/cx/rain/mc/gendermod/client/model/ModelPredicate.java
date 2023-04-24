package cx.rain.mc.gendermod.client.model;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@FunctionalInterface
public interface ModelPredicate<T> {
    boolean matches(ItemStack stack, ClientLevel level, LivingEntity entity, T value);
}
