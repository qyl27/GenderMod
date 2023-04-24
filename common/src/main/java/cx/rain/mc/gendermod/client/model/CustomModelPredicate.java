package cx.rain.mc.gendermod.client.model;

import com.google.gson.JsonElement;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public abstract class CustomModelPredicate<T> {
    private ModelPredicate<T> predicate;

    public CustomModelPredicate(ModelPredicate<T> predicate) {
        this.predicate = predicate;
    }

    public boolean matches(ItemStack stack, ClientLevel level, LivingEntity entity, T value) {
        return predicate.matches(stack, level, entity, value);
    }

    public abstract T parse(JsonElement json);
}
