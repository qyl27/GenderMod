package cx.rain.mc.gendermod.mixins.mixin;

import cx.rain.mc.gendermod.GConstants;
import cx.rain.mc.gendermod.mixins.interfaces.IGenderHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin implements IGenderHolder {

    private String gendermod$gender;    // Todo.
    private boolean gendermod$hasTransformedGender = false;

    @Inject(at = @At("TAIL"), method = "addAdditionalSaveData")
    private void gendermod$addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        var genderTag = new CompoundTag();
        genderTag.putBoolean(GConstants.TAG_PLAYER_GENDER_TRANSFORMED_NAME, gendermod$hasTransformedGender);

        tag.put(GConstants.TAG_PLAYER_GENDER_COMPOUND_NAME, genderTag);
    }

    @Inject(at = @At("TAIL"), method = "readAdditionalSaveData")
    private void gendermod$readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        var genderTag = tag.getCompound(GConstants.TAG_PLAYER_GENDER_COMPOUND_NAME);

        gendermod$hasTransformedGender = genderTag.getBoolean(GConstants.TAG_PLAYER_GENDER_TRANSFORMED_NAME);
    }

    @Override
    public String getGender() {
        return gendermod$gender;
    }

    @Override
    public void setGender(String gender) {
        gendermod$gender = gender;
    }

    @Override
    public boolean hasTransformedGender() {
        return gendermod$hasTransformedGender;
    }

    @Override
    public void setTransformedGender(boolean hasTransformed) {
        gendermod$hasTransformedGender = hasTransformed;
    }
}
