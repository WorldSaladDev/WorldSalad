package com.radioctivetacoo.worldsalad.objects.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BreathingReedItem extends Item {

	public BreathingReedItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
			BlockPos airPos = new BlockPos(playerIn.getPosX(), playerIn.getPosYEye() + 5, playerIn.getPosZ());
			BlockPos eyePos = new BlockPos(playerIn.getPosX(), playerIn.getPosYEye(), playerIn.getPosZ());
			ItemStack itemstack = playerIn.getHeldItem(handIn);
			if (!worldIn.isRemote) {
				if(worldIn.getBlockState(airPos).getBlock().equals(Blocks.AIR) && worldIn.getBlockState(eyePos).getBlock().equals(Blocks.WATER)) {
					playerIn.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 100));
					playerIn.getCooldownTracker().setCooldown(this, 99);
					itemstack.damageItem(1, playerIn, (p_220017_1_) -> {
			            p_220017_1_.sendBreakAnimation(handIn);
			         });
					}
			}
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}
