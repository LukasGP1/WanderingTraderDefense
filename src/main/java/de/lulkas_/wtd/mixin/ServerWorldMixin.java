package de.lulkas_.wtd.mixin;

import de.lulkas_.wtd.event.EntitySpawnCallback;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Inject(method = "spawnEntity", at = @At("TAIL"))
    private void onEntitySpawn(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if(((World)(Object)this) instanceof ServerWorld world) {
            EntitySpawnCallback.EVENT.invoker().onSpawn(world, entity);
        }
    }
}
