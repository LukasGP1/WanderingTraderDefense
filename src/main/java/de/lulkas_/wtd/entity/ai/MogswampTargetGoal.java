package de.lulkas_.wtd.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class MogswampTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    public MogswampTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, Predicate<LivingEntity> targetPredicate) {
        super(mob, targetClass, checkVisibility, targetPredicate);
    }

    @Override
    protected void findClosestTarget() {
        this.targetEntity = this.mob.getWorld().getClosestPlayer(this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
    }

    @Override
    public void tick() {
        super.tick();

        if(targetEntity != null) {
            if(targetEntity instanceof PlayerEntity) {
                findClosestTarget();
                this.mob.setTarget(this.targetEntity);
            }
        }
    }
}
