package de.lulkas_.wtd.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.passive.WanderingTraderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class WanderingTraderImmuneToArrowMixin<T extends LivingEntity> {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        if(self instanceof WanderingTraderEntity) {
            if(source.isOf(DamageTypes.ARROW)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
