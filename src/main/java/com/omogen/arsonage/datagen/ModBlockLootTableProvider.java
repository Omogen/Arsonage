package com.omogen.arsonage.datagen;

import java.util.Set;

import com.omogen.arsonage.blocks.CheeseBlock;
import com.omogen.arsonage.blocks.ModBlocks;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModBlockLootTableProvider extends BlockLootSubProvider{

	protected ModBlockLootTableProvider(Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	protected void generate() {
		this.add(ModBlocks.CHEESE_BLOCK.get(), block -> LootTable.lootTable()
				.withPool(LootPool.lootPool()
				.setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(ModBlocks.CHEESE_BLOCK.get())
				.when(LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(ModBlocks.CHEESE_BLOCK.get())
				.setProperties(StatePropertiesPredicate.Builder.properties()
				.hasProperty(CheeseBlock.BITES, 0))))));
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
	}

}
