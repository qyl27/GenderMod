package cx.rain.mc.gendermod.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import cx.rain.mc.gendermod.capabilities.ModCapabilities;
import cx.rain.mc.gendermod.gender.GenderRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.player.Player;

public class LongHairModel<P extends Player> extends TraitsModel<P> {
    private final ModelPart hairModel;

    public LongHairModel(ModelPart root) {
        hairModel = root.getChild("long_hair");
    }

    @Override
    public void setupAnim(P entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float headYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        hairModel.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public static LayerDefinition createLayer() {
        var cubeDeformation = CubeDeformation.NONE;
        var meshDefinition = HumanoidModel.createMesh(cubeDeformation, 0);
        var partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("long_hair", CubeListBuilder.create().texOffs(32, 0).addBox(-4, 0, 2, 8, 8, 1, cubeDeformation), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public boolean shouldRender(P entity) {
        // Todo: capability sync.
        var cap = entity.getCapability(ModCapabilities.PLAYER_GENDER_CAPABILITY).orElseThrow(RuntimeException::new);
        System.out.println(cap.hasGenderTrait(GenderRegistry.LONG_HAIR.get()));
        return cap.hasGenderTrait(GenderRegistry.LONG_HAIR.get());
    }
}
