package de.lulkas_.wtd.entity.client;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MogswampRenderer extends MobEntityRenderer<MogswampEntity, MogswampModel<MogswampEntity>> {
    public MogswampRenderer(EntityRendererFactory.Context context) {
        super(context, new MogswampModel<>(context.getPart(MogswampModel.MOGSWAMP)), 0.5f);
    }

    @Override
    public Identifier getTexture(MogswampEntity entity) {
        return Identifier.of(WandyTDefense.MOD_ID, "textures/entity/mogswamp/mogswamp.png");
    }

    @Override
    public void render(MogswampEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
