package de.lulkas_.wtd;

import de.lulkas_.wtd.entity.ModEntities;
import de.lulkas_.wtd.entity.client.MogswampModel;
import de.lulkas_.wtd.entity.client.MogswampRenderer;
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
    }
}
