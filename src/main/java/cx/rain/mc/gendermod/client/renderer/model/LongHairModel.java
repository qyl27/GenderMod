package cx.rain.mc.gendermod.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.player.AbstractClientPlayer;

public class LongHairModel extends EntityModel<AbstractClientPlayer> {
    private final ModelPart hairModel;

    public LongHairModel(ModelPart root) {
        hairModel = root.getChild("long_hair");
    }

    @Override
    public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float headYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        hairModel.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public static LayerDefinition createLayer(CubeDeformation cubeDeformation) {
        var meshDefinition = new MeshDefinition();
        var partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("long_hair", CubeListBuilder.create().texOffs(32, 0).addBox(0, 0, 0, 50, 100, 20, cubeDeformation), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
