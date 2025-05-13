package de.lulkas_.wtd.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;

public interface EntitySpawnCallback {
    Event<EntitySpawnCallback> EVENT = EventFactory.createArrayBacked(
            EntitySpawnCallback.class,
            (listeners) -> (world, entity) -> {
                for(EntitySpawnCallback listener : listeners) {
                    listener.onSpawn(world, entity);
                }
            }
    );

    void onSpawn(ServerWorld world, Entity entity);
}
