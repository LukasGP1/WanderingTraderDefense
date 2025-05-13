package de.lulkas_.wtd.event;

import de.lulkas_.wtd.WandyTDefense;
import de.lulkas_.wtd.util.HasHitTraderData;
import de.lulkas_.wtd.util.IEntityDataSaver;
import de.lulkas_.wtd.util.MogswampSpawner;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.passive.TraderLlamaEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.util.ActionResult;

public class ModEvents {
    public static void registerModEvents() {
        WandyTDefense.LOGGER.info("Registering Mod Events for " + WandyTDefense.MOD_ID);

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if(entity instanceof WanderingTraderEntity || entity instanceof TraderLlamaEntity) {
                HasHitTraderData.setHasHitTrader(((IEntityDataSaver) player), true);
            }
            return ActionResult.PASS;
        });

        EntitySpawnCallback.EVENT.register(((world, entity) -> {
            if(entity instanceof WanderingTraderEntity trader) {
                MogswampSpawner.spawnMogswmaps(world, trader);
            }
        }));
    }
}
