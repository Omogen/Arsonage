package com.omogen.arsonage.blocks;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import static com.omogen.arsonage.Arsonage.MODID;

public class ModBlocks{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
	public static final DeferredBlock<Block> CHEESE_BLOCK = BLOCKS.register("cheese_block", 
		() -> new CheeseBlock(BlockBehaviour.Properties.of()
			.destroyTime(1f)
			.explosionResistance(6f)
			.sound(SoundType.HONEY_BLOCK)
			.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "cheese_block")))
		));
}