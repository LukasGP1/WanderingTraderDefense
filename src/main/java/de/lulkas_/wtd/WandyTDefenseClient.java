package de.lulkas_.wtd;

import de.lulkas_.wtd.entity.ModEntities;
import de.lulkas_.wtd.entity.client.model.MogswampArcherModel;
import de.lulkas_.wtd.entity.client.renderer.MogswampArcherRenderer;
import de.lulkas_.wtd.entity.client.model.MogswampModel;
import de.lulkas_.wtd.entity.client.renderer.MogswampRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class WandyTDefenseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(MogswampModel.MOGSWAMP, MogswampModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MOGSWAMP, MogswampRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MogswampArcherModel.MOGSWAMP_ARCHER, MogswampArcherModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MOGSWAMP_ARCHER, MogswampArcherRenderer::new);
    }
}
