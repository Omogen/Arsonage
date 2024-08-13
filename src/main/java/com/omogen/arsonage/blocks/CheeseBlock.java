package com.omogen.arsonage.blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DeferredRegister.Blocks;
import static com.omogen.arsonage.blocks.ModBlocks.CHEESE_BLOCK;

public class CheeseBlock extends Block{
	
public CheeseBlock(Properties p_49795_) {
		super(p_49795_);
	}
public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 5);
@Override
protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
   pBuilder.add(BITES);
}


	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if(!event.getLevel().isClientSide()) {
		System.out.println("right clicked");
		if (event.getLevel().getBlockState(event.getPos()).getBlock() == ModBlocks.CHEESE_BLOCK.get()) {
			System.out.println("right clicked cheese");
			BlockState state = event.getLevel().getBlockState(event.getPos());
            int bites = state.getValue(BITES);
			if (bites < 5) {
				System.out.println("bites WAS ="+bites);
				bites++;
				System.out.println("bites NOW ="+bites);
			event.getLevel().setBlock(event.getPos(), state.setValue(BITES, bites), 3);
            } else {
                event.getLevel().removeBlock(event.getPos(), false);
            }
			}
		}
    }
}