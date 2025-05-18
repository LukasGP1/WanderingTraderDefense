package de.lulkas_.wtd.entity.client.model;

import de.lulkas_.wtd.entity.client.anim.MogswampAnimations;
import de.lulkas_.wtd.entity.client.anim.MogswampArcherAnimations;
import de.lulkas_.wtd.entity.custom.MogswampArcherEntity;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.client.model.*;

public class MogswampArcherModel<T extends MogswampArcherEntity> extends MogswampModel<T> {
    public MogswampArcherModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData mogswamp = modelPartData.addChild("mogswamp", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = mogswamp.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -6.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, 0.0F));

        ModelPartData rightleg = mogswamp.addChild("rightleg", ModelPartBuilder.create().uv(16, 32).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -14.0F, 0.0F));

        ModelPartData leftleg = mogswamp.addChild("leftleg", ModelPartBuilder.create().uv(32, 0).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -14.0F, 0.0F));

        ModelPartData leftarm = mogswamp.addChild("leftarm", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -22.0F, 0.0F));

        ModelPartData rightarm = mogswamp.addChild("rightarm", ModelPartBuilder.create(), ModelTransform.pivot(-6.0F, -22.0F, 0.0F));

        ModelPartData cube_r1 = rightarm.addChild("cube_r1", ModelPartBuilder.create().uv(24, 16).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
        ModelPartData head = mogswamp.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(MogswampEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(MogswampArcherAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, MogswampArcherAnimations.IDLE, ageInTicks, 1f);
    }
}
