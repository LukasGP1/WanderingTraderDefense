package de.lulkas_.wtd.entity.custom;

import de.lulkas_.wtd.entity.ai.MogswampFollowTraderGoal;
import de.lulkas_.wtd.entity.ai.MogswampTargetGoal;
import de.lulkas_.wtd.util.HasHitTraderData;
import de.lulkas_.wtd.util.IEntityDataSaver;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MogswampArcherEntity extends MogswampEntity implements RangedAttackMob {
    public MogswampArcherEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.followTraderGoal = new MogswampFollowTraderGoal(this, 1, 10, 500);

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MogswampTargetGoal<>(this, PlayerEntity.class, false, livingEntity -> HasHitTraderData.getHasHitTrader(((IEntityDataSaver) livingEntity))));
        this.goalSelector.add(1, new BowAttackGoal<>(this, 1, 5, 5));
        this.goalSelector.add(2, followTraderGoal);
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 100));
    }

    @Override
    protected void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 30;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    @Override
    protected void drop(ServerWorld world, DamageSource damageSource) {
        boolean bl = this.playerHitTimer > 0;
        if (this.shouldDropLoot() && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropLoot(damageSource, bl);
        }

        this.dropInventory();
        this.dropXp(damageSource.getAttacker());
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        if(slot == EquipmentSlot.MAINHAND) {
            ItemStack stack = new ItemStack(Items.BOW);
            stack.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true));

            return stack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
        ItemStack itemStack2 = this.getProjectileType(itemStack);
        PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
        double d = target.getX() - this.getX();
        double f = target.getZ() - this.getZ();
        persistentProjectileEntity.setVelocity(d, 0, f, 10F, 14 - this.getWorld().getDifficulty().getId() * 4);
        persistentProjectileEntity.setDamage(0.8);
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(persistentProjectileEntity);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        return ProjectileUtil.createArrowProjectile(this, arrow, damageModifier, shotFrom);
    }
}
