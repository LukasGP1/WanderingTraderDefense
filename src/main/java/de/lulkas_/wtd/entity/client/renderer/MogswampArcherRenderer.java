package de.lulkas_.wtd.entity.client.renderer;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.client.model.MogswampArcherModel;
import de.lulkas_.wtd.entity.client.renderer.feature.MogswampEntityHeldItemFeatureRenderer;
import de.lulkas_.wtd.entity.custom.MogswampArcherEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MogswampArcherRenderer extends MobEntityRenderer<MogswampArcherEntity, MogswampArcherModel<MogswampArcherEntity>> {
    public MogswampArcherRenderer(EntityRendererFactory.Context context) {
        super(context, new MogswampArcherModel<>(context.getPart(MogswampArcherModel.MOGSWAMP_ARCHER)), 0.5f);
        this.addFeature(new MogswampEntityHeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(MogswampArcherEntity entity) {
        return Identifier.of(WandyTDefense.MOD_ID, "textures/entity/mogswamp/mogswamp.png");
    }
}
