package de.lulkas_.wtd.entity.client.renderer.feature;

import de.lulkas_.wtd.entity.client.renderer.ModelWithGetRightArm;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class MogswampEntityHeldItemFeatureRenderer<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms & ModelWithGetRightArm> extends FeatureRenderer<T, M> {
    private final HeldItemRenderer heldItemRenderer;

    public MogswampEntityHeldItemFeatureRenderer(FeatureRendererContext<T, M> context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.MAINHAND);
        if(!stack.isEmpty()) {
            matrices.push();

            matrices.translate(-0.2f, 0.2f, -0.6f);
            matrices.scale(0.8f, 0.8f, 0.8f);
            this.heldItemRenderer.renderItem(entity, stack, ModelTransformationMode.THIRD_PERSON_RIGHT_HAND, false, matrices, vertexConsumers, light);

            matrices.pop();
        }
    }
}
