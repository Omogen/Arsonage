package com.omogen.arsonage.blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CheeseBlock extends Block{

	public CheeseBlock(Properties p_49795_) {
		super(p_49795_);
	}

	public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 5);
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(BITES);
		}
	@Override
	protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
		if(!pLevel.isClientSide()) {
			if (pLevel.getBlockState(pPos).is(ModBlocks.CHEESE_BLOCK.get())) {
				FoodData foodData = pPlayer.getFoodData();
				if ((foodData.getFoodLevel() < 20) || pPlayer.isCreative()) {
					pLevel.playSound(null, pPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);
					pPlayer.swing(InteractionHand.MAIN_HAND, true);
					foodData.eat(4, 0.1F);
					int bites = pState.getValue(BITES);
					if (bites < 5) {
						bites++;
						pLevel.setBlock(pPos, pState.setValue(BITES, bites), 3);
						if (bites == 5) {
							pLevel.removeBlock(pPos, false);
						}
					}
					return InteractionResult.SUCCESS;
				}
			}
		}
		else {
			return InteractionResult.SUCCESS;
	}
		return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		 switch (state.getValue(BITES)) {
	        case 0:
	        default:
	            return Block.box(0, 0, 0, 16, 16, 16);
	        case 1:
	            return Block.box(0, 0, 0, 13, 16, 16);
	        case 2:
	            return Block.box(0, 0, 0, 10, 16, 16);
	        case 3:
	            return Block.box(0, 0, 0, 7, 16, 16);
	        case 4:
	            return Block.box(0, 0, 0, 4, 16, 16);
	    }
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return this.getShape(state, world, pos, context);
	}
}