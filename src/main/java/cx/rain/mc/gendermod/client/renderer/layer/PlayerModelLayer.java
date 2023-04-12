package cx.rain.mc.gendermod.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.mc.gendermod.client.renderer.model.TraitsModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

public class PlayerModelLayer<P extends LivingEntity, M extends PlayerModel<P>> extends RenderLayer<P, M> {
    protected final TraitsModel<P> model;

    public PlayerModelLayer(RenderLayerParent<P, M> parent, TraitsModel<P> model) {
        super(parent);

        this.model = model;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, P livingEntity,
                       float limbSwing, float limbSwingAmount, float partialTick,
                       float ageInTicks, float headYaw, float headPitch) {
        if (model.shouldRender(livingEntity)) {
            var vertexConsumer = buffer.getBuffer(this.getParentModel().renderType(this.getTextureLocation(livingEntity)));
            model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }
}
