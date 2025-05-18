package de.lulkas_.wtd.entity.custom;

import de.lulkas_.wtd.entity.ai.MogswampAttackGoal;
import de.lulkas_.wtd.entity.ai.MogswampFollowTraderGoal;
import de.lulkas_.wtd.entity.ai.MogswampTargetGoal;
import de.lulkas_.wtd.util.HasHitTraderData;
import de.lulkas_.wtd.util.IEntityDataSaver;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.UUID;

public class MogswampEntity extends HostileEntity {
    protected static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(MogswampEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    protected int idleAnimationTimeout = 0;
    public final AnimationState attackingAnimationState = new AnimationState();
    public int attackingAnimationTimeout = 0;
    protected WanderingTraderEntity trader;
    protected UUID traderUUID;
    protected MogswampFollowTraderGoal followTraderGoal;
    protected final String traderUUIDDataName = "TraderUUID";

    public MogswampEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.followTraderGoal = new MogswampFollowTraderGoal(this, 1, 3, 500);

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MogswampTargetGoal<>(this, PlayerEntity.class, false, livingEntity -> HasHitTraderData.getHasHitTrader(((IEntityDataSaver) livingEntity))));
        this.goalSelector.add(1, new MogswampAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, followTraderGoal);
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 100));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50);
    }

    protected void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 30;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }

        if(this.isAttacking() && attackingAnimationTimeout <= 0) {
            this.attackingAnimationTimeout = 30;
            this.attackingAnimationState.start(this.age);
        } else {
            this.attackingAnimationTimeout--;
        }

        if(!this.isAttacking()) {
            attackingAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        if(this.getWorld() instanceof ServerWorld world) {
            if(world.getEntity(traderUUID) instanceof WanderingTraderEntity trader) {
                this.trader = trader;
            }
        }

        if(this.trader != null) {
            this.followTraderGoal.setTrader(this.trader);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isOf(DamageTypes.ARROW)) {
            return false;
        }
        return super.damage(source, amount);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ATTACKING, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if(traderUUID != null) {
            nbt.putUuid(traderUUIDDataName, traderUUID);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if(nbt.contains(traderUUIDDataName)) {
            traderUUID = nbt.getUuid(traderUUIDDataName);
        }
    }

    public void setAttacking(boolean value) {
        this.dataTracker.set(ATTACKING, value);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    public void setTrader(WanderingTraderEntity trader) {
        traderUUID = trader.getUuid();
    }
}
