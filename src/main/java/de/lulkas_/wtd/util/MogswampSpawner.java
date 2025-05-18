package de.lulkas_.wtd.util;

import de.lulkas_.wtd.entity.ModEntities;
import de.lulkas_.wtd.entity.custom.MogswampArcherEntity;
import de.lulkas_.wtd.entity.custom.MogswampEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class MogswampSpawner {
    public static void spawnMogswmaps(ServerWorld world, WanderingTraderEntity trader) {
        BlockPos pos = trader.getBlockPos();
        Random random = new Random();
        if(random.nextInt() > 0) {
            MogswampEntity mogswamp = ModEntities.MOGSWAMP.spawn(world, pos, SpawnReason.EVENT);
            if(mogswamp != null) {
                mogswamp.setTrader(trader);
            }
        } else {
            MogswampArcherEntity mogswamp = ModEntities.MOGSWAMP_ARCHER.spawn(world, pos, SpawnReason.EVENT);
            if(mogswamp != null) {
                mogswamp.setTrader(trader);
            }
        }
    }
}
