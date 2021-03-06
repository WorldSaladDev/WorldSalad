package com.radioctivetacoo.worldsalad.entities;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Browncap extends CreatureEntity implements IAnimatable {
	@SuppressWarnings("unused")
	private UUID angerTargetUUID;
	private AnimationFactory manager = new AnimationFactory(this);
	
	public Browncap(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.experienceValue = 6;
	}

	@Override
	protected boolean canDropLoot() {
		return true;
	}
	
	public void setRevengeTarget(@Nullable LivingEntity livingBase) {
	      super.setRevengeTarget(livingBase);
	      if (livingBase != null) {
	         this.angerTargetUUID = livingBase.getUniqueID();
	      }

	   }

	@Override
	public void livingTick() {
		this.updateArmSwingProgress();
		super.livingTick();
	}
	
	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_GENERIC_SWIM;
	}
	
	@Override
	protected boolean isDespawnPeaceful() {
		return false;
	}

	@Override
	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_GENERIC_SPLASH;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isInvulnerableTo(source) ? false : super.attackEntityFrom(source, amount);
	}

	@Override
	protected SoundEvent getFallSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.ENTITY_GENERIC_BIG_FALL : SoundEvents.ENTITY_GENERIC_SMALL_FALL;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(12, new LookAtGoal(this, Browncap.class, 3.0F));
		this.goalSelector.addGoal(11, new LookAtGoal(this, TraderAnt.class, 3.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(8, new SwimGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FungalZombie.class, true));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
		this.targetSelector.addGoal(8, (new HurtByTargetGoal(this, Browncap.class).setCallsForHelp(Browncap.class)));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45F);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_GENERIC_HURT;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return true;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (super.attackEntityAsMob(entityIn)) {
			if (entityIn instanceof LivingEntity) {
				int i = 2;
				if (this.world.getDifficulty() == Difficulty.NORMAL) {
					i = 3;
				} else if (this.world.getDifficulty() == Difficulty.HARD) {
					i = 4;
				}

				if (i > 0) {
					((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, i * 50, 0));
				}
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public AnimationFactory getFactory() {
		return manager;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}
	
	@SuppressWarnings("unchecked")
	private <E extends Bluecap> PlayState predicate(AnimationEvent<E> event)
	{
		if(event.isMoving())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.redcap.walking", true));
			event.getController().transitionLengthTicks = 0; 
			return PlayState.CONTINUE;
		} else {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.redcap.idle", true));
			event.getController().transitionLengthTicks = 0;
			return PlayState.CONTINUE;
		}
	}
	
}
