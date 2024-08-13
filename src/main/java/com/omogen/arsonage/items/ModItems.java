package com.omogen.arsonage.items;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import static com.omogen.arsonage.Arsonage.MODID;

import com.omogen.arsonage.blocks.ModBlocks;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;




public class ModItems {
    	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
		public static final DeferredItem<BlockItem> CHEESE_BLOCK = ITEMS.registerSimpleBlockItem(ModBlocks.CHEESE_BLOCK);
}
