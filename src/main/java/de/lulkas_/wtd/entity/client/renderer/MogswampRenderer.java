package de.lulkas_.wtd.entity.client.renderer;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.entity.client.model.MogswampModel;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MogswampRenderer extends MobEntityRenderer<MogswampEntity, MogswampModel<MogswampEntity>> {
    public MogswampRenderer(EntityRendererFactory.Context context) {
        super(context, new MogswampModel<>(context.getPart(MogswampModel.MOGSWAMP)), 0.5f);
    }

    @Override
    public Identifier getTexture(MogswampEntity entity) {
        return Identifier.of(WandyTDefense.MOD_ID, "textures/entity/mogswamp/mogswamp.png");
    }
}
