package cx.rain.mc.gendermod.client.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;

public abstract class TraitsModel<P extends LivingEntity> extends EntityModel<P> {
    public abstract boolean shouldRender(P entity);
}
