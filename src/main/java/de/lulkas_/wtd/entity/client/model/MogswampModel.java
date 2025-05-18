package de.lulkas_.wtd.entity.client.model;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.client.anim.MogswampAnimations;
import de.lulkas_.wtd.entity.client.renderer.ModelWithGetRightArm;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class MogswampModel<T extends MogswampEntity> extends SinglePartEntityModel<T> implements ModelWithArms, ModelWithGetRightArm {
    public static final EntityModelLayer MOGSWAMP = new EntityModelLayer(Identifier.of(WandyTDefense.MOD_ID, "mogswamp"), "main");
    public static final EntityModelLayer MOGSWAMP_ARCHER = new EntityModelLayer(Identifier.of(WandyTDefense.MOD_ID, "mogswamp_archer"), "main");

    private final ModelPart mogswamp;
    private final ModelPart head;
    private final ModelPart leftArm;
    private final ModelPart rightArm;

    public MogswampModel(ModelPart root) {
        this.mogswamp = root.getChild("mogswamp");
        this.head = this.mogswamp.getChild("head");
        this.leftArm = this.mogswamp.getChild("leftarm");
        this.rightArm = this.mogswamp.getChild("rightarm");
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

        this.animateMovement(MogswampAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, MogswampAnimations.IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackingAnimationState, MogswampAnimations.ATTACK, ageInTicks, 1f);
    }

    protected void setHeadAngles(float headYaw, float headPitch) {
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

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        this.getArm(arm).rotate(matrices);
    }

    protected ModelPart getArm(Arm arm) {
        return arm == Arm.LEFT ? this.leftArm : this.rightArm;
    }

    @Override
    public ModelPart getRightArm() {
        return this.rightArm;
    }
}
