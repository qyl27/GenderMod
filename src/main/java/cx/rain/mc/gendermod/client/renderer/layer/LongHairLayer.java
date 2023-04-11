package cx.rain.mc.gendermod.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.mc.gendermod.client.renderer.model.LongHairModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class LongHairLayer extends RenderLayer<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> {
    protected final LongHairModel model;
    protected final float r;

    public LongHairLayer(RenderLayerParent<AbstractClientPlayer, EntityModel<AbstractClientPlayer>> parent, LongHairModel model, float r) {
        super(parent);

        this.model = model;
        this.r = r;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, AbstractClientPlayer livingEntity,
                       float limbSwing, float limbSwingAmount, float partialTick,
                       float ageInTicks, float headYaw, float headPitch) {
        var vertexConsumer = buffer.getBuffer(this.getParentModel().renderType(this.getTextureLocation(livingEntity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, r, 1, 1, 1);
    }
}
