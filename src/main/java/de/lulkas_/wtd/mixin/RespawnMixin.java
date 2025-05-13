package de.lulkas_.wtd.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.lulkas_.wtd.util.HasHitTraderData;
import de.lulkas_.wtd.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerManager.class)
public class RespawnMixin {
    @Inject(method = "respawnPlayer", at = @At("RETURN"))
    protected void injectRespawnMethod(ServerPlayerEntity player, boolean alive, Entity.RemovalReason removalReason, CallbackInfoReturnable<ServerPlayerEntity> cir, @Local(ordinal = 0) ServerPlayerEntity playerLocal) {
        HasHitTraderData.setHasHitTrader(((IEntityDataSaver) playerLocal), false);
    }
}
