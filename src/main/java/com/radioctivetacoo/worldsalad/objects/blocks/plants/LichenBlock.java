package com.radioctivetacoo.worldsalad.objects.blocks.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class LichenBlock extends Block {
	private static final VoxelShape SHAPE = Block.makeCuboidShape(1, 0, 1, 15, 8, 15);
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	public LichenBlock(Properties properties) {
		super(properties);
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setMotionMultiplier(state, new Vec3d(0.75D, (double) 0.75F, 0.75D));
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }

	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
	   }
}
