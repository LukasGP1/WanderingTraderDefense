package de.lulkas_.wtd.entity.client;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class MogswampModel<T extends MogswampEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer MOGSWAMP = new EntityModelLayer(Identifier.of(WandyTDefense.MOD_ID, "mogswamp"), "main");

    private final ModelPart mogswamp;
    private final ModelPart head;

    public MogswampModel(ModelPart root) {
        this.mogswamp = root.getChild("mogswamp");
        this.head = this.mogswamp.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData mogswamp = modelPartData.addChild("mogswamp", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = mogswamp.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, 0.0F));

        ModelPartData rightleg = mogswamp.addChild("rightleg", ModelPartBuilder.create().uv(16, 32).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -14.0F, 0.0F));

        ModelPartData leftleg = mogswamp.addChild("leftleg", ModelPartBuilder.create().uv(32, 0).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -14.0F, 0.0F));

        ModelPartData leftarm = mogswamp.addChild("leftarm", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -22.0F, 0.0F));

        ModelPartData rightarm = mogswamp.addChild("rightarm", ModelPartBuilder.create().uv(24, 16).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -22.0F, 0.0F));

        ModelPartData head = mogswamp.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(MogswampEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(MogswampAnimations.ANIM_MOGSWAMP_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, MogswampAnimations.ANIM_MOGSWAMP_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackingAnimationState, MogswampAnimations.ANIM_MOGSWAMP_ATTACK, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

        this.head.yaw = headYaw * ((float)Math.PI / 180f);
        this.head.pitch = headPitch * ((float)Math.PI / 180f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        mogswamp.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return mogswamp;
    }
}
